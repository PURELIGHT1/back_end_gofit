package com.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.implement.BookingKelasImpl;
import com.api.implement.JadwalHarianImpl;
import com.api.implement.services.InstrukturService;
import com.api.models.entities.BookingKelas;
import com.api.models.entities.GenerateTabel;
import com.api.models.entities.JadwalHarian;
import com.api.models.repos.GenerateRepo;
import com.api.models.repos.JadwalHarianRepo;
import com.api.util.ResponseHandler;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/mobile/")
@CrossOrigin(origins = "https://gofitfitness.netlify.app/")
public class ControllerMobile {

    @Autowired
    private JadwalHarianImpl impl;

    @Autowired
    private BookingKelasImpl bookingKelasImpl;

    @Autowired
    private GenerateRepo generateRepo;

    @Autowired
    private JadwalHarianRepo repo;

    @Autowired
    private InstrukturService instrukturService;

    @GetMapping("/jadwal_harian/find_today/{cari}")
    public ResponseEntity<Object> getJadwalHarianByDate(@PathVariable("cari") String cari) {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                impl.findAllByCariAndDate(cari));
    }

    @PutMapping(value = "jadwal_harian/presensi_awal/{idJadwal}")
    public ResponseEntity<Object> mulaiKelas(@PathVariable("idJadwal") String idJadwal) {
        return ResponseHandler.responseEntity("Berhasil mulai kelas",
                HttpStatus.OK,
                impl.mulaiKelas(idJadwal));
    }

    @PutMapping(value = "jadwal_harian/presensi_akhir/{idJadwal}")
    public ResponseEntity<Object> akhiriKelas(@PathVariable("idJadwal") String idJadwal) {
        return ResponseHandler.responseEntity("Berhasil akhiri kelas",
                HttpStatus.OK,
                impl.akhiriKelas(idJadwal));
    }

    @GetMapping("/instrukturs/{id}")
    public ResponseEntity<Object> getByIdInstruktur(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengambil data",
                HttpStatus.OK,
                instrukturService.findByIdInstruktur(id));
    }

}
