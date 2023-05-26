package com.api.dto;

import java.util.Date;

import com.api.models.entities.Instruktur;
import com.api.models.entities.Kelas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JadwalHarianResponse {
    private String id;
    private Instruktur instruktur;
    private Instruktur instrukturPeganti;
    private Kelas kelas;
    private String tglJadwal;
    private String hariJadwal;
    private Integer sesiJadwal;
    private String status;
    private Integer totalMember;
}
