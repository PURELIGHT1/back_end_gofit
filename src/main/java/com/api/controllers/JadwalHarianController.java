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

import com.api.implement.JadwalHarianImpl;
import com.api.models.entities.GenerateTabel;
import com.api.models.entities.JadwalHarian;
import com.api.models.repos.GenerateRepo;
import com.api.models.repos.JadwalHarianRepo;
import com.api.util.ResponseHandler;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class JadwalHarianController {

    @Autowired
    private JadwalHarianImpl impl;

    @Autowired
    private GenerateRepo generateRepo;

    @Autowired
    private JadwalHarianRepo repo;

    @GetMapping(value = "jadwal_harian")
    public ResponseEntity<Object> findAllJadwalHarian() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK,
                impl.findAllJadwalHarian());
    }

    @GetMapping(value = "jadwal_harian/{awal}/{akhir}")
    public ResponseEntity<Object> findAllJadwalHarian(@PathVariable("awal") LocalDate awal,
            @PathVariable("akhir") LocalDate akhir) {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK,
                impl.findAllJadwalHarianByDate(awal, akhir));
    }

    @GetMapping("/jadwal_harian/find/{nama}")
    public ResponseEntity<Object> getByNamaMember(@PathVariable("nama") String nama) {
        List<JadwalHarian> jadwalHarianDB = repo.findByNama(nama);
        if (jadwalHarianDB.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseHandler.responseEntity("Berhasil mengambil data",
                HttpStatus.OK,
                jadwalHarianDB);
    }

    @DeleteMapping(value = "/jadwal_harian/{id}")
    public ResponseEntity<Object> deleteJadwalHarian(@PathVariable("id") String id) {

        JadwalHarian jadwalHarian = impl.findJadwalHarianById(id);
        impl.deleteJadwalHarian(id);
        return ResponseHandler.responseEntity("Berhasil hapus data",
                HttpStatus.ACCEPTED,
                jadwalHarian);

    }

    @PutMapping(value = "/jadwal_harian/libur/{id}")
    public ResponseEntity<Object> editJadwalHarian(@PathVariable("id") String id) {

        return ResponseHandler.responseEntity("Berhasil hapus data",
                HttpStatus.ACCEPTED,
                impl.editJadwalHarian(id));

    }

    @PutMapping(value = "jadwal_harian/generate")
    public ResponseEntity<Object> createJadwalHarian() {

        GenerateTabel generateTabel = new GenerateTabel(1, 1, 2, 1, 101, false);
        generateRepo.save(generateTabel);
        boolean statusGenerate = generateRepo.findgenerateJadwalByGenerateTabel(1);
        if (statusGenerate == true) {
            return ResponseHandler.responseEntity("Gagal menambahkan data, karena minggu ini sudah ada jadwal harian",
                    HttpStatus.BAD_REQUEST,
                    null);
        }
        return ResponseHandler.responseEntity("Berhasil generate data",
                HttpStatus.ACCEPTED,
                impl.createJadwalharian());
    }
}
