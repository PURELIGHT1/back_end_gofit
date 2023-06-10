package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Kelas;

public interface KelasRepo extends JpaRepository<Kelas, Integer> {

    @Query("SELECT k FROM Kelas k order by k.nama asc")
    public List<Kelas> findAllASC();

}
