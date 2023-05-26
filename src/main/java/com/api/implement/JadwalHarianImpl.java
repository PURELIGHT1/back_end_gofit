package com.api.implement;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.MulaiKelasResponse;
import com.api.models.entities.GenerateTabel;
import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalHarian;
import com.api.models.entities.Kelas;
import com.api.models.entities.PresensiInstruktur;
import com.api.models.repos.GenerateRepo;
import com.api.models.repos.JadwalHarianRepo;
import com.api.models.repos.PresensiInstrukturRepo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class JadwalHarianImpl {

    @Autowired
    private JadwalHarianRepo repo;

    @Autowired
    private InstrukturImpl instrukturImpl;

    @Autowired
    private KelasImpl kelasImpl;

    @Autowired
    private PresensiInstrukturRepo presensiInstrukturRepo;

    @Autowired
    private JadwalHarianRepo jadwalHarianRepo;

    @Autowired
    private GenerateRepo generateRepo;

    public List<JadwalHarian> findAllJadwalHarianByDate(Date awal, Date akhir) {
        DateFormat dateFormatCekTangal = new SimpleDateFormat("Y-MM-dd");
        String current = dateFormatCekTangal.format(awal);
        String next = dateFormatCekTangal.format(akhir);
        return (List<JadwalHarian>) repo.findJadwalSatuMinggu(current, next);
    }

    public List<JadwalHarian> findAllJadwalHarianIns(String id) {
        Instruktur instrukturDB = instrukturImpl.findByIdInstruktur(id);
        return (List<JadwalHarian>) repo.findJadwalInsAll(instrukturDB);
    }

    public JadwalHarian findJadwalHarianIns(Integer id) {
        return repo.findJadwalInsOne(id);
    }

    public Integer findJadwalHarianInsLibur(Integer id) {
        return repo.findJadwalInsOneLibur(id);
    }

    public List<JadwalHarian> findAllJadwalHarianKhususUmum() {
        Date cek = new Date();
        DateFormat dateFormat = new SimpleDateFormat("EEEEE");

        String hari = dateFormat.format(cek);

        if (hari.equals("Monday")) {
            Date awal = new Date();
            Date akhir = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, 6);
            akhir = c.getTime();

            DateFormat dateFormatCekTangal = new SimpleDateFormat("Y-MM-dd");
            String current = dateFormatCekTangal.format(awal);
            String next = dateFormatCekTangal.format(akhir);

            return (List<JadwalHarian>) repo.findJadwalSatuMinggu(current, next);
        } else if (hari.equals("Sunday")) {
            Date awal = new Date();
            Date akhir = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -6);
            awal = c.getTime();

            DateFormat dateFormatCekTangal = new SimpleDateFormat("Y-MM-dd");
            String current = dateFormatCekTangal.format(awal);
            String next = dateFormatCekTangal.format(akhir);

            return (List<JadwalHarian>) repo.findJadwalSatuMinggu(current, next);
        } else if (hari.equals("Tuesday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -1);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 5);
            akhir = c2.getTime();

            DateFormat dateFormatCekTangal = new SimpleDateFormat("Y-MM-dd");
            String current = dateFormatCekTangal.format(awal);
            String next = dateFormatCekTangal.format(akhir);

            return (List<JadwalHarian>) repo.findJadwalSatuMinggu(current, next);
        } else if (hari.equals("Wednesday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -2);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 4);
            akhir = c2.getTime();

            DateFormat dateFormatCekTangal = new SimpleDateFormat("Y-MM-dd");
            String current = dateFormatCekTangal.format(awal);
            String next = dateFormatCekTangal.format(akhir);

            return (List<JadwalHarian>) repo.findJadwalSatuMinggu(current, next);
        } else if (hari.equals("Thursday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -3);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 3);
            akhir = c2.getTime();

            DateFormat dateFormatCekTangal = new SimpleDateFormat("Y-MM-dd");
            String current = dateFormatCekTangal.format(awal);
            String next = dateFormatCekTangal.format(akhir);

            return (List<JadwalHarian>) repo.findJadwalSatuMinggu(current, next);
        } else if (hari.equals("Friday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -4);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 2);
            akhir = c2.getTime();

            DateFormat dateFormatCekTangal = new SimpleDateFormat("Y-MM-dd");
            String current = dateFormatCekTangal.format(awal);
            String next = dateFormatCekTangal.format(akhir);

            return (List<JadwalHarian>) repo.findJadwalSatuMinggu(current, next);
        } else if (hari.equals("Saturday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -5);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 1);
            akhir = c2.getTime();

            DateFormat dateFormatCekTangal = new SimpleDateFormat("Y-MM-dd");
            String current = dateFormatCekTangal.format(awal);
            String next = dateFormatCekTangal.format(akhir);

            return (List<JadwalHarian>) repo.findJadwalSatuMinggu(current, next);
        } else {
            return null;
        }
    }

    public List<JadwalHarian> findAllJadwalHarianByIns(String idIns) {
        Date cek = new Date();
        DateFormat dateFormat = new SimpleDateFormat("EEEEE");

        String hari = dateFormat.format(cek);

        if (hari.equals("Monday")) {
            Date awal = new Date();
            Date akhir = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, 6);
            akhir = c.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            // List<JadwalHarian> jadwalHarianDB = jadwalHarianRepo.findJadwalIns(ins);

            // for (int i = 0; i <= jadwalHarianDB.size(); i++) {
            // }

            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstruktur(awal, akhir, ins);
        } else if (hari.equals("Sunday")) {
            Date awal = new Date();
            Date akhir = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -6);
            awal = c.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstruktur(awal, akhir, ins);
        } else if (hari.equals("Tuesday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -1);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 5);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstruktur(awal, akhir, ins);
        } else if (hari.equals("Wednesday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -2);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 4);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstruktur(awal, akhir, ins);
        } else if (hari.equals("Thursday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -3);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 3);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstruktur(awal, akhir, ins);
        } else if (hari.equals("Friday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -4);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 2);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstruktur(awal, akhir, ins);
        } else if (hari.equals("Saturday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -5);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 1);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstruktur(awal, akhir, ins);
        } else {
            return null;
        }
    }

    public List<JadwalHarian> findAllJadwalHarianByInsPegganti(String idIns) {
        Date cek = new Date();
        DateFormat dateFormat = new SimpleDateFormat("EEEEE");

        String hari = dateFormat.format(cek);

        if (hari.equals("Monday")) {
            Date awal = new Date();
            Date akhir = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, 6);
            akhir = c.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstrukturPegganti(awal, akhir, ins);
        } else if (hari.equals("Sunday")) {
            Date awal = new Date();
            Date akhir = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -6);
            awal = c.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstrukturPegganti(awal, akhir, ins);
        } else if (hari.equals("Tuesday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -1);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 5);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstrukturPegganti(awal, akhir, ins);
        } else if (hari.equals("Wednesday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -2);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 4);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstrukturPegganti(awal, akhir, ins);
        } else if (hari.equals("Thursday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -3);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 3);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstrukturPegganti(awal, akhir, ins);
        } else if (hari.equals("Friday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -4);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 2);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstrukturPegganti(awal, akhir, ins);
        } else if (hari.equals("Saturday")) {
            Date awal = new Date();
            Date akhir = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(awal);
            c.add(Calendar.DATE, -5);
            awal = c.getTime();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(akhir);
            c2.add(Calendar.DATE, 1);
            akhir = c2.getTime();
            Instruktur ins = instrukturImpl.findByIdInstruktur(idIns);
            return (List<JadwalHarian>) repo.findJadwalSatuMingguByInstrukturPegganti(awal, akhir, ins);
        } else {
            return null;
        }
    }

    public List<JadwalHarian> findAllByCariAndDate(String cari) {

        DateFormat dateFormat = new SimpleDateFormat("EEEEE");
        Date date = new Date();
        DateFormat dateFormat2 = new SimpleDateFormat("y-MM-dd");
        String hari = dateFormat.format(date);
        String date2 = dateFormat2.format(date);
        if (hari.equals("Sunday")) {
            hari = "Minggu";
        } else if (hari.equals("Monday")) {
            hari = "Senin";
        } else if (hari.equals("Tuesday")) {
            hari = "Selasa";
        } else if (hari.equals("Wednesday")) {
            hari = "Rabu";
        } else if (hari.equals("Thursday")) {
            hari = "Kamis";
        } else if (hari.equals("Friday")) {
            hari = "Jumat";
        } else if (hari.equals("Saturday")) {
            hari = "Sabtu";
        }
        if (cari.equals("cari")) {
            return (List<JadwalHarian>) repo.findAllBookingGymByDate(date2, hari);
        } else {
            return (List<JadwalHarian>) repo.findAllBookingGymByDateAndCari(date2, hari, cari);
        }

    }

    public List<JadwalHarian> findAllJadwalHarian() {
        return repo.findAll();
    }

    public void deleteJadwalHarian(String id) {
        repo.deleteById(id);
    }

    public JadwalHarian findJadwalHarianById(String id) {
        return repo.findById(id).get();
    }

    public JadwalHarian editJadwalHarian(String id) {
        JadwalHarian jadwalHarianDB = findJadwalHarianById(id);
        jadwalHarianDB.setInstruktur(null);
        jadwalHarianDB.setInstrukturPeganti(null);
        jadwalHarianDB.setStatus("L");
        return repo.save(jadwalHarianDB);
    }

    public List<JadwalHarian> createJadwalharian() {
        List<JadwalHarian> list = new ArrayList<>();
        List<Instruktur> instrukturDB = instrukturImpl.findAll();
        List<Kelas> kelasDB = kelasImpl.findAll();

        // cek jadwal
        for (int i = 0; i < 7; i++) {
            Date tanggal = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(tanggal);
            c.add(Calendar.DATE, i + 1);
            tanggal = c.getTime();

            DateFormat dateFormat = new SimpleDateFormat("EEEEE");
            String hari = dateFormat.format(c.getTime());
            if (hari.equals("Sunday")) {
                hari = "Minggu";
            } else if (hari.equals("Monday")) {
                hari = "Senin";
            } else if (hari.equals("Tuesday")) {
                hari = "Selasa";
            } else if (hari.equals("Wednesday")) {
                hari = "Rabu";
            } else if (hari.equals("Thursday")) {
                hari = "Kamis";
            } else if (hari.equals("Friday")) {
                hari = "Jumat";
            } else if (hari.equals("Saturday")) {
                hari = "Sabtu";
            }

            for (int j = 0; j < 4; j++) {
                Integer sesi = j + 1;

                Calendar c2 = Calendar.getInstance();
                c2.setTime(tanggal);
                c2.add(Calendar.DATE, 7);
                Date akhir = c2.getTime();

                Random DiceIns = new Random();
                int m = DiceIns.nextInt(instrukturDB.size());
                Instruktur instruktur = instrukturDB.get(m);

                DateFormat dateFormatCekTangal = new SimpleDateFormat("Y-MM-dd");
                String current = dateFormatCekTangal.format(tanggal);
                String last = dateFormatCekTangal.format(akhir);

                JadwalHarian cekJH = repo.findJadwalHarianInstruktur(hari, sesi, instruktur, current, last);
                if (cekJH == null) {
                    DateFormat dateFormatID = new SimpleDateFormat("YY.MM");
                    Date date = new Date();
                    String currentDateTime = dateFormatID.format(date);

                    JadwalHarian jadwalHarianDB = new JadwalHarian();
                    String generateString = RandomStringUtils.randomAlphanumeric(11);
                    jadwalHarianDB.setId("JH." + currentDateTime + "." + generateString);

                    jadwalHarianDB.setInstruktur(instruktur);

                    Random DiceKelas = new Random();
                    int n = DiceKelas.nextInt(kelasDB.size());
                    jadwalHarianDB.setKelas(kelasDB.get(n));

                    jadwalHarianDB.setTglJadwal(current);
                    jadwalHarianDB.setHariJadwal(hari);
                    jadwalHarianDB.setSesiJadwal(sesi);
                    jadwalHarianDB.setStatus("S");
                    repo.save(jadwalHarianDB);
                    list.add(jadwalHarianDB);
                }
            }

        }
        // for (int i = 0; i < kelasDB.size(); i++) {
        // JadwalHarian jadwalHarianDB = new JadwalHarian();
        // String generateString = RandomStringUtils.randomAlphanumeric(15);
        // jadwalHarianDB.setId("JH_" + generateString);

        // Random Dice = new Random();
        // int m = Dice.nextInt(instrukturDB.size());
        // jadwalHarianDB.setInstruktur(instrukturDB.get(m));
        // jadwalHarianDB.setKelas(kelasDB.get(i));

        // // LocalDate tanggalSekarang = LocalDate.now();
        // // LocalDate tanggal = tanggalSekarang.plusDays(i);
        // Date tanggal = new Date();
        // Calendar c = Calendar.getInstance();
        // c.setTime(tanggal);
        // c.add(Calendar.DATE, i + 1);
        // tanggal = c.getTime();
        // jadwalHarianDB.setTglJadwal(tanggal);

        // DateFormat dateFormat = new SimpleDateFormat("EEEEE");
        // String hari = dateFormat.format(c.getTime());
        // if (hari.equals("Sunday")) {
        // hari = "Minggu";
        // } else if (hari.equals("Monday")) {
        // hari = "Senin";
        // } else if (hari.equals("Tuesday")) {
        // hari = "Selasa";
        // } else if (hari.equals("Wednesday")) {
        // hari = "Rabu";
        // } else if (hari.equals("Thursday")) {
        // hari = "Kamis";
        // } else if (hari.equals("Friday")) {
        // hari = "Jumat";
        // } else if (hari.equals("Saturday")) {
        // hari = "Sabtu";
        // }
        // // String[] hari = { "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu",
        // // "Minggu" };
        // // Random Dice2 = new Random();
        // // int n = Dice2.nextInt(6);
        // jadwalHarianDB.setHariJadwal(hari);

        // // for (int k = 0; k < 5; k++) {

        // Integer[] sesi = { 1, 2, 3, 4 };
        // Random Dice3 = new Random();
        // int o = Dice3.nextInt(4);
        // jadwalHarianDB.setSesiJadwal(sesi[o]);
        // // }
        // jadwalHarianDB.setStatus("S");
        // list.add(jadwalHarianDB);
        // }
        // repo.saveAll(list);

        GenerateTabel generateTabel = generateRepo.findById(1).get();
        generateTabel.setGenerateJadwal(true);
        generateRepo.save(generateTabel);

        return list;
    }

    public MulaiKelasResponse mulaiKelas(String idJadwal) {
        MulaiKelasResponse DB = new MulaiKelasResponse();
        JadwalHarian jadwalHarianDB = findJadwalHarianById(idJadwal);

        if (jadwalHarianDB.getInstrukturPeganti() != null) {

            DB.setIdJadwal(jadwalHarianDB.getId());
            DB.setInstruktur(jadwalHarianDB.getInstruktur());
            DB.setInstrukturPeganti(jadwalHarianDB.getInstrukturPeganti());
            DB.setKelas(jadwalHarianDB.getKelas());
            DB.setTglJadwal(jadwalHarianDB.getTglJadwal());
            DB.setHariJadwal(jadwalHarianDB.getHariJadwal());
            DB.setSesiJadwal(jadwalHarianDB.getSesiJadwal());
            DB.setStatusJadwal(jadwalHarianDB.getStatus());

            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = dateFormat.format(now);

            DB.setTglpresensi(tgl);
            DB.setMulaiGym(jadwalHarianDB.getSesiJadwal());
            DB.setStatusPresensi("AP");
            DB.setKeterangan("presensi_kelas");

            PresensiInstruktur presensiInstrukturDB = new PresensiInstruktur();
            String generateString = RandomStringUtils.randomAlphanumeric(8);

            String inisial = jadwalHarianDB.getInstrukturPeganti().getInisial();

            presensiInstrukturDB.setId("PK-" + generateString + "-" + inisial);
            presensiInstrukturDB.setInstruktur(jadwalHarianDB.getInstrukturPeganti());
            presensiInstrukturDB.setTglpresensi(tgl);
            presensiInstrukturDB.setMulaiGym(jadwalHarianDB.getSesiJadwal());
            presensiInstrukturDB.setStatus("AP");
            presensiInstrukturDB.setKeterangan("presensi_kelas");
            presensiInstrukturRepo.save(presensiInstrukturDB);

            jadwalHarianDB.setStatus("G");
            jadwalHarianRepo.save(jadwalHarianDB);
            // presensiInstrukturDB.setTglpresensi(tgl);
            // presensiInstrukturDB.setMulaiGym(jadwalHarianDB.getSesiJadwal());
            // presensiInstrukturDB.setStatus("");
            return DB;
        } else {

            DB.setIdJadwal(jadwalHarianDB.getId());
            DB.setInstruktur(jadwalHarianDB.getInstruktur());
            DB.setInstrukturPeganti(jadwalHarianDB.getInstrukturPeganti());
            DB.setKelas(jadwalHarianDB.getKelas());
            DB.setTglJadwal(jadwalHarianDB.getTglJadwal());
            DB.setHariJadwal(jadwalHarianDB.getHariJadwal());
            DB.setSesiJadwal(jadwalHarianDB.getSesiJadwal());
            DB.setStatusJadwal(jadwalHarianDB.getStatus());

            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = dateFormat.format(now);

            DB.setTglpresensi(tgl);
            DB.setMulaiGym(jadwalHarianDB.getSesiJadwal());
            DB.setStatusPresensi("AP");
            DB.setKeterangan("presensi_kelas");

            PresensiInstruktur presensiInstrukturDB = new PresensiInstruktur();
            String generateString = RandomStringUtils.randomAlphanumeric(8);

            String inisial = jadwalHarianDB.getInstruktur().getInisial();

            presensiInstrukturDB.setId("PK-" + generateString + "-" + inisial);
            presensiInstrukturDB.setInstruktur(jadwalHarianDB.getInstruktur());
            presensiInstrukturDB.setTglpresensi(tgl);
            presensiInstrukturDB.setMulaiGym(jadwalHarianDB.getSesiJadwal());
            presensiInstrukturDB.setStatus("AP");
            presensiInstrukturDB.setKeterangan("presensi_kelas");
            presensiInstrukturRepo.save(presensiInstrukturDB);

            jadwalHarianDB.setStatus("G");
            jadwalHarianRepo.save(jadwalHarianDB);
            // presensiInstrukturDB.setTglpresensi(tgl);
            // presensiInstrukturDB.setMulaiGym(jadwalHarianDB.getSesiJadwal());
            // presensiInstrukturDB.setStatus("");
            return DB;

        }

    }

    public MulaiKelasResponse akhiriKelas(String idJadwal) {
        MulaiKelasResponse DB = new MulaiKelasResponse();
        JadwalHarian jadwalHarianDB = findJadwalHarianById(idJadwal);

        if (jadwalHarianDB.getInstrukturPeganti() != null) {
            DB.setIdJadwal(jadwalHarianDB.getId());
            DB.setInstruktur(jadwalHarianDB.getInstruktur());
            DB.setInstrukturPeganti(jadwalHarianDB.getInstrukturPeganti());
            DB.setKelas(jadwalHarianDB.getKelas());
            DB.setTglJadwal(jadwalHarianDB.getTglJadwal());
            DB.setHariJadwal(jadwalHarianDB.getHariJadwal());
            DB.setSesiJadwal(jadwalHarianDB.getSesiJadwal());
            DB.setStatusJadwal(jadwalHarianDB.getStatus());

            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = dateFormat.format(now);

            DB.setTglpresensi(tgl);
            DB.setMulaiGym(jadwalHarianDB.getSesiJadwal());
            DB.setStatusPresensi("AP");
            DB.setKeterangan("presensi_kelas");

            String ket = "presensi_kelas";
            String status = "AP";
            PresensiInstruktur presensiInstrukturDB = presensiInstrukturRepo.findPresensiInstruktur(
                    jadwalHarianDB.getInstrukturPeganti(), tgl,
                    jadwalHarianDB.getSesiJadwal(), ket, status);

            presensiInstrukturDB.setAkhirGym(jadwalHarianDB.getSesiJadwal() + 1);
            presensiInstrukturRepo.save(presensiInstrukturDB);

            jadwalHarianDB.setStatus("E");
            jadwalHarianRepo.save(jadwalHarianDB);
            return DB;
        } else {
            DB.setIdJadwal(jadwalHarianDB.getId());
            DB.setInstruktur(jadwalHarianDB.getInstruktur());
            DB.setInstrukturPeganti(jadwalHarianDB.getInstrukturPeganti());
            DB.setKelas(jadwalHarianDB.getKelas());
            DB.setTglJadwal(jadwalHarianDB.getTglJadwal());
            DB.setHariJadwal(jadwalHarianDB.getHariJadwal());
            DB.setSesiJadwal(jadwalHarianDB.getSesiJadwal());
            DB.setStatusJadwal(jadwalHarianDB.getStatus());

            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = dateFormat.format(now);

            DB.setTglpresensi(tgl);
            DB.setMulaiGym(jadwalHarianDB.getSesiJadwal());
            DB.setStatusPresensi("AP");
            DB.setKeterangan("presensi_kelas");

            String ket = "presensi_kelas";
            String status = "AP";
            PresensiInstruktur presensiInstrukturDB = presensiInstrukturRepo.findPresensiInstruktur(
                    jadwalHarianDB.getInstruktur(), tgl,
                    jadwalHarianDB.getSesiJadwal(), ket, status);

            presensiInstrukturDB.setAkhirGym(jadwalHarianDB.getSesiJadwal() + 1);
            presensiInstrukturRepo.save(presensiInstrukturDB);

            jadwalHarianDB.setStatus("E");
            jadwalHarianRepo.save(jadwalHarianDB);
            return DB;
        }

    }

    // public String createJadwalHarian() {
    // JadwalHarian jadwalHarianDB1 = new JadwalHarian();
    // JadwalHarian jadwalHarianDB2 = new JadwalHarian();
    // JadwalHarian jadwalHarianDB3 = new JadwalHarian();
    // JadwalHarian jadwalHarianDB4 = new JadwalHarian();
    // JadwalHarian jadwalHarianDB5 = new JadwalHarian();
    // JadwalHarian jadwalHarianDB6 = new JadwalHarian();

    // String generateString1 = RandomStringUtils.randomAlphanumeric(15);
    // String generateString2 = RandomStringUtils.randomAlphanumeric(15);
    // String generateString3 = RandomStringUtils.randomAlphanumeric(15);
    // String generateString4 = RandomStringUtils.randomAlphanumeric(15);
    // String generateString5 = RandomStringUtils.randomAlphanumeric(15);
    // String generateString6 = RandomStringUtils.randomAlphanumeric(15);
    // jadwalHarianDB1.setId("JH_" + generateString1);
    // jadwalHarianDB2.setId("JH_" + generateString2);
    // jadwalHarianDB3.setId("JH_" + generateString3);
    // jadwalHarianDB4.setId("JH_" + generateString4);
    // jadwalHarianDB5.setId("JH_" + generateString5);
    // jadwalHarianDB6.setId("JH_" + generateString6);

    // List<Instruktur> instrukturDB = impl.findAll();

    // List<JadwalHarian> list = new ArrayList<>();
    // for(int i= 0; i<6; i++){
    // list.add(new JadwalHarian(generateString1,));
    // }

    // jadwalHarianDB1.set
    // // list.add(jadwalHarianDB6);
    // // for (int i = 0; i < 6; i++) {
    // // bookList.add(new Book("Book " + i, "Author " + i));
    // // }
    // return "Berhasil menambah data";
    // }

}