package com.api.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
// import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ResponseData;
import com.api.implement.services.InstrukturService;
import com.api.implement.services.JadwalUmumService;
import com.api.implement.services.KelasService;
import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalUmum;
import com.api.models.entities.Kelas;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class JadwalUmumController {

    @Autowired
    private JadwalUmumService service;

    // @Autowired
    // private ModelMapper modelMapper;

    @Autowired
    private InstrukturService instrukturService;

    @Autowired
    private KelasService kelasService;

    // @PostMapping(value = "jadwal_umum", consumes = { "application/xml",
    // "application/json" })
    // public ResponseEntity<ResponseData<JadwalUmum>> createPromo(@Valid
    // @RequestBody JadwalUmumData jadwal,
    // Errors errors) {

    // ResponseData<JadwalUmum> responseData = new ResponseData<>();
    // if (errors.hasErrors()) {
    // for (ObjectError error : errors.getAllErrors()) {
    // responseData.getMessage().add(error.getDefaultMessage());
    // }

    // responseData.setMessageSucceses(null);
    // responseData.setStatus(false);
    // responseData.setData(null);

    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    // }

    // DateFormat dateFormat = new SimpleDateFormat("YY.MM");
    // Date date = new Date();
    // String currentDateTime = dateFormat.format(date);

    // JadwalUmum jadwalUmum = new JadwalUmum();
    // jadwalUmum.setTglJadwal(jadwal.getTglJadwal());
    // jadwalUmum.setHariJadwal(jadwal.getHariJadwal());
    // jadwalUmum.setSesiJadwal(jadwal.getSesiJadwal());
    // jadwalUmum.setStatus(true);

    // String generateString = RandomStringUtils.randomAlphanumeric(8);
    // jadwalUmum.setId("JU." + currentDateTime + "." + generateString);

    // responseData.setMessageSucceses("Berhasil menambah data");
    // responseData.setStatus(true);
    // responseData.setData(service.createJadwalUmum(jadwalUmum));

    // return ResponseEntity.ok(responseData);
    // }

    @PostMapping(value = "jadwal_umum/{id}/{id2}", consumes = { "application/xml",
            "application/json" })
    public ResponseEntity<ResponseData<JadwalUmum>> createPromo(@PathVariable("id") String idIns,
            @PathVariable("id2") Integer idKelas, @Valid @RequestBody JadwalUmum jadwal,
            Errors errors) {

        ResponseData<JadwalUmum> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        DateFormat dateFormat = new SimpleDateFormat("YY.MM");
        Date date = new Date();
        String currentDateTime = dateFormat.format(date);

        JadwalUmum jadwalUmum = new JadwalUmum();
        jadwalUmum.setHariJadwal(jadwal.getHariJadwal());
        jadwalUmum.setTglJadwal(jadwal.getTglJadwal());
        jadwalUmum.setSesiJadwal(jadwal.getSesiJadwal());

        String generateString = RandomStringUtils.randomAlphanumeric(8);
        jadwalUmum.setId("JU." + currentDateTime + "." + generateString);
        Instruktur instruktur = new Instruktur();
        instruktur = instrukturService.findByIdInstruktur(idIns);
        jadwalUmum.setInstruktur(instruktur);

        Kelas kelas = new Kelas();
        kelas = kelasService.findByIdKelas(idKelas);
        jadwalUmum.setKelas(kelas);

        jadwalUmum.setStatus("A");

        responseData.getMessage().add("Berhasil menambah Data");
        responseData.setStatus(true);
        responseData.setData(service.createJadwalUmum(jadwalUmum));

        return ResponseEntity.ok(responseData);
    }
}
