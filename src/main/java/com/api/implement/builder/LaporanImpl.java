package com.api.implement.builder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ExportPendapatanResponse;
import com.api.dto.ExportResponseKelasBulanan;
import com.api.dto.ResponseGymBulanan;
import com.api.dto.ResponseKelasBulanan;
import com.api.implement.JadwalHarianImpl;
import com.api.implement.KelasImpl;
import com.api.models.entities.JadwalHarian;
import com.api.models.entities.Kelas;
import com.api.models.repos.BookingKelasRepo;
import com.api.models.repos.DepositUangRepo;
import com.api.models.repos.JadwalHarianRepo;
import com.api.models.repos.TransaksiAktivasiRepo;

@Service
public class LaporanImpl {
    @Autowired
    private TransaksiAktivasiRepo tARepo;

    @Autowired
    private DepositUangRepo dURepo;

    @Autowired
    private KelasImpl kelasImpl;

    @Autowired
    private JadwalHarianImpl jadwalHarianImpl;

    @Autowired
    private JadwalHarianRepo jHRepo;

    @Autowired
    private BookingKelasRepo bKRepo;

    public List<Integer> pendapatanPerTahun(Integer tahun) {
        List<Integer> p = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        ZoneId z = ZoneId.of("Asia/Jakarta");
        LocalDateTime now = LocalDateTime.now(z);
        if (tahun == 0) {
            tahun = Integer.parseInt(dtf.format(now));
        }

        for (int i = 1; i <= 12; i++) {
            if (tARepo.findLaporanPendapatan(i, tahun) == null && dURepo.findLaporanPendapatan(i, tahun) != null) {
                Integer total = 0 + dURepo.findLaporanPendapatan(i, tahun);
                p.add(total);
                continue;

            } else if (tARepo.findLaporanPendapatan(i, tahun) != null
                    && dURepo.findLaporanPendapatan(i, tahun) == null) {
                Integer total = tARepo.findLaporanPendapatan(i, tahun) + 0;
                p.add(total);
                continue;

            } else if (tARepo.findLaporanPendapatan(i, tahun) == null
                    && dURepo.findLaporanPendapatan(i, tahun) == null) {
                p.add(0);
                continue;

            } else {
                Integer total = tARepo.findLaporanPendapatan(i, tahun) + dURepo.findLaporanPendapatan(i, tahun);
                p.add(total);
                continue;
            }
        }
        return p;
    }

    public List<Integer> aktivasiPerTahun(Integer tahun) {
        List<Integer> p = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        ZoneId z = ZoneId.of("Asia/Jakarta");
        LocalDateTime now = LocalDateTime.now(z);
        if (tahun == 0) {
            tahun = Integer.parseInt(dtf.format(now));
        }

        for (int i = 1; i <= 12; i++) {
            if (tARepo.findLaporanPendapatan(i, tahun) == null) {
                p.add(0);
                continue;
            } else {
                Integer total = tARepo.findLaporanPendapatan(i, tahun);
                p.add(total);
                continue;
            }
        }
        return p;
    }

    public List<Integer> depositPerTahun(Integer tahun) {
        List<Integer> p = new ArrayList<>();

        // Integer year = 2023;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        ZoneId z = ZoneId.of("Asia/Jakarta");
        LocalDateTime now = LocalDateTime.now(z);
        if (tahun == 0) {
            tahun = Integer.parseInt(dtf.format(now));
        }

        for (int i = 1; i <= 12; i++) {
            if (dURepo.findLaporanPendapatan(i, tahun) == null) {
                p.add(0);
                continue;
            } else {
                p.add(dURepo.findLaporanPendapatan(i, tahun));
                continue;
            }
        }
        return p;
    }

