package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.GenerateTabel;

import jakarta.transaction.Transactional;

public interface GenerateRepo extends JpaRepository<GenerateTabel, Integer> {
    @Query("SELECT generateIdInstruktur FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateIdInstrukturByGenerateTabel(Integer id);

    @Query("SELECT generateIdMember FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateIdMemberByGenerateTabel(Integer id);

    @Query("SELECT generateIdPegawai FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateIdPegawaiByGenerateTabel(Integer id);

    @Query("SELECT generateStruk FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateStrukByGenerateTabel(Integer id);

    @Query("SELECT generateJadwal FROM generate gr WHERE gr.id = ?1")
    public boolean findgenerateJadwalByGenerateTabel(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateIdInstruktur = ?1 WHERE gr.id = 1")
    public Integer updateGenereteIdInstrukturByGenerateTabel(Integer counter);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateIdMember = ?1 WHERE gr.id = 1")
    public Integer updateGenereteIdMemberByGenerateTabel(Integer counter);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateIdPegawai = ?1 WHERE gr.id = 1")
    public Integer updateGenereteIdPegawaiByGenerateTabel(Integer counter);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateStruk = ?1 WHERE gr.id = 1")
    public Integer updateGenereteStrukByGenerateTabel(Integer counter);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateJadwal = ?1 WHERE gr.id = 1")
    public Integer updateGenereteJadwalByGenerateTabel(Boolean statusGenerate);
}
