package com.api.implement;

import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.IjinInstrukturResponse;
import com.api.dto.PresensiInstrukturRequest;
import com.api.models.entities.Instruktur;
import com.api.models.entities.PresensiInstruktur;
import com.api.models.repos.InstrukturRepo;
import com.api.models.repos.PresensiInstrukturRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PresensiInstrukturImpl {

    @Autowired
    private PresensiInstrukturRepo repo;

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

    public List<IjinInstrukturResponse> findAllIzinInstruktur() {
        List<IjinInstrukturResponse> list = new ArrayList<>();
        List<PresensiInstruktur> listPI = repo.findPresensiIzinInstruktur();
        for (int i = 0; i < listPI.size(); i++) {
            IjinInstrukturResponse izin = new IjinInstrukturResponse();

            PresensiInstruktur PI = listPI.get(i);

            izin.setId(PI.getId());
            izin.setIdInstruktur(PI.getInstruktur().getId());
            izin.setInstruktur(PI.getInstruktur().getNama());
            izin.setInisialInstruktur(PI.getInstruktur().getInisial());
            izin.setTglpresensi(PI.getTglpresensi());
            izin.setMulaiGym(PI.getMulaiGym());
            izin.setAkhirGym(PI.getAkhirGym());
            izin.setStatus(PI.getStatus());
            izin.setIdJadwalHarian(PI.getJadwalHarian().getId());
            izin.setTglJadwal(PI.getJadwalHarian().getTglJadwal());

            list.add(izin);
        }
        return list;
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

        jadwalHarianImpl.editJadwalHarian(presensiDB.getJadwalHarian().getId());

        Instruktur instrukturDB = instrukturImpl.findByIdInstruktur(presensiDB.getInstruktur().getId());
        instrukturDB.setJlhLibur(instrukturDB.getJlhLibur() + 1);
        instrukturRepo.save(instrukturDB);
        repo.save(presensiDB);
    }

    public void tolakIzin(String id) {
        PresensiInstruktur presensiDB = repo.findById(id).get();
        presensiDB.setStatus("D");

        repo.save(presensiDB);
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
        // db.setTglpresensi(req.getTglpresensi());
        db.setMulaiGym(req.getMulaiGym());
        db.setAkhirGym(req.getAkhirGym());
        db.setStatus("PE");
        db.setKeterangan("presensi_izin");

        return repo.save(db);
    }
}
