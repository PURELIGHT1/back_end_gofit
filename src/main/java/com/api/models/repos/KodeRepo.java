package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.models.entities.Kode;

public interface KodeRepo extends JpaRepository<Kode, Integer> {

}
