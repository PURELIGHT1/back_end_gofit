package com.api.controllers;

// import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.JadwalUmumRequest;
import com.api.implement.InstrukturImpl;
import com.api.implement.services.JadwalUmumService;
import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalUmum;
import com.api.models.repos.JadwalUmumRepo;
import com.api.util.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "https://gofitfitness.netlify.app/")
public class JadwalUmumController {

    @Autowired
    private JadwalUmumService service;

    @Autowired
    private JadwalUmumRepo repo;
    @Autowired
    private InstrukturImpl instrukturImpl;

    @GetMapping(value = "jadwal_umum")
    public ResponseEntity<Object> findAllJadwalHarian() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK, service.findJadwalUmum());
    }

    @GetMapping(value = "jadwal_umum/{id}")
    public ResponseEntity<Object> findJadwalHarianById(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK, service.findJadwalUmumById(id));
    }

    @PostMapping(value = "jadwal_umum", consumes = { "application/xml", "application/json" })
    public ResponseEntity<Object> addJadwalPromo(@Valid @RequestBody JadwalUmumRequest req) {
        Instruktur instrukturDB = instrukturImpl.findByIdInstruktur(req.getInstruktur());
        JadwalUmum jadwalUmumDB = repo.findAllJadwalKosong(req.getHariJadwal(), req.getSesiJadwal(), instrukturDB);
        if (jadwalUmumDB == null) {
            return ResponseHandler.responseEntity("Berhasil menambah data", HttpStatus.CREATED,
                    service.createJadwalUmum(req));
        } else {
            return ResponseHandler.responseEntity("Gagal menambah data", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping(value = "jadwal_umum/{id}", consumes = { "application/xml", "application/json" })
    public ResponseEntity<Object> updateJadwalPromo(@PathVariable("id") String id,
            @Valid @RequestBody JadwalUmumRequest req) {
        Instruktur instrukturDB = instrukturImpl.findByIdInstruktur(req.getInstruktur());
        JadwalUmum jadwalUmumTersedia = repo.findAllJadwalKosong(req.getHariJadwal(), req.getSesiJadwal(),
                instrukturDB);
        if (jadwalUmumTersedia == null) {
            return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                    service.updateJadwalUmum(id, req));
        } else {
            return ResponseHandler.responseEntity("Gagal menambah data", HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping(value = "/jadwal_umum/{id}")
    public ResponseEntity<Object> deleteJadwalHarian(@PathVariable("id") String id) {

        JadwalUmum jadwalHarian = service.findJadwalUmumById(id);
        service.deleteJadwalUmum(id);
        return ResponseHandler.responseEntity("Berhasil hapus data",
                HttpStatus.ACCEPTED,
                jadwalHarian);

    }
}
