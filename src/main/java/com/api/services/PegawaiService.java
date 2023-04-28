package com.api.services;

import java.util.List;

import com.api.models.entities.Pegawai;

public interface PegawaiService {

    List<Pegawai> findAll();

    Pegawai findByIdPegawai(String id);

    Pegawai updatePegawai(String id, Pegawai pegawai);

    Pegawai addUserPegawai(String id, String role);

    Pegawai updatePegawaiStatus(String id);

    Pegawai createPegawai(Pegawai Pegawai);

    void deletePegawai(String id);

    Pegawai updateFotoPegawai(String id, String foto);

    List<Pegawai> findByEmail(String email);
}
