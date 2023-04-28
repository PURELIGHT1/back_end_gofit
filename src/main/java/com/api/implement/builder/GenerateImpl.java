package com.api.implement.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.models.repos.GenerateRepo;

@Service
public class GenerateImpl {

    @Autowired
    private GenerateRepo repo;

    public Integer findGenerateIdInstruktur(Integer id) {
        return repo.findgenerateIdInstrukturByGenerateTabel(id);
    }

    public Integer findGenerateIdMember(Integer id) {
        return repo.findgenerateIdMemberByGenerateTabel(id);
    }

    public Integer findGenerateIdPegawai(Integer id) {
        return repo.findgenerateIdPegawaiByGenerateTabel(id);
    }

    public Integer findGenerateStruk(Integer id) {
        return repo.findgenerateStrukByGenerateTabel(id);
    }

    public Integer updateGenereteIdInstruktur(Integer counter) {
        return repo.updateGenereteIdInstrukturByGenerateTabel(counter);
    }

    public Integer updateGenereteIdMember(Integer counter) {
        return repo.updateGenereteIdMemberByGenerateTabel(counter);
    }

    public Integer updateGenereteIdPegawai(Integer counter) {
        return repo.updateGenereteIdPegawaiByGenerateTabel(counter);
    }

    public Integer updateGenereteStruk(Integer counter) {
        return repo.updateGenereteStrukByGenerateTabel(counter);
    }
}
