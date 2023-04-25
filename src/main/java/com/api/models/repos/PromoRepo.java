package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Promo;

public interface PromoRepo extends JpaRepository<Promo, Integer> {

    @Query("SELECT pr FROM promo pr WHERE pr.jenis = ?1")
    public List<Promo> findByjenis(String jenis);

}
