package com.api.dto;

import java.util.List;

import com.api.models.entities.JadwalHarian;
import com.api.models.entities.PresensiInstruktur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInsResponse {
    private List<JadwalHarianResponse> listJadwalHarian;
    private List<PresensiInstruktur> listPresensiInstruktur;
}
