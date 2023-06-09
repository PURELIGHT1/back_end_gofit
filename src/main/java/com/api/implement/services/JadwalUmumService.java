package com.api.implement.services;

import com.api.dto.JadwalUmumRequest;
import com.api.models.entities.JadwalUmum;
import java.util.List;

public interface JadwalUmumService {
    List<JadwalUmum> findJadwalUmum();

    List<JadwalUmum> findJadwalUmumByDay(String hari);

    JadwalUmum findJadwalUmumById(String id);

    JadwalUmum createJadwalUmum(JadwalUmumRequest jadwal);

    JadwalUmum updateJadwalUmum(String id, JadwalUmumRequest jadwal);

    void deleteJadwalUmum(String id);

}
