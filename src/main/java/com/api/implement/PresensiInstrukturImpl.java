package com.api.implement;

import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.PresensiInstrukturRequest;
import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalHarian;
import com.api.models.entities.PresensiInstruktur;
import com.api.models.repos.InstrukturRepo;
import com.api.models.repos.JadwalHarianRepo;
import com.api.models.repos.PresensiInstrukturRepo;

import java.util.Date;
import java.util.List;

@Service
public class PresensiInstrukturImpl {

    @Autowired
    private PresensiInstrukturRepo repo;

    @Autowired
    private JadwalHarianRepo jadwalHarianRepo;

    @Autowired
    private JadwalHarianImpl jadwalHarianImpl;

    @Autowired
    private InstrukturImpl instrukturImpl;

    @Autowired
    private InstrukturRepo instrukturRepo;

    public List<PresensiInstruktur> findAllIzin() {
        // return (List<PresensiInstruktur>) repo.findPresensiIzin();
        return repo.findAll();
    }

    public List<PresensiInstruktur> filterAllIzin() {
        // return (List<PresensiInstruktur>) repo.findPresensiIzin();
        return repo.findPresensiIzin();
    }

    public List<PresensiInstruktur> findAllJadwalHarianIns(String id) {

        Instruktur instrukturDB = instrukturImpl.findByIdInstruktur(id);
        return repo.findPresensiIzinById(instrukturDB);
    }

    public List<PresensiInstruktur> findAllIzinById(String id) {
        Instruktur instrukturDB = instrukturRepo.findById(id).get();
        return repo.findPresensiIzinById(instrukturDB);
    }

    public void konfirmasiIzin(String id) {
        PresensiInstruktur presensiDB = repo.findById(id).get();
        presensiDB.setStatus("C");

        repo.save(presensiDB);
        Date now = new Date();

        List<JadwalHarian> jadwalHarian = repo.findJadwalHarianByIdInsBysesi(presensiDB.getInstruktur(), now,
                presensiDB.getMulaiGym(), presensiDB.getAkhirGym());

        int i = 0;
        for (i = 0; i <= jadwalHarian.size(); i++) {
            jadwalHarianImpl.editJadwalHarian(jadwalHarian.get(i).getId());
        }
        jadwalHarianRepo.saveAll(jadwalHarian);
    }

    public void tolakIzin(String id) {
        PresensiInstruktur presensiDB = repo.findById(id).get();
        presensiDB.setStatus("D");

        repo.save(presensiDB);
        ;
    }

    public PresensiInstruktur findByIdPresensi(String id) {
        return repo.findById(id).get();
    }

    public PresensiInstruktur createPresensiIzin(PresensiInstrukturRequest req) {
        PresensiInstruktur db = new PresensiInstruktur();
        String generateString = RandomStringUtils.randomAlphanumeric(8);
        Instruktur instrukturDB = instrukturImpl.findByIdInstruktur(req.getId());

        db.setId("PI-" + generateString + "-" + instrukturDB.getInisial());
        db.setInstruktur(instrukturDB);
        db.setTglpresensi(req.getTglpresensi());
        db.setMulaiGym(req.getMulaiGym());
        db.setAkhirGym(req.getAkhirGym());
        db.setStatus("PE");
        db.setKeterangan("presensi_izin");

        return repo.save(db);
    }
}
