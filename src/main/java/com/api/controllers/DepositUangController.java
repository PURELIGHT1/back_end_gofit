package com.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.DepositUangRequest;
import com.api.implement.DepositUangImpl;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "https://gofitfitness.netlify.app/")
public class DepositUangController {

    @Autowired
    private DepositUangImpl impl;

    // @GetMapping(value = "/deposit_uang")
    // public ResponseEntity<Object> findAllDepositUang() {
    // return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
    // HttpStatus.OK,
    // impl.findAll());
    // }
    @GetMapping(value = "/deposit_uang")
    public ResponseEntity<Object> findAllDepositUang() {
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK,
                impl.findAll());
    }

    @PostMapping(value = "/deposit_uang", consumes = { "application/xml", "application/json" })
    public ResponseEntity<Object> createDepositUang(@RequestBody @Validated DepositUangRequest request) {
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK,
                impl.createTransaksi(request));
    }
}
