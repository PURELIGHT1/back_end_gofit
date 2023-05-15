package com.api.implement.services;

import java.util.List;

import com.api.dto.UbahPasswordRequest;
import com.api.models.entities.Instruktur;

public interface InstrukturService {

    List<Instruktur> findAll();

    Instruktur findByIdInstruktur(String id);

    Instruktur updateInstruktur(String id, Instruktur instruktur);

    Instruktur updateInstrukturStatus(String id);

    Instruktur createInstruktur(Instruktur instruktur);

    void deleteInstruktur(String id);

    Instruktur updateFotoInstruktur(String id, String foto);

    List<Instruktur> findByEmail(String email);

    List<Instruktur> findByInisial(String inisial);

    Instruktur ubahPasswordInstruktur(String id, UbahPasswordRequest request);
}
