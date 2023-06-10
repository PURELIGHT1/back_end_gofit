package com.api.implement;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.JadwalUmumRequest;
import com.api.exception.kelas.KelasExceptionNotFound;
import com.api.implement.services.JadwalUmumService;
import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalUmum;
import com.api.models.entities.Kelas;
import com.api.models.repos.JadwalUmumRepo;

@Service
public class JadwalUmumImpl implements JadwalUmumService {

    @Autowired
    private JadwalUmumRepo repo;

    @Autowired
    private InstrukturImpl instrukturImpl;

    @Autowired
    private KelasImpl kelasImpl;

    @Override
    public List<JadwalUmum> findJadwalUmum() {
        return (List<JadwalUmum>) repo.findAll();
    }

    @Override
    public JadwalUmum findJadwalUmumById(String id) {
        if (repo.findById(id).get() == null) {
            throw new KelasExceptionNotFound("Data tidak ditemukan");
        }
        return repo.findById(id).get();
    }

    @Override
    public JadwalUmum createJadwalUmum(JadwalUmumRequest jadwal) {
        JadwalUmum db = new JadwalUmum();
        Instruktur instrukturDB = instrukturImpl.findByIdInstruktur(jadwal.getInstruktur());
        Kelas kelasDB = kelasImpl.findByIdKelas(jadwal.getKelas());
        db.setInstruktur(instrukturDB);
        db.setKelas(kelasDB);
        db.setHariJadwal(jadwal.getHariJadwal());
        db.setSesiJadwal(jadwal.getSesiJadwal());
        db.setStatus("S");

        Date now = new Date();
        DateFormat dateFormat2 = new SimpleDateFormat("YY.MM");
        String id = dateFormat2.format(now);
        String generateString = RandomStringUtils.randomAlphanumeric(8);
        db.setId("JU." + id + "." + generateString);

        return repo.save(db);
    }

    @Override
    public JadwalUmum updateJadwalUmum(String id, JadwalUmumRequest jadwal) {
        JadwalUmum db = findJadwalUmumById(id);
        Instruktur instrukturDB = instrukturImpl.findByIdInstruktur(jadwal.getInstruktur());
        Kelas kelasDB = kelasImpl.findByIdKelas(jadwal.getKelas());
        db.setInstruktur(instrukturDB);
        db.setKelas(kelasDB);
        db.setHariJadwal(jadwal.getHariJadwal());
        db.setSesiJadwal(jadwal.getSesiJadwal());
        return repo.save(db);
    }

    @Override
    public void deleteJadwalUmum(String id) {
        repo.deleteById(id);
    }

    @Override
    public List<JadwalUmum> findJadwalUmumByDay(String hari) {
        return repo.findAllJadwalByDay(hari);
    }
}
