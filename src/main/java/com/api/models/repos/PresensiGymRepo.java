package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.PresensiGym;
import com.api.models.entities.TransaksiAktivasi;

import jakarta.transaction.Transactional;

public interface PresensiGymRepo extends JpaRepository<PresensiGym, String> {

    @Query("SELECT pg FROM PresensiGym pg WHERE pg.status = 'G'")
    public List<PresensiGym> findAllPresensiGym();

    
}
