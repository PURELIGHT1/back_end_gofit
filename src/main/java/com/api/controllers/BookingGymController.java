package com.api.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.BookingGymRequest;
import com.api.implement.BookingGymImpl;
import com.api.implement.MemberImpl;
import com.api.models.entities.BookingGym;
import com.api.models.entities.Member;
import com.api.models.repos.BookingGymRepo;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class BookingGymController {

    @Autowired
    private BookingGymImpl impl;

    @Autowired
    private MemberImpl memberImpl;

    @Autowired
    private BookingGymRepo repo;

    @GetMapping("/booking_gym")
    public ResponseEntity<Object> findAllBooking() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.findAll());
    }

    // @GetMapping("/booking_gym/{id}")
    // public ResponseEntity<Object> findAllBookingById(@PathVariable("id") String
    // id) {

    // if (memberImpl.findByIdMemberAktif(id) == null) {
    // return ResponseEntity.badRequest().body(null);
    // } else {
    // return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
    // HttpStatus.OK,
    // impl.findById(id));
    // }
    // }

    @GetMapping("/booking_gym/cari/{id}")
    public ResponseEntity<Object> findBookingById(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                impl.findBookingById(id));

    }

    @GetMapping("/booking_gym/slot/{tgl}/{sesi}")
    public ResponseEntity<Object> findSlotGym(@PathVariable("tgl") String tgl, @PathVariable("sesi") Integer sesi) {
        // return ResponseEntity.ok().body(impl.findSlotGym(tgl, sesi));
        return ResponseHandler.responseEntity("Berhasil mengambil data",
                HttpStatus.OK,
                impl.findSlotGym(tgl, sesi));

    }

    @GetMapping("/booking_gym/find/{id}/{cari}")
    public ResponseEntity<Object> findBookingByCari(@PathVariable("id") String id, @PathVariable("cari") String cari) {

        if (memberImpl.findByIdMemberAktif(id) == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            // if (cari.equals("")) {
            // return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
            // HttpStatus.OK,
            // impl.findAllById(id));

            // } else {

            return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                    impl.findAllByCari(id, cari));
            // }
            // if(cari == null){

            // }
            // List<BookingGym> list = impl.findAllByCari(id, cari);
            // if (list.size() <= 0) {
            // return ResponseEntity.notFound().build();
            // } else {
            // }
        }
    }

    // @GetMapping("/booking_gym/find/{id}")
    // public ResponseEntity<Object> findBookingById(@PathVariable("id") String id)
    // {

    // return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
    // HttpStatus.OK,
    // impl.findById(id));

    // }

    @PutMapping(value = "/booking_gym/{id}")
    public ResponseEntity<Object> addBookingGym(@PathVariable("id") String id,
            @RequestBody @Validated BookingGym req) {
        if (memberImpl.findByIdMemberAktif(id) == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            Member member = new Member();
            Member memberDB = memberImpl.findByIdMember(id);
            BookingGym DB = impl.viewBooking(req.getSesi(), req.getTglBooking(), memberDB);
            if (DB == null) {
                // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                // String currentDateTime = dateFormat.format(req.getTglBooking());
                Integer slot = repo.getSlotMember(req.getTglBooking(), req.getSesi());
                if (slot >= 10) {
                    return ResponseEntity.status(501).build();
                } else {
                    return ResponseHandler.responseEntity("Berhasil booking gym", HttpStatus.CREATED,
                            impl.bookingGym(id, req));
                }
            } else {
                return ResponseEntity.status(500).build();
            }
        }
    }

    @DeleteMapping(value = "/booking_gym/{id}")
    public ResponseEntity<Object> deleteBookingGym(@PathVariable("id") String id) {
        try {

            BookingGym DB = impl.findBookingById(id);
            String cek = DB.getTglBooking();
            LocalDate tanggalSekarang = LocalDate.now();
            LocalDate booking = LocalDate.parse(cek);
            LocalDate satuHariSebelum = booking.plusDays(-1);
            if (satuHariSebelum.compareTo(tanggalSekarang) >= 0) {

                impl.deleteBookingGym(id);
                return ResponseHandler.responseEntity("Berhasil hapus booking", HttpStatus.OK,
                        DB);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.getStackTrace();
            return ResponseEntity.status(500).build();
        }
        // Date date = new Date();
        // Calendar cal = Calendar.getInstance();
        // cal.setTime(date);
        // cal.add(Calendar.DATE, -1);
        // String currentDateTime = dateFormat.format(date);
    }

}
