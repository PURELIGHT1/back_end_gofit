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

import com.api.dto.PresensiInstrukturRequest;
import com.api.implement.PresensiInstrukturImpl;
import com.api.models.entities.PresensiInstruktur;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class PresensiInstrukturController {

    @Autowired
    private PresensiInstrukturImpl impl;

    @GetMapping("/presensi_instruktur")
    public ResponseEntity<Object> findAllIzin() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.findAllIzin());
    }

    @GetMapping("/presensi_instruktur/filter")
    public ResponseEntity<Object> filterAllIzin() {

        return ResponseHandler.responseEntity("Berhasil filter data", HttpStatus.OK,
                impl.filterAllIzin());
    }

    @GetMapping("/presensi_instruktur/{id}")
    public ResponseEntity<Object> findAllIzin(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.findAllIzinById(id));
    }

    @PutMapping("/presensi_instruktur/{id}")
    public ResponseEntity<Object> konfirmasiPresensiIzin(@PathVariable("id") String id) {
        PresensiInstruktur presensiInstruktur = impl.findByIdPresensi(id);
        impl.konfirmasiIzin(id);
        return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                presensiInstruktur);
    }

    @PutMapping("/presensi_instruktur/tolak/{id}")
    public ResponseEntity<Object> tolakPresensiIzin(@PathVariable("id") String id) {
        PresensiInstruktur presensiInstruktur = impl.findByIdPresensi(id);
        impl.tolakIzin(id);
        return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                presensiInstruktur);
    }

    @PostMapping("/presensi_instruktur")
    public ResponseEntity<Object> createPresensiIzin(@RequestBody @Validated PresensiInstrukturRequest request) {
        return ResponseHandler.responseEntity("Berhasil menambah data", HttpStatus.CREATED,
                impl.createPresensiIzin(request));
    }
}