    public List<ExportPendapatanResponse> pendapatanPerBulan(Integer tahun) {
        List<ExportPendapatanResponse> p = new ArrayList<ExportPendapatanResponse>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        ZoneId z = ZoneId.of("Asia/Jakarta");
        LocalDateTime now = LocalDateTime.now(z);
        if (tahun == 0) {
            tahun = Integer.parseInt(dtf.format(now));
        }

        for (int i = 1; i <= 12; i++) {
            ExportPendapatanResponse export = new ExportPendapatanResponse();
            if (i == 1) {
                export.setBulan("Januari");
            } else if (i == 2) {
                export.setBulan("Februari");
            } else if (i == 3) {
                export.setBulan("Maret");
            } else if (i == 4) {
                export.setBulan("April");
            } else if (i == 5) {
                export.setBulan("Mei");
            } else if (i == 6) {
                export.setBulan("Juni");
            } else if (i == 7) {
                export.setBulan("Juli");
            } else if (i == 8) {
                export.setBulan("Agustus");
            } else if (i == 9) {
                export.setBulan("September");
            } else if (i == 10) {
                export.setBulan("Oktober");
            } else if (i == 11) {
                export.setBulan("November");
            } else if (i == 12) {
                export.setBulan("Desember");
            }

            if (tARepo.findLaporanPendapatan(i, tahun) == null && dURepo.findLaporanPendapatan(i, tahun) != null) {
                export.setAktivasi(0);
                export.setDeposit(dURepo.findLaporanPendapatan(i, tahun));

            } else if (tARepo.findLaporanPendapatan(i, tahun) != null
                    && dURepo.findLaporanPendapatan(i, tahun) == null) {
                export.setAktivasi(tARepo.findLaporanPendapatan(i, tahun));
                export.setDeposit(0);

            } else if (tARepo.findLaporanPendapatan(i, tahun) == null
                    && dURepo.findLaporanPendapatan(i, tahun) == null) {
                export.setAktivasi(0);
                export.setDeposit(0);

            } else {
                export.setAktivasi(tARepo.findLaporanPendapatan(i, tahun));
                export.setDeposit(dURepo.findLaporanPendapatan(i, tahun));
            }
            p.add(export);
        }
        return p;
    }

    public ResponseKelasBulanan kelasPerBulan(Integer bulan, Integer tahun) {
        List<Kelas> listKelasDB = kelasImpl.findAllASC();

        ResponseKelasBulanan response = new ResponseKelasBulanan();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM");

        ZoneId z = ZoneId.of("Asia/Jakarta");
        LocalDateTime tahunCounter = LocalDateTime.now(z);

        if (tahun == 0) {
            tahun = Integer.parseInt(dtf.format(tahunCounter));
        }

        if (bulan == 0) {
            bulan = Integer.parseInt(dtf2.format(tahunCounter));
        }
        List<String> responseKelas = new ArrayList<>();
        List<Integer> responseJP = new ArrayList<>();
        List<List<Integer>> responsePeserta = new ArrayList<>();
        List<Integer> responseLibur = new ArrayList<>();
        List<String> responseIns = new ArrayList<>();

        for (int i = 0; i < listKelasDB.size(); i++) {
            Kelas kelas = listKelasDB.get(i);
            responseKelas.add(kelas.getNama());

            Integer libur = jHRepo.getTotalLibur(kelas.getId(), bulan, tahun);
            responseLibur.add(libur);

            List<String> newArr1 = new ArrayList<>();
            List<JadwalHarian> listJH = jHRepo.findJadwalKelas(kelas.getId(), bulan, tahun);

            for (int j = 0; j < listJH.size(); j++) {
                JadwalHarian jh = listJH.get(j);
                newArr1.add(jh.getId());
            }

            List<Integer> newArr2 = new ArrayList<>();
            for (int k = 0; k < newArr1.size(); k++) {
                JadwalHarian counter = jadwalHarianImpl.findJadwalHarianById(newArr1.get(k));
                Integer peserta = bKRepo.findAllMemberBooking(counter);
                newArr2.add(peserta);
            }

            JadwalHarian JHInsAsc = jHRepo.findJadwalKelasInsASC(kelas.getId()).get(0);
            responsePeserta.add(newArr2);
            responseIns.add(JHInsAsc.getInstruktur().getInisial());
        }

        for (int l = 0; l < responsePeserta.size(); l++) {
            List<Integer> listInt = responsePeserta.get(l);

            int total = 0;
            for (int m = 0; m < listInt.size(); m++) {
                total += listInt.get(m);
            }
            responseJP.add(total);

        }
        response.setKelas(responseKelas);
        response.setJlhPeserta(responseJP);
        response.setLibur(responseLibur);
        response.setIns(responseIns);
        return response;
    }

