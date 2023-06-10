package com.api.implement.services;

import com.api.dto.ResponseSelectInt;
import com.api.models.entities.Kelas;
import java.util.List;

public interface KelasService {

    List<Kelas> findAll();

    List<Kelas> findAllASC();

    List<String> findAllKelasRepot();

    List<ResponseSelectInt> findKelasAktif();

    Kelas findByIdKelas(Integer id);

    Kelas updateKelas(Integer id, Kelas kelas);

    Kelas createKelas(Kelas kelas);

    void deleteKelas(Integer id);
}
