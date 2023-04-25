package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.models.entities.GenerateTabel;

public interface GenerateRepo extends JpaRepository<GenerateTabel, Integer> {

}
