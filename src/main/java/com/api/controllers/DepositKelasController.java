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

import com.api.dto.DepositKelasRequest;
import com.api.implement.DepositKelasImpl;
import com.api.implement.KelasImpl;
import com.api.implement.MemberImpl;
import com.api.models.entities.Kelas;
import com.api.models.entities.Member;
import com.api.models.entities.TransaksiDepositKelas;
import com.api.models.repos.DepositKelasRepo;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class DepositKelasController {
    @Autowired
    private DepositKelasImpl impl;

    @Autowired
    private DepositKelasRepo repo;

    @Autowired
    private KelasImpl kelasImpl;

    @Autowired
    private MemberImpl memberImpl;

    @GetMapping("/deposit_kelas")
    public ResponseEntity<Object> findAllDepositKelas() {
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK,
                impl.findAll());
    }

    @PostMapping(value = "/deposit_kelas", consumes = { "application/xml", "application/json" })
    public ResponseEntity<Object> createDepositUang(@RequestBody @Validated DepositKelasRequest request) {

        Kelas kelasDB = kelasImpl.findByIdKelas(request.getKelas());
        Integer totalDeposit = request.getTotalKelas() * kelasDB.getHarga();
        Member memberDB = memberImpl.findByIdMember(request.getMember());
        TransaksiDepositKelas cekMember = repo.cekTransaksiDepositKelas(memberDB);
        if (cekMember == null) {
            return ResponseHandler.responseEntity("Berhasil menambah data",
                    HttpStatus.OK,
                    impl.createTransaksi(request));
        } else {
            if (cekMember.getSisaKelas() == 0) {
                if (totalDeposit > memberDB.getSisaDeposit()) {
                    return ResponseHandler.responseEntity("Deposit tidak mencukupi",
                            HttpStatus.BAD_REQUEST,
                            null);
                } else {
                    return ResponseHandler.responseEntity("Berhasil menambah data",
                            HttpStatus.OK,
                            impl.createTransaksi(request));
                }
            } else {
                return ResponseHandler.responseEntity("Sisa paket sebelumnya belum habis",
                        HttpStatus.FORBIDDEN,
                        null);
            }
        }
    }
}
