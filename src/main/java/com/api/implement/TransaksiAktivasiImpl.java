package com.api.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.api.dto.TransaksiAktivasiRequest;
import com.api.dto.TransaksiAktivasiResponse;
import com.api.exception.member.MemberExceptionBadRequest;
import com.api.implement.builder.GenerateImpl;
import com.api.models.entities.Member;
import com.api.models.entities.Pegawai;
import com.api.models.entities.TransaksiAktivasi;
import com.api.models.repos.TransaksiAktivasiRepo;

@Service
public class TransaksiAktivasiImpl {

    @Autowired
    private TransaksiAktivasiRepo repo;

    @Autowired
    private MemberImpl memberImpl;

    @Autowired
    private PegawaiImpl pegawaiImpl;

    @Autowired
    private GenerateImpl generateImpl;

    public List<TransaksiAktivasiResponse> findAllAktivasi() {
        List<TransaksiAktivasi> list = repo.findAll();
        List<TransaksiAktivasiResponse> listResponse = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            TransaksiAktivasiResponse response = new TransaksiAktivasiResponse();
            TransaksiAktivasi ta = list.get(i);

            response.setId(ta.getId());
            response.setIdMember(ta.getMember().getId());
            response.setMember(ta.getMember().getNama());
            response.setIdPegawai(ta.getPegawai().getId());
            response.setPegawai(ta.getPegawai().getNama());
            response.setJlhBayar(ta.getJlhBayar());
            response.setTglBerlaku(ta.getTglBerlaku());
            response.setTglAktiviasi(ta.getTglAktiviasi());
            response.setStatus(ta.getStatus());

            listResponse.add(response);
        }
        return listResponse;
    }

    public TransaksiAktivasi findAllByMember(String id) {
        Member memberDB = memberImpl.findByIdMember(id);
        return repo.findTAMember(memberDB);
    }

    public List<TransaksiAktivasi> findByMember(String id) {
        Member memberDB = memberImpl.findByIdMember(id);
        return repo.findAllTAMember(memberDB);
    }
    // public List<TransaksiAktivasi> findAllNonAktivasi() {
    // return (List<TransaksiAktivasi>) repo.findAllNonAktivasi();
    // }

    public TransaksiAktivasi findTransaksiAktivasiById(String id) {
        if (repo.findById(id).isEmpty()) {
            throw new MemberExceptionBadRequest("Data tidak ditemukan");
        }
        return repo.findById(id).get();
    }

    public TransaksiAktivasi updateTransaksiAktivasi(String id) {
        TransaksiAktivasi transaksiAktivasiDB = findTransaksiAktivasiById(id);
        // Member memberDB =
        // memberImpl.findByIdMember(transaksiAktivasiDB.getMember().getId());
        memberImpl.updateMemberStatus(transaksiAktivasiDB.getMember().getId());
        transaksiAktivasiDB.setStatus("P");
        repo.save(transaksiAktivasiDB);
        return transaksiAktivasiDB;
    }

    public TransaksiAktivasi createTransaksiAktivasi(TransaksiAktivasiRequest req) {
        TransaksiAktivasi transaksiAktivasiDB = new TransaksiAktivasi();

        Pegawai pegawaiDB = pegawaiImpl.findByIdPegawai(req.getPegawai());
        transaksiAktivasiDB.setPegawai(pegawaiDB);

        Member memberDB = memberImpl.findByIdMember(req.getMember());
        transaksiAktivasiDB.setMember(memberDB);
        transaksiAktivasiDB.setJlhBayar(3000000);

        Date now = new Date();
        transaksiAktivasiDB.setTglAktiviasi(now);

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, 365);
        Date tglAktivasi = cal.getTime();
        transaksiAktivasiDB.setTglBerlaku(tglAktivasi);

        transaksiAktivasiDB.setStatus("P");

        DateFormat dateFormat = new SimpleDateFormat("YY.MM");
        String currentDateTime = dateFormat.format(now);

        Integer counter2 = generateImpl.findGenerateStruk(1);
        if (counter2 != 0) {
            counter2 += 1;
            transaksiAktivasiDB.setId(currentDateTime + "." + counter2);
            generateImpl.updateGenereteStruk(counter2);
        }

        repo.save(transaksiAktivasiDB);
        return transaksiAktivasiDB;
    }
}
