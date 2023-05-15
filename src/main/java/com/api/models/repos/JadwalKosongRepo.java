package com.api.models.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.JadwalHarian;

public interface JadwalKosongRepo extends JpaRepository<JadwalHarian, String> {
    @Query("SELECT jh FROM JadwalHarian jh where jh.tglJadwal = ?1")
    public List<JadwalHarian> findAllJadwalKosong(LocalDate now);
}
