package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.TransaksiDepositUang;

public interface DepositUangRepo extends JpaRepository<TransaksiDepositUang, String> {

    @Query("SELECT t FROM TransaksiAktivasi t WHERE t.status = 'A'")
    public List<TransaksiDepositUang> findAllAktivasi();
}
