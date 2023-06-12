package com.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.PresensiGymRequest;
import com.api.implement.PresensiGymImpl;
import com.api.models.entities.Kelas;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class PresensiGymController {

    @Autowired
    private PresensiGymImpl impl;

    @GetMapping("/presensi_gym")
    public ResponseEntity<Object> findAllPresensi() {

        // List<PresensiGymBookingResponse> response = impl.findAllPresensiBooking();
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.findAll());
    }

    @PostMapping("/presensi_gym")
    public ResponseEntity<Object> tambahPresensiGym(@RequestBody @Validated PresensiGymRequest req) {

        // List<PresensiGymBookingResponse> response = impl.findAllPresensiBooking();
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.createPresensi(req));
    }

    @PutMapping("/presensi_gym/{id}")
    public ResponseEntity<Object> ubahKelas(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                impl.updateDataPresensi(id));
    }
}
