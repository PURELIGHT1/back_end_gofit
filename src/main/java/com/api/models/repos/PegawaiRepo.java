package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.api.models.entities.Pegawai;

import jakarta.transaction.Transactional;

public interface PegawaiRepo extends JpaRepository<Pegawai, String> {

    @Query("SELECT p FROM pegawai p WHERE p.email = ?1")
    public List<Pegawai> findByEmail(String email);

    @Query("SELECT generateIdPegawai FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateIdPegawaiByGenereateTabel(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateIdPegawai = ?1 WHERE gr.id = ?2")
    public Integer updateGenereteIdByGenerateTabel(Integer counter, Integer id);
}
