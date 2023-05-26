package com.api.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.models.entities.BookingGym;
import com.api.models.entities.Member;
import com.api.models.entities.PresensiGym;
import com.api.models.repos.PresensiGymRepo;

import java.util.Date;
import java.util.List;

@Service
public class PresensiGymImpl {

    @Autowired
    private PresensiGymRepo repo;

    @Autowired
    private MemberImpl memberImpl;

    @Autowired
    private BookingGymImpl bookingGymImpl;

    public List<PresensiGym> findAll() {

        return (List<PresensiGym>) repo.findAll();
    }

    public List<PresensiGym> findAllByMember(String id) {

        Member memberDB = memberImpl.findByIdMember(id);
        return (List<PresensiGym>) repo.findAllPresensiGymMember(memberDB);
    }

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

    public PresensiGym createPresensi(String id) {

        PresensiGym DB = findPresensiById(id);
        Date now = new Date();
        DB.setTglpresensi(now);
        DB.setAkhirGym(DB.getMulaiGym() + 1);
        DB.setStatus("G");
        String idBooking = DB.getBookingGym().getId();
        BookingGym bookingGymDB = bookingGymImpl.findBookingById(idBooking);
        bookingGymDB.setStatus("G");
        bookingGymImpl.update(bookingGymDB);
        return repo.save(DB);
    }

    public PresensiGym findPresensiById(String id) {

        return repo.findById(id).get();
    }

    public PresensiGym updateDataPresensi(String id) {
        PresensiGym DB = findPresensiById(id);
        DB.setStatus("E");
        String idBooking = DB.getBookingGym().getId();
        BookingGym bookingGymDB = bookingGymImpl.findBookingById(idBooking);
        bookingGymDB.setStatus("E");
        bookingGymImpl.update(bookingGymDB);
        return repo.save(DB);
    }

    public PresensiGym update(PresensiGym presensiGym) {
        return repo.save(presensiGym);
    }
}
