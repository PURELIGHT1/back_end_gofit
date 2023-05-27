package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalUmum;

public interface JadwalUmumRepo extends JpaRepository<JadwalUmum, String> {

    @Query("select ju from JadwalUmum ju where ju.hariJadwal =?1 and ju.sesiJadwal =?2 and ju.instruktur =?3")
    public JadwalUmum findAllJadwalKosong(String hari, Integer sesi, Instruktur instruktur);

    @Query("select count(ju) from JadwalUmum ju where ju.hariJadwal =?1 and ju.sesiJadwal =?2 and ju.instruktur =?3")
    public Integer countAllJadwalKosong(String hari, Integer sesi, Instruktur instruktur);
}
