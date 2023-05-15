package com.api.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.models.entities.TransaksiDepositUang;
import com.api.models.repos.DepositUangRepo;
import java.util.List;

@Service
public class DepositUangImpl {

    @Autowired
    private DepositUangRepo repo;

    public List<TransaksiDepositUang> findAll() {
        return (List<TransaksiDepositUang>) repo.findAll();
    }

}
