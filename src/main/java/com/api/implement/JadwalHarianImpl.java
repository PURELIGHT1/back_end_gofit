package com.api.implement;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalHarian;
import com.api.models.entities.Kelas;
import com.api.models.repos.JadwalHarianRepo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
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

    public List<JadwalHarian> findAllJadwalHarianByDate(LocalDate awal, LocalDate akhir) {
        // LocalDate tanggalSekarang = LocalDate.now();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
        // String hariSekarang = tanggalSekarang.format(formatter);
        // if (hariSekarang.equals("Friday")) {
        // return (List<JadwalHarian>) repo.findJadwalSatuMinggu(tanggalSekarang,
        // tanggalSekarang.plusDays(6));
        // } else {

        // int result = hariSekarang.compareTo("Friday");
        // if (result < 0) {
        // return (List<JadwalHarian>)
        // repo.findJadwalSatuMinggu(tanggalSekarang.plusDays(result),
        // tanggalSekarang.plusDays(6 + result));
        // } else {
        // return (List<JadwalHarian>)
        // repo.findJadwalSatuMinggu(tanggalSekarang.minusDays(result),
        // tanggalSekarang.plusDays(6 + result));
        // }
        // }
        // while (!hariSekarang.compareTo(hariSekarang)) {
        // tanggalSekarang = tanggalSekarang.minusDays(1);
        // hariSekarang = tanggalSekarang.format(formatter);
        // }
        return (List<JadwalHarian>) repo.findJadwalSatuMinggu(awal, akhir);
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
            JadwalHarian jadwalHarianDB = new JadwalHarian();
            String generateString = RandomStringUtils.randomAlphanumeric(15);
            jadwalHarianDB.setId("JH_" + generateString);
            Random Dice = new Random();
            int m = Dice.nextInt(3);
            jadwalHarianDB.setInstruktur(instrukturDB.get(m));
            jadwalHarianDB.setKelas(kelasDB.get(i));

            LocalDate tanggalSekarang = LocalDate.now();
            LocalDate tanggal = tanggalSekarang.plusDays(i);
            jadwalHarianDB.setTglJadwal(tanggal);

            String[] hari = { "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu" };
            Random Dice2 = new Random();
            int n = Dice2.nextInt(6);
            Integer[] sesi = { 1, 2, 3, 4 };
            Random Dice3 = new Random();
            int o = Dice3.nextInt(4);
            jadwalHarianDB.setHariJadwal(hari[n]);

            // for (int k = 0; k < 5; k++) {
            jadwalHarianDB.setSesiJadwal(sesi[o]);
            // }
            jadwalHarianDB.setStatus("S");
            list.add(jadwalHarianDB);
        }
        return repo.saveAll(list);
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