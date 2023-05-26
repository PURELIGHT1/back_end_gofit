package com.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ExportKelasBulananResponse;
import com.api.dto.ExportPendapatanResponse;
import com.api.dto.LaporanInstrukturResponse;
import com.api.implement.InstrukturImpl;
import com.api.implement.JadwalHarianImpl;
import com.api.implement.KelasImpl;
import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalHarian;
import com.api.models.entities.Kelas;
import com.api.models.entities.TransaksiAktivasi;
import com.api.models.repos.BookingKelasRepo;
import com.api.models.repos.DepositUangRepo;
import com.api.models.repos.TransaksiAktivasiRepo;
import com.api.util.ResponseHandler;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5173/")
public class LaporanController {

    @Autowired
    private TransaksiAktivasiRepo tARepo;

    @Autowired
    private DepositUangRepo dURepo;

    @Autowired
    private KelasImpl kelasImpl;

    @Autowired
    private InstrukturImpl instrukturImpl;

    @Autowired
    private JadwalHarianImpl jadwalHarianImpl;

    @Autowired
    private BookingKelasRepo bookingKelasRepo;

    @GetMapping("/laporan/pendapatan")
    public ResponseEntity<Object> findAllProfile() {

        // Integer ta = tARepo.findLaporanAktivasi(bulan, tahun);
        List<Integer> ta = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            if (tARepo.findLaporanAktivasi(i, 2023) == null && dURepo.findLaporanAktivasi(i, 2023) != null) {
                Integer total = 0 + dURepo.findLaporanAktivasi(i, 2023);
                ta.add(total);
                continue;

            } else if (tARepo.findLaporanAktivasi(i, 2023) != null && dURepo.findLaporanAktivasi(i, 2023) == null) {
                Integer total = tARepo.findLaporanAktivasi(i, 2023) + 0;
                ta.add(total);
                continue;

            } else if (tARepo.findLaporanAktivasi(i, 2023) == null && dURepo.findLaporanAktivasi(i, 2023) == null) {
                ta.add(0);
                continue;

            } else {
                Integer total = tARepo.findLaporanAktivasi(i, 2023) + dURepo.findLaporanAktivasi(i, 2023);
                ta.add(total);
                continue;
            }
        }
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK, ta);
    }

    @GetMapping("/laporan/instruktur")
    public ResponseEntity<Object> findAllInstruktur() {

        // Integer ta = tARepo.findLaporanAktivasi(bulan, tahun);
        List<LaporanInstrukturResponse> list = new ArrayList<>();
        List<Instruktur> instrukturs = instrukturImpl.findAllAsc();
        for (int i = 0; i < instrukturs.size(); i++) {
            LaporanInstrukturResponse li = new LaporanInstrukturResponse();
            li.setNama(instrukturs.get(i).getInisial());
            li.setHadir(instrukturs.get(i).getJlhHadir());
            li.setLibur(instrukturs.get(i).getJlhLibur());
            Integer telat = instrukturs.get(i).getJlhTerlambat() * 60;
            li.setTerlambat(telat);
            list.add(li);
        }
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK, list);
    }

    @GetMapping("/laporan/pendapatan/export")
    public ResponseEntity<Object> findAllPendapatan() {

        // Integer ta = tARepo.findLaporanAktivasi(bulan, tahun);
        List<ExportPendapatanResponse> ta = new ArrayList<ExportPendapatanResponse>();
        for (int i = 1; i <= 12; i++) {
            ExportPendapatanResponse epr = new ExportPendapatanResponse();
            if (i == 1) {
                epr.setBulan("Januari");
            } else if (i == 2) {
                epr.setBulan("Februari");
            } else if (i == 3) {
                epr.setBulan("Maret");
            } else if (i == 4) {
                epr.setBulan("April");
            } else if (i == 5) {
                epr.setBulan("Mei");
            } else if (i == 6) {
                epr.setBulan("Juni");
            } else if (i == 7) {
                epr.setBulan("Juli");
            } else if (i == 8) {
                epr.setBulan("Agustus");
            } else if (i == 9) {
                epr.setBulan("September");
            } else if (i == 10) {
                epr.setBulan("Oktober");
            } else if (i == 11) {
                epr.setBulan("November");
            } else if (i == 12) {
                epr.setBulan("Desember");
            }

            if (tARepo.findLaporanAktivasi(i, 2023) == null && dURepo.findLaporanAktivasi(i, 2023) != null) {
                // Integer total = 0 + dURepo.findLaporanAktivasi(i, 2023);
                epr.setAktivasi(0);
                epr.setDeposit(dURepo.findLaporanAktivasi(i, 2023));

            } else if (tARepo.findLaporanAktivasi(i, 2023) != null && dURepo.findLaporanAktivasi(i, 2023) == null) {
                // Integer total = tARepo.findLaporanAktivasi(i, 2023) + 0;
                epr.setAktivasi(tARepo.findLaporanAktivasi(i, 2023));
                epr.setDeposit(0);

            } else if (tARepo.findLaporanAktivasi(i, 2023) == null && dURepo.findLaporanAktivasi(i, 2023) == null) {
                epr.setAktivasi(0);
                epr.setDeposit(0);

            } else {
                // Integer total = tARepo.findLaporanAktivasi(i, 2023) +
                // dURepo.findLaporanAktivasi(i, 2023);
                epr.setAktivasi(tARepo.findLaporanAktivasi(i, 2023));
                epr.setDeposit(dURepo.findLaporanAktivasi(i, 2023));
            }
            ta.add(epr);
        }
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK, ta);
    }

    @GetMapping("/laporan/kelas/export")
    public ResponseEntity<Object> findAllKelas() {

        // Integer ta = tARepo.findLaporanAktivasi(bulan, tahun);
        List<ExportKelasBulananResponse> kb = new ArrayList<>();
        List<Kelas> kelas = kelasImpl.findAll();
        for (int i = 0; i < kelas.size(); i++) {
            ExportKelasBulananResponse ekbr = new ExportKelasBulananResponse();
            ekbr.setKelas(kelas.get(i).getNama());
            JadwalHarian jadwalHarian = jadwalHarianImpl.findJadwalHarianIns(kelas.get(i).getId());
            if (jadwalHarian == null) {
                ekbr.setInstruktur("-");
            } else {
                Integer libur = jadwalHarianImpl.findJadwalHarianInsLibur(kelas.get(i).getId());
                if (libur == null) {
                    libur = 0;
                } else {
                    ekbr.setLibur(libur);
                    Integer peserta = bookingKelasRepo.findAllMemberBooking(jadwalHarian);
                    if (peserta == null) {
                        peserta = 0;
                    } else {

                        ekbr.setPeserta(peserta);
                    }
                }

                ekbr.setInstruktur(jadwalHarian.getInstruktur().getNama());
            }
            kb.add(ekbr);
        }
        return ResponseHandler.responseEntity("Berhasil mengambil seluruh data",
                HttpStatus.OK, kb);
    }
}
