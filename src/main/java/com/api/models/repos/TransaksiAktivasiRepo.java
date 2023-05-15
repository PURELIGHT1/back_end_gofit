package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.TransaksiAktivasi;

import jakarta.transaction.Transactional;

public interface TransaksiAktivasiRepo extends JpaRepository<TransaksiAktivasi, String> {

    // @Query("SELECT t FROM TransaksiAktivasi t WHERE t.status = 'A'")
    // public List<TransaksiAktivasi> findAllAktivasi();

    // @Query("SELECT t FROM TransaksiAktivasi t WHERE t.status = 'Tidak Aktif'")
    // public List<TransaksiAktivasi> findAllNonAktivasi();

}
