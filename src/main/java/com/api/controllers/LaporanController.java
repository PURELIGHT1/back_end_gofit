package com.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.implement.builder.LaporanImpl;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class LaporanController {

        @Autowired
        private LaporanImpl laporanImpl;

        @GetMapping("/laporan/pendapatan/{tahun}")
        public ResponseEntity<Object> findAllPendapatan(@PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.pendapatanPerTahun(tahun));
        }

        @GetMapping("/laporan/aktivasi/{tahun}")
        public ResponseEntity<Object> findAllAktivasi(@PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.aktivasiPerTahun(tahun));
        }

        @GetMapping("/laporan/deposit/{tahun}")
        public ResponseEntity<Object> findAllDeposit(@PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.depositPerTahun(tahun));
        }

        @GetMapping("/laporan/pendapatan/export/{tahun}")
        public ResponseEntity<Object> findPendapatanDeposit(@PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.pendapatanPerBulan(tahun));
        }

        @GetMapping("/laporan/kelas/{bulan}/{tahun}")
        public ResponseEntity<Object> findKelasBulanan(@PathVariable("bulan") Integer bulan,
                        @PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.kelasPerBulan(bulan, tahun));
        }

        @GetMapping("/export/kelas/{bulan}/{tahun}")
        public ResponseEntity<Object> exportKelasBulanan(@PathVariable("bulan") Integer bulan,
                        @PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.exportKelasPerBulan(bulan, tahun));
        }

        @GetMapping("/laporan/gym/{bulan}/{tahun}")
        public ResponseEntity<Object> findGymBulanan(@PathVariable("bulan") Integer bulan,
                        @PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.gymPerBulan(bulan, tahun));
        }

        @GetMapping("/export/gym/{bulan}/{tahun}")
        public ResponseEntity<Object> exportGymBulanan(@PathVariable("bulan") Integer bulan,
                        @PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.exportGymPerBulan(bulan, tahun));
        }

        @GetMapping("/laporan/kinerja/{bulan}/{tahun}")
        public ResponseEntity<Object> findKinerjaBulanan(@PathVariable("bulan") Integer bulan,
                        @PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.kinerjaInstrukturBulanan(bulan, tahun));
        }

        @GetMapping("/export/kinerja/{bulan}/{tahun}")
        public ResponseEntity<Object> exportKinerjaBulanan(@PathVariable("bulan") Integer bulan,
                        @PathVariable("tahun") Integer tahun) {
                return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                                HttpStatus.OK, laporanImpl.exportKinerjaPerBulan(bulan, tahun));
        }
}
