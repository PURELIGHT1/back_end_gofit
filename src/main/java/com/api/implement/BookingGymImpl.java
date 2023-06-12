package com.api.implement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.BookingGymResponse;
import com.api.dto.ResponseSelect;
import com.api.implement.builder.GenerateImpl;
import com.api.models.entities.BookingGym;
import com.api.models.entities.Instruktur;
import com.api.models.entities.Member;
import com.api.models.entities.Pegawai;
import com.api.models.entities.PresensiGym;
import com.api.models.repos.BookingGymRepo;

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

    public List<ResponseSelect> findAllBookingToday() {
        List<ResponseSelect> list = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("Y-MM-dd");
        ZoneId z = ZoneId.of("Asia/Jakarta");
        LocalDateTime now = LocalDateTime.now(z);
        String today = dtf.format(now);

        List<BookingGym> bookingGym = repo.findAllBookingGymToday(today);
        bookingGym.forEach(i -> {
            ResponseSelect responseSelect = new ResponseSelect();
            if (i.getSesi() == 1) {
                responseSelect.setLabel(i.getMember().getNama() + ", 07.00 - 09.00 WIB");
            } else if (i.getSesi() == 2) {
                responseSelect.setLabel(i.getMember().getNama() + ", 09.00 - 11.00 WIB");
            } else if (i.getSesi() == 3) {
                responseSelect.setLabel(i.getMember().getNama() + ", 11.00 - 13.00 WIB");
            } else if (i.getSesi() == 4) {
                responseSelect.setLabel(i.getMember().getNama() + ", 13.00 - 15.00 WIB");
            } else if (i.getSesi() == 5) {
                responseSelect.setLabel(i.getMember().getNama() + ", 15.00 - 17.00 WIB");
            } else if (i.getSesi() == 6) {
                responseSelect.setLabel(i.getMember().getNama() + ", 17.00 - 19.00 WIB");
            } else if (i.getSesi() == 2) {
                responseSelect.setLabel(i.getMember().getNama() + ", 19.00 - 21.00 WIB");
            }
            responseSelect.setValue(i.getId());
            list.add(responseSelect);
        });
        return list;
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
        // SimpleDateFormat formatter = new SimpleDateFormat("Y-MM-dd");
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
        // presensiGymDB.setMember(memberDB);
        presensiGymDB.setBookingGym(DB);
        // presensiGymDB.setTglBooking(DB.getTglBooking());
        Pegawai pegawai = pegawaiImpl.findByIdPegawai("P02");
        presensiGymDB.setPegawai(pegawai);

        // presensiGymDB.setMulaiGym(bookingGym.getSesi());
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
