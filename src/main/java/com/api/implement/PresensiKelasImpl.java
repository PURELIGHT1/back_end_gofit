package com.api.implement;

import java.util.ArrayList;
// import java.text.DateFormat;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ResponsePresensiKelas;
import com.api.models.entities.PresensiKelas;
import com.api.models.repos.PresensiKelasRepo;

@Service
public class PresensiKelasImpl {

    @Autowired
    private PresensiKelasRepo repo;

    public List<ResponsePresensiKelas> findAll() {

        List<ResponsePresensiKelas> pk = new ArrayList<>();

        List<PresensiKelas> listPk = repo.findAll();

        listPk.forEach(i -> {
            ResponsePresensiKelas response = new ResponsePresensiKelas();
            response.setId(i.getId());
            response.setMember(i.getBookingKelas().getMember().getNama());
            response.setIdMember(i.getBookingKelas().getMember().getId());
            response.setIdKelas(i.getBookingKelas().getJadwal().getKelas().getId());
            response.setKelas(i.getBookingKelas().getJadwal().getKelas().getNama());
            if (i.getBookingKelas().getJadwal().getInstrukturPeganti() == null) {
                response.setIdInstruktur(i.getBookingKelas().getJadwal().getInstruktur().getId());
                response.setInstruktur(i.getBookingKelas().getJadwal().getInstruktur().getInisial());
            } else {
                response.setIdInstruktur(i.getBookingKelas().getJadwal().getInstrukturPeganti().getId());
                response.setInstruktur(i.getBookingKelas().getJadwal().getInstrukturPeganti().getInisial());
            }
            response.setStatus(i.getStatus());
            response.setTgl(i.getTglpresensi());
            pk.add(response);
        });
        return pk;
    }

    // public List<PresensiKelas> findAllByMember(String id) {

    // Member memberDB = memberImpl.findByIdMember(id);
    // return (List<PresensiKelas>) repo.findAllPresensiKelasMember(memberDB);
    // }

    // public List<PresensiMemberCustomeResponse> findAllPresensiKelas(String idIns)
    // {
    // // Integer[] sesi = { 1, 2, 3, 4 };

    // List<PresensiMemberCustomeResponse> list = new ArrayList<>();
    // for (int i = 1; i <= 4; i++) {
    // PresensiMemberCustomeResponse DB = new PresensiMemberCustomeResponse();
    // String status = "S";

    // Date now = new Date();
    // // LocalDate now = LocalDate.now();
    // DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    // String currentDate = dateFormat2.format(now);
    // DateFormat dateFormat = new SimpleDateFormat("EEEEE");
    // String hari = dateFormat.format(now);
    // if (hari.equals("Sunday")) {
    // hari = "Minggu";
    // } else if (hari.equals("Monday")) {
    // hari = "Senin";
    // } else if (hari.equals("Tuesday")) {
    // hari = "Selasa";
    // } else if (hari.equals("Wednesday")) {
    // hari = "Rabu";
    // } else if (hari.equals("Thursday")) {
    // hari = "Kamis";
    // } else if (hari.equals("Friday")) {
    // hari = "Jumat";
    // } else if (hari.equals("Saturday")) {
    // hari = "Sabtu";
    // }

    // // Instruktur instrukturDB = instrukturImpl.findByIdInstruktur(idIns);
    // JadwalHarian jadwalHarianDB = jadwalHarianRepo.findJadwalPresensi(
    // currentDate, status, i, hari, idIns);
    // DB.setJadwal(jadwalHarianDB);
    // // DB.setInstruktur(instrukturDB);
    // DB.setStatusPresensiKelasSendiri("T");

    // // String currentDate2 = dateFormat2.format(jadwalHarianDB.getTglJadwal());
    // Integer slot = repo.getSlotMember(currentDate, 20);
    // DB.setJumlahMemberKelas(slot + i);
    // list.add(DB);
    // }
    // return list;
    // // try {
    // // PresensiMemberCustomeResponse DB = new PresensiMemberCustomeResponse();
    // // String status = "G";

    // // LocalDate now = LocalDate.now();
    // // DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    // // String currentDate = dateFormat2.format(now);
    // // Date nowtgl = dateFormat2.parse(currentDate);

    // }
}
