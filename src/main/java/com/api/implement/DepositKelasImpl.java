package com.api.implement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.DepositKelasRequest;
import com.api.dto.TransaksiDepositKelasResponse;
import com.api.implement.builder.GenerateImpl;
import com.api.models.entities.Kelas;
import com.api.models.entities.Member;
import com.api.models.entities.Pegawai;
import com.api.models.entities.Promo;
import com.api.models.entities.TransaksiDepositKelas;
import com.api.models.repos.DepositKelasRepo;

@Service
public class DepositKelasImpl {

    @Autowired
    private DepositKelasRepo repo;

    @Autowired
    private GenerateImpl generateImpl;

    @Autowired
    private PegawaiImpl pegawaiImpl;

    @Autowired
    private MemberImpl memberImpl;

    @Autowired
    private PromoImpl promoImpl;

    @Autowired
    private KelasImpl kelasImpl;

    // public List<TransaksiDepositKelas> findAll() {
    // return (List<TransaksiDepositKelas>) repo.findAll();
    // }
    public List<TransaksiDepositKelasResponse> findAll() {
        List<TransaksiDepositKelas> listDK = repo.findAll();
        List<TransaksiDepositKelasResponse> list = new ArrayList<>();

        for (int i = 0; i < listDK.size(); i++) {
            TransaksiDepositKelasResponse DB = new TransaksiDepositKelasResponse();
            TransaksiDepositKelas DK = listDK.get(i);

            DB.setId(DK.getId());
            DB.setPegawai(DK.getPegawai().getNama());
            DB.setIdPegawai(DK.getPegawai().getId());
            DB.setIdMember(DK.getMember().getId());
            DB.setMember(DK.getMember().getNama());
            DB.setKelas(DK.getKelas().getNama());
            DB.setIdKelas(DK.getKelas().getId());

            Promo promo = DK.getPromo();
            if (promo == null) {
                DB.setPromo("-");
            } else {
                DB.setPromo(promo.getJenis());
                DB.setIdPromo(promo.getId());
            }

            DB.setSisaKelas(DK.getSisaKelas());
            DB.setTglBerlaku(DK.getTglBerlaku());
            DB.setStatus(DK.getStatus());

            list.add(DB);
        }
        return list;
    }

    public ArrayList<TransaksiDepositKelas> findAllByMember(String id) {
        Member memberDB = memberImpl.findByIdMember(id);
        return (ArrayList<TransaksiDepositKelas>) repo.findAllTransaksiDepositKelas(memberDB);
    }

    public TransaksiDepositKelas createTransaksi(DepositKelasRequest req) {
        TransaksiDepositKelas DB = new TransaksiDepositKelas();

        DateFormat dateFormat = new SimpleDateFormat("YY.MM");
        Date date = new Date();
        String currentDateTime = dateFormat.format(date);
        Integer counter = generateImpl.findGenerateStruk(1);
        counter += 1;
        DB.setId(currentDateTime + "." + counter);
        generateImpl.updateGenereteStruk(counter);

        Pegawai pegawaiDB = pegawaiImpl.findByIdPegawai(req.getPegawai());
        Member memberDB = memberImpl.findByIdMember(req.getMember());

        Kelas kelasDB = kelasImpl.findByIdKelas(req.getKelas());

        DB.setPegawai(pegawaiDB);
        DB.setMember(memberDB);
        DB.setKelas(kelasDB);
        DB.setTglDeposit(date);
        DB.setStatus("P");

        if (req.getTotalKelas() >= 5 && req.getTotalKelas() <= 9) {
            DB.setTotalKelas(req.getTotalKelas());
            DB.setSisaKelas(req.getTotalKelas() + 1);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 30);
            Date TglBerlaku = cal.getTime();
            DB.setTglBerlaku(TglBerlaku);

            Promo promoDB = promoImpl.findByjenis("Paket").get(0);
            DB.setPromo(promoDB);

            Integer totalDeposit = req.getTotalKelas() * kelasDB.getHarga();
            DB.setTotalDeposit(totalDeposit);

        } else if (req.getTotalKelas() >= 10) {
            DB.setTotalKelas(req.getTotalKelas());
            DB.setSisaKelas(req.getTotalKelas() + 3);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 60);
            Date TglBerlaku = cal.getTime();
            DB.setTglBerlaku(TglBerlaku);

            Promo promoDB = promoImpl.findByjenis("Paket").get(0);
            DB.setPromo(promoDB);

            Integer totalDeposit = req.getTotalKelas() * kelasDB.getHarga();
            DB.setTotalDeposit(totalDeposit);
        } else {
            DB.setTotalKelas(req.getTotalKelas());
            DB.setSisaKelas(req.getTotalKelas());

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 30);
            Date TglBerlaku = cal.getTime();
            DB.setTglBerlaku(TglBerlaku);

            Promo promoDB = promoImpl.findByjenis("Paket").get(0);
            DB.setPromo(promoDB);

            Integer totalDeposit = req.getTotalKelas() * kelasDB.getHarga();
            DB.setTotalDeposit(totalDeposit);
        }

        repo.save(DB);
        memberImpl.updateDepositMemberKurang(memberDB.getId(), DB.getTotalDeposit());
        return DB;
    }
}
