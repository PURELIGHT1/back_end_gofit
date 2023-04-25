package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.models.entities.Kelas;

public interface KelasRepo extends JpaRepository<Kelas, Integer> {

}
