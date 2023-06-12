package com.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.implement.BookingKelasImpl;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "https://gofitfitness.netlify.app/")
public class BookingKelasController {
    @Autowired
    private BookingKelasImpl impl;

    @GetMapping("/booking_kelas")
    public ResponseEntity<Object> findAllBooking() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.findAll());
    }

    @GetMapping("/booking_kelas/{id}")
    public ResponseEntity<Object> findAllBookingMember(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.findAllByMember(id));
    }
}
