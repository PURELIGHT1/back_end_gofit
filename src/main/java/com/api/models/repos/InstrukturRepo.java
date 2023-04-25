package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.api.models.entities.Instruktur;

import jakarta.transaction.Transactional;

public interface InstrukturRepo extends JpaRepository<Instruktur, String> {

    @Query("SELECT i FROM instruktur i WHERE i.email = ?1")
    public List<Instruktur> findByEmail(String email);

    @Query("SELECT i FROM instruktur i WHERE i.inisial = ?1")
    public List<Instruktur> findByInisial(String inisial);

    @Query("SELECT generateIdInstruktur FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateIdInstrukturByGenereateTabel(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateIdInstruktur = ?1 WHERE gr.id = ?2")
    public Integer updateGenereteIdByGenerateTabel(Integer counter, Integer id);
}
