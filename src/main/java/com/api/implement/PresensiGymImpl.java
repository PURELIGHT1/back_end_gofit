package com.api.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.PresensiGymRequest;
import com.api.dto.PresensiGymResponse;
import com.api.implement.builder.GenerateImpl;
import com.api.models.entities.BookingGym;
import com.api.models.entities.Pegawai;
import com.api.models.entities.PresensiGym;
import com.api.models.repos.BookingGymRepo;
import com.api.models.repos.PresensiGymRepo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PresensiGymImpl {

    @Autowired
    private PresensiGymRepo repo;

    @Autowired
    private GenerateImpl generateImpl;

    @Autowired
    private PegawaiImpl pegawaiImpl;

    @Autowired
    private BookingGymImpl bookingGymImpl;

    public List<PresensiGymResponse> findAll() {
        List<PresensiGym> list = repo.findAll();
        List<PresensiGymResponse> listResponse = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < list.size(); i++) {
            PresensiGymResponse response = new PresensiGymResponse();
            PresensiGym pg = list.get(i);

            response.setId(pg.getId());
            response.setIdMember(pg.getBookingGym().getMember().getId());
            response.setMember(pg.getBookingGym().getMember().getNama());
            response.setTglpresensi(pg.getBookingGym().getTglBooking());
            response.setMulaiGym(pg.getBookingGym().getSesi());
            response.setAkhirGym(pg.getAkhirGym());
            response.setStatus(pg.getStatus());

            listResponse.add(response);
        }
        return listResponse;
    }

    // public List<PresensiGym> findAll() {

    // return (List<PresensiGym>) repo.findAll();
    // }

    // public List<PresensiGym> findAllByMember(String id) {

    // Member memberDB = memberImpl.findByIdMember(id);
    // return (List<PresensiGym>) repo.findAllPresensiGymMember(memberDB);
    // }

    // public List<PresensiGymBookingResponse> findAllPresensiBooking() {
    // List<PresensiGymBookingResponse> list = new ArrayList<>();
    // List<Member> member = memberImpl.findAll();
    // List<BookingGym> bookingGyms =
    // for (int i = 0; i < kelasDB.size(); i++) {
    // PresensiGymBookingResponse DB = new PresensiGymBookingResponse();
    // DB.setMember(null);
    // list.add(DB);
    // }

    // return list;
    // }

    public PresensiGym createPresensi(PresensiGymRequest req) {

        PresensiGym DB = new PresensiGym();
        BookingGym bookingGymDB = bookingGymImpl.findBookingById(req.getBooking());
        Pegawai pegawaiDB = pegawaiImpl.findByIdPegawai(req.getPegawai());
        Date now = new Date();

        DB.setStatus("G");
        DB.setAkhirGym(bookingGymDB.getSesi() + 1);
        DB.setBookingGym(bookingGymDB);
        DB.setPegawai(pegawaiDB);

        bookingGymDB.setStatus("G");
        bookingGymImpl.update(bookingGymDB);

        DateFormat dateFormat = new SimpleDateFormat("YY.MM");
        String currentDateTime = dateFormat.format(now);

        Integer counter2 = generateImpl.findGenerateStruk(1);
        if (counter2 != 0) {
            counter2 += 1;
            DB.setId(currentDateTime + "." + counter2);
            generateImpl.updateGenereteStruk(counter2);
        }
        return repo.save(DB);
    }

    public PresensiGym findPresensiById(String id) {

        return repo.findById(id).get();
    }

    public PresensiGym updateDataPresensi(String id) {
        PresensiGym DB = findPresensiById(id);
        DB.setStatus("E");
        BookingGym bookingGymDB = bookingGymImpl.findBookingById(DB.getBookingGym().getId());
        bookingGymDB.setStatus("E");
        bookingGymImpl.update(bookingGymDB);
        return repo.save(DB);
    }

    public PresensiGym update(PresensiGym presensiGym) {
        return repo.save(presensiGym);
    }
}
