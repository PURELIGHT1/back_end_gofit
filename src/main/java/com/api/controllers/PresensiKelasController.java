package com.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.PresensiMemberCustomeResponse;
import com.api.implement.PresensiKelasImpl;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class PresensiKelasController {
    @Autowired
    private PresensiKelasImpl impl;

    @GetMapping("/presensi_instruktur/find_jadwal/{idIns}")
    public ResponseEntity<Object> findAllIzin(@PathVariable("idIns") String idIns) {

        List<PresensiMemberCustomeResponse> response = impl.findAllPresensiKelas(idIns);
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK, response);
    }

    @GetMapping("/presensi_kelas")
    public ResponseEntity<Object> findAllPresensi() {

        // List<PresensiGymBookingResponse> response = impl.findAllPresensiBooking();
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.findAll());
    }
}