    public List<ExportResponseKelasBulanan> exportKelasPerBulan(Integer bulan,
            Integer tahun) {
        ResponseKelasBulanan expo = kelasPerBulan(bulan, tahun);

        List<ExportResponseKelasBulanan> list = new ArrayList<>();

        List<String> kelas = expo.getKelas();
        List<Integer> jlhPeserta = expo.getJlhPeserta();
        List<Integer> jlhLibur = expo.getLibur();
        List<String> ins = expo.getIns();

        for (int i = 0; i < kelas.size(); i++) {
            ExportResponseKelasBulanan kb = new ExportResponseKelasBulanan();
            kb.setKelas(kelas.get(i));
            kb.setInstruktur(ins.get(i));
            kb.setPeserta(jlhPeserta.get(i));
            kb.setLibur(jlhLibur.get(i));
            list.add(kb);
        }

        return list;
    }

    public ResponseGymBulanan GymPerBulan(Integer bulan, Integer tahun) {
        // public ResponseGymBulanan GymPerBulan(Integer bulan, Integer tahun) {
        ResponseGymBulanan response = new ResponseGymBulanan();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM");

        ZoneId z = ZoneId.of("Asia/Jakarta");
        LocalDateTime tahunCounter = LocalDateTime.now(z);

        if (tahun == 0) {
            tahun = Integer.parseInt(dtf.format(tahunCounter));
        }

        if (bulan == 0) {
            bulan = Integer.parseInt(dtf2.format(tahunCounter));
        }

        List<Date> responseTgl = new ArrayList<>();
        List<Integer> responseMember = new ArrayList<>();

        LocalDate ld = LocalDate.of(tahun, bulan, 1);
        if (bulan == 12) {
            LocalDate ld2 = LocalDate.of(tahun + 1, 1, 1);

            Date counter = Date.from(ld2.atStartOfDay(z).toInstant());

            for (int i = 1; i < 33; i++) {
                LocalDate ld3 = ld.plusDays(i);
                Date date = Date.from(ld3.atStartOfDay(z).toInstant());
                if (date.equals(counter)) {
                    break;
                }
                responseTgl.add(date);
            }
            responseTgl.add(counter);

            response.setTgl(responseTgl);
            response.setMember(responseMember);
        } else {
            LocalDate ld2 = LocalDate.of(tahun, bulan + 1, 1);

            Date counter = Date.from(ld2.atStartOfDay(z).toInstant());

            for (int i = 1; i < 33; i++) {
                LocalDate ld3 = ld.plusDays(i);
                Date date = Date.from(ld3.atStartOfDay(z).toInstant());
                if (date.equals(counter)) {
                    break;
                }
                responseTgl.add(date);
            }
            responseTgl.add(counter);

            response.setTgl(responseTgl);
            response.setMember(responseMember);
        }

        return response;
    }

    public List<ExportResponseKelasBulanan> exportGymPerBulan(Integer bulan,
            Integer tahun) {
        // ResponseKelasBulanan expo = GymPerBulan(bulan, tahun);

        List<ExportResponseKelasBulanan> list = new ArrayList<>();

        // List<String> kelas = expo.getKelas();
        // List<Integer> jlhPeserta = expo.getJlhPeserta();
        // List<Integer> jlhLibur = expo.getLibur();
        // List<String> ins = expo.getIns();

        // for (int i = 0; i < kelas.size(); i++) {
        // ExportResponseKelasBulanan kb = new ExportResponseKelasBulanan();

        // kb.setKelas(kelas.get(i));
        // kb.setInstruktur(ins.get(i));
        // kb.setPeserta(jlhPeserta.get(i));
        // kb.setLibur(jlhLibur.get(i));
        // list.add(kb);
        // }

        return list;
    }

}
