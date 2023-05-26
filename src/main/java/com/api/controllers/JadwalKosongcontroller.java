package com.api.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.models.repos.JadwalKosongRepo;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class JadwalKosongcontroller {
    @Autowired
    private JadwalKosongRepo repo;

    @GetMapping(value = "jadwal_harian/{awal}")
    public ResponseEntity<Object> findAllJadwalKosong(@PathVariable("awal") LocalDate awal) {

        // List<JadwalHarian> jadwal =
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK,
                repo.findAllJadwalKosong(awal));
    }

}
