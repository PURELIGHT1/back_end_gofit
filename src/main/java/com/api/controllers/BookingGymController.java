package com.api.controllers;

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

import com.api.implement.BookingGymImpl;
import com.api.implement.MemberImpl;
import com.api.implement.PresensiGymImpl;
import com.api.implement.services.KelasService;
import com.api.models.entities.BookingGym;
import com.api.models.entities.Kelas;
import com.api.models.entities.Member;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class BookingGymController {

    @Autowired
    private BookingGymImpl impl;

    @Autowired
    private MemberImpl memberImpl;

    @GetMapping("/booking_gym")
    public ResponseEntity<Object> findAllPromo() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.findAll());
    }

    @PutMapping(value = "/booking_gym/{id}")
    public ResponseEntity<Object> bookingGym(@PathVariable("id") String id, @RequestBody @Validated BookingGym req) {
        if (memberImpl.findByIdMemberAktif(id).equals(null)) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseHandler.responseEntity("Berhasil booking gym", HttpStatus.CREATED,
                    impl.bookingGym(id));
        }
    }

}
