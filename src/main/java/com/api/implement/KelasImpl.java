package com.api.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ResponseSelectInt;
import com.api.exception.kelas.*;
import com.api.implement.services.KelasService;
import com.api.models.entities.Kelas;
import com.api.models.repos.KelasRepo;

@Service
public class KelasImpl implements KelasService {

    @Autowired
    private KelasRepo kelasRepo;

    @Override
    public Kelas createKelas(Kelas kelas) {
        Kelas kelasDB = new Kelas();

        if (Objects.nonNull(kelas.getNama()) &&
                !"".equalsIgnoreCase(kelas.getNama())) {
            kelasDB.setNama(kelas.getNama());
        } else {
            throw new KelasExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (kelas.getHarga().equals(0)) {
            throw new KelasExceptionBadRequest("Harga tidak boleh kurang atau sama dengan 0");
        } else {
            kelasDB.setHarga(kelas.getHarga());
        }

        if (kelas.getSlot() <= 0) {
            throw new KelasExceptionBadRequest("Slot tidak boleh kurang atau sama dengan 0");
        } else {
            kelasDB.setSlot(kelas.getSlot());
        }

        return kelasRepo.save(kelasDB);
    }

    @Override
    public void deleteKelas(Integer id) {
        kelasRepo.deleteById(id);
    }

    @Override
    public List<Kelas> findAll() {
        return (List<Kelas>) kelasRepo.findAll();
    }

    @Override
    public List<Kelas> findAllASC() {
        return (List<Kelas>) kelasRepo.findAllASC();
    }

    @Override
    public List<ResponseSelectInt> findKelasAktif() {
        List<ResponseSelectInt> list = new ArrayList<>();
        List<Kelas> kelas = kelasRepo.findAll();
        kelas.forEach(i -> {
            ResponseSelectInt responseSelect = new ResponseSelectInt();
            responseSelect.setLabel(i.getNama());
            responseSelect.setValue(i.getId());
            // responseSelect.setValue(i.getId()).toString());
            list.add(responseSelect);
        });
        return list;
    }

    @Override
    public List<String> findAllKelasRepot() {
        List<String> list = new ArrayList<>();
        List<Kelas> kelas = kelasRepo.findAll();
        for (int i = 0; i < kelas.size(); i++) {
            list.add(kelas.get(i).getNama());
        }
        return list;
    }

    @Override
    public Kelas findByIdKelas(Integer id) {
        if (kelasRepo.findById(id).isEmpty()) {
            throw new KelasExceptionNotFound("Data tidak ditemukan");
        }
        return kelasRepo.findById(id).get();
    }

    @Override
    public Kelas updateKelas(Integer id, Kelas kelas) {
        if (kelasRepo.findById(id).isEmpty()) {
            throw new KelasExceptionBadRequest("Data tidak ditemukan");
        }

        Kelas kelasDB = kelasRepo.findById(id).get();

        if (Objects.nonNull(kelas.getNama()) &&
                !"".equalsIgnoreCase(kelas.getNama())) {
            kelasDB.setNama(kelas.getNama());
        } else {
            throw new KelasExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (kelas.getHarga().equals(0)) {
            throw new KelasExceptionBadRequest("Harga tidak boleh kurang atau sama dengan 0");
        } else {
            kelasDB.setHarga(kelas.getHarga());
        }

        if (kelas.getSlot() <= 0) {
            throw new KelasExceptionBadRequest("Slot tidak boleh kurang atau sama dengan 0");
        } else {
            kelasDB.setSlot(kelas.getSlot());
        }

        return kelasRepo.save(kelasDB);
    }
}
