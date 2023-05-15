package com.api.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.implement.services.JadwalUmumService;
import com.api.models.entities.JadwalUmum;
import com.api.models.repos.JadwalUmumRepo;

@Service
public class JadwalUmumImpl implements JadwalUmumService {

    @Autowired
    private JadwalUmumRepo jadwalRepo;

    @Override
    public JadwalUmum createJadwalUmum(JadwalUmum jadwal) {
        return jadwalRepo.save(jadwal);
    }

}
