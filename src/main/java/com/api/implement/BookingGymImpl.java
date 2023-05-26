package com.api.implement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.BookingGymRequest;
import com.api.implement.builder.GenerateImpl;
import com.api.models.entities.BookingGym;
import com.api.models.entities.Member;
import com.api.models.entities.Pegawai;
import com.api.models.entities.PresensiGym;
import com.api.models.repos.BookingGymRepo;
import com.api.models.repos.GenerateRepo;
import com.api.models.repos.PresensiGymRepo;

@Service
public class BookingGymImpl {

    @Autowired
    private BookingGymRepo repo;

    @Autowired
    private MemberImpl memberImpl;

    @Autowired
    private PegawaiImpl pegawaiImpl;

    @Autowired
    private GenerateImpl generateImpl;

    @Autowired
    private PresensiGymImpl presensiGymImpl;

    public List<BookingGym> findAll() {
        return (List<BookingGym>) repo.findAllBookingGym();
    }

    public List<BookingGym> findAllById(String id) {
        Member db = memberImpl.findByIdMember(id);
        return (List<BookingGym>) repo.findAllBookingGymById(db);
    }

    // public List<BookingGymResponse> findAllByMember(String id) {
    // Member db = memberImpl.findByIdMember(id);
    // return (List<BookingGym>) repo.findAllBookingGymById(db);
    // }

    public List<BookingGym> findAllByCari(String id, String cari) {

        Member db = memberImpl.findByIdMember(id);
        if (cari.equals("cari")) {
            return (List<BookingGym>) repo.findAllBookingGymById(db);
        } else {
            return (List<BookingGym>) repo.findAllBookingGymByCari(db, cari);
        }
    }

    public Integer findSlotGym(String tgl, Integer sesi) {
        return repo.getSlotMember(tgl, sesi);
    }

    public BookingGym findBookingById(String id) {
        return repo.findById(id).get();
    }

    public BookingGym bookingGym(String id, BookingGym bookingGym) {
        Member memberDB = memberImpl.findByIdMember(id);
        BookingGym DB = new BookingGym();
        SimpleDateFormat formatter = new SimpleDateFormat("Y-MM-dd");
        DB.setMember(memberDB);
        DB.setStatus("S");
        DB.setTglBooking(bookingGym.getTglBooking());
        Date now = new Date();
        DB.setTglBuat(now);
        // Date date2 = new Date(tglBooking);
        // Date date2 = new Date(tahun, bulan, hari);
        DB.setSesi(bookingGym.getSesi());
        String generateString = RandomStringUtils.randomAlphanumeric(15);
        DB.setId(memberDB.getId() + "-bookGym-" + generateString);
        repo.save(DB);

        PresensiGym presensiGymDB = new PresensiGym();
        presensiGymDB.setMember(memberDB);
        presensiGymDB.setBookingGym(DB);
        presensiGymDB.setTglBooking(DB.getTglBooking());
        Pegawai pegawai = pegawaiImpl.findByIdPegawai("P02");
        presensiGymDB.setPegawai(pegawai);

        presensiGymDB.setMulaiGym(bookingGym.getSesi());
        presensiGymDB.setStatus("S");

        // id Presensi GYM
        DateFormat dateFormat = new SimpleDateFormat("YY.MM");
        Date date = new Date();
        String currentDateTime = dateFormat.format(date);

        Integer counter = generateImpl.findGenerateStruk(1);
        counter += 1;
        presensiGymDB.setId(currentDateTime + "." + counter);
        generateImpl.updateGenereteStruk(counter);

        presensiGymImpl.update(presensiGymDB);
        return DB;

    }

    public void deleteBookingGym(String id) {
        repo.deleteById(id);
    }

    public BookingGym update(BookingGym booking) {
        return repo.save(booking);
    }

    public BookingGym viewBooking(Integer sesi, String tgl, Member member) {
        return repo.cekBooking(sesi, tgl, member);
    }
}
