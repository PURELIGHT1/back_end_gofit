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

import com.api.implement.services.KelasService;
import com.api.models.entities.Kelas;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class KelasController {

    @Autowired
    private KelasService kelasService;

    @GetMapping("/kelas")
    public ResponseEntity<Object> findAllPromo() {

        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data", HttpStatus.OK,
                kelasService.findAll());
    }

    @GetMapping("/kelas/{id}")
    public ResponseEntity<Object> getByIdPromo(@PathVariable("id") Integer id) {

        return ResponseHandler.responseEntity("Berhasil mengambil data", HttpStatus.OK,
                kelasService.findByIdKelas(id));
    }

    @PostMapping(value = "kelas", consumes = { "application/xml", "application/json" })
    public ResponseEntity<Object> createPromo(@RequestBody @Validated Kelas kelas) {

        return ResponseHandler.responseEntity("Berhasil menambah data", HttpStatus.CREATED,
                kelasService.createKelas(kelas));
    }

    @PutMapping("/kelas/{id}")
    public ResponseEntity<Object> updatePromo(@PathVariable("id") Integer id,
            @RequestBody @Validated Kelas kelas) {

        return ResponseHandler.responseEntity("Berhasil mengubah data", HttpStatus.CREATED,
                kelasService.updateKelas(id, kelas));

    }

    @DeleteMapping("/kelas/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Integer id) {

        Kelas promoDB = kelasService.findByIdKelas(id);
        kelasService.deleteKelas(id);

        return ResponseHandler.responseEntity("Berhasil hapus data", HttpStatus.OK,
                promoDB);

    }

}
