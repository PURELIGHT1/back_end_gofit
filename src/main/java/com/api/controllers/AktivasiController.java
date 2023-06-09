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

import com.api.dto.TransaksiAktivasiRequest;
import com.api.implement.TransaksiAktivasiImpl;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "https://gofitfitness.netlify.app/")
public class AktivasiController {

    @Autowired
    private TransaksiAktivasiImpl impl;

    @GetMapping("/aktivasi_tahunan")
    public ResponseEntity<Object> findAllAktivasi() {
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK,
                impl.findAllAktivasi());
    }

    // @GetMapping("/aktivasi_tahunan/status_aktivasi")
    // public ResponseEntity<Object> findAllMemberNonAktivasi() {
    // return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
    // HttpStatus.OK,
    // impl.findAllNonAktivasi());
    // }

    @PutMapping("/aktivasi_tahunan/{id}")
    public ResponseEntity<Object> updateAktivasi(@PathVariable("id") String id) {
        return ResponseHandler.responseEntity("Berhasil mengubah data",
                HttpStatus.OK,
                impl.updateTransaksiAktivasi(id));
    }

    @PostMapping("/aktivasi_tahunan")
    public ResponseEntity<Object> createAktivasi(@RequestBody @Validated TransaksiAktivasiRequest request) {
        return ResponseHandler.responseEntity("Berhasil menambah data",
                HttpStatus.OK,
                impl.createTransaksiAktivasi(request));
    }
}