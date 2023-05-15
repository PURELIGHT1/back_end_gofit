package com.api.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.models.entities.PresensiGym;
import com.api.models.repos.PresensiGymRepo;
import java.util.List;

@Service
public class PresensiGymImpl {

    @Autowired
    private PresensiGymRepo repo;

    public List<PresensiGym> findAll() {

        return (List<PresensiGym>) repo.findAllPresensiGym();
    }
}
