package com.api.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.DepositUangRequest;
import com.api.dto.TransaksiDepositUangResponse;
import com.api.implement.builder.GenerateImpl;
import com.api.models.entities.Member;
import com.api.models.entities.Pegawai;
import com.api.models.entities.Promo;
import com.api.models.entities.TransaksiDepositUang;
import com.api.models.repos.DepositUangRepo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DepositUangImpl {

    @Autowired
    private DepositUangRepo repo;

    @Autowired
    private GenerateImpl generateImpl;

    @Autowired
    private PegawaiImpl pegawaiImpl;

    @Autowired
    private MemberImpl memberImpl;

    @Autowired
    private PromoImpl promoImpl;

    // public List<TransaksiDepositUang> findAll() {
    // return (List<TransaksiDepositUang>) repo.findAll();
    // }

    public List<TransaksiDepositUangResponse> findAll() {
        List<TransaksiDepositUang> listDU = repo.findAll();

        List<TransaksiDepositUangResponse> list = new ArrayList<>();

        for (int i = 0; i < listDU.size(); i++) {
            TransaksiDepositUangResponse DB = new TransaksiDepositUangResponse();
            TransaksiDepositUang DU = listDU.get(i);

            DB.setId(DU.getId());
            DB.setPegawai(DU.getPegawai().getNama());
            DB.setIdPegawai(DU.getPegawai().getId());
            DB.setIdMember(DU.getMember().getId());
            DB.setMember(DU.getMember().getNama());

            Promo promo = DU.getPromo();
            if (promo == null) {
                DB.setPromo("-");
            } else {
                DB.setPromo(promo.getJenis());
                DB.setIdPromo(promo.getId());
            }

            DB.setJlhDeposit(DU.getJlhDeposit());
            DB.setTglDeposit(DU.getTglDeposit());
            DB.setTotalDeposit(DU.getTotalDeposit());
            DB.setStatus(DU.getStatus());

            list.add(DB);
        }
        return list;
    }

    public List<TransaksiDepositUang> findAllByMember(String id) {
        // Member memberDB = memberImpl.findByIdMember(id);
        return (List<TransaksiDepositUang>) repo.findAll();
    }

    public TransaksiDepositUang createTransaksi(DepositUangRequest req) {
        TransaksiDepositUang DB = new TransaksiDepositUang();

        DateFormat dateFormat = new SimpleDateFormat("YY.MM");
        Date date = new Date();
        String currentDateTime = dateFormat.format(date);
        Integer counter = generateImpl.findGenerateStruk(1);
        counter += 1;
        DB.setId(currentDateTime + "." + counter);
        generateImpl.updateGenereteStruk(counter);

        Pegawai pegawaiDB = pegawaiImpl.findByIdPegawai(req.getPegawai());
        Member memberDB = memberImpl.findByIdMember(req.getMember());

        DB.setPegawai(pegawaiDB);
        DB.setMember(memberDB);
        DB.setJlhDeposit(req.getJlhDeposit());
        DB.setTglDeposit(date);
        DB.setStatus("P");

        DB.setJlhDeposit(req.getJlhDeposit());

        if (req.getJlhDeposit() >= 3000000) {
            DB.setTotalDeposit(req.getJlhDeposit() + 300000);
            Promo promoDB = promoImpl.findByjenis("Reguler").get(0);
            DB.setPromo(promoDB);
        } else {
            DB.setTotalDeposit(req.getJlhDeposit());
        }

        repo.save(DB);
        memberImpl.updateDepositMember(memberDB.getId(), DB.getTotalDeposit());
        return DB;
    }

}
