package com.api.dto;

import com.api.models.entities.Instruktur;
import com.api.models.entities.Kelas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MulaiKelasResponse {

    private String idJadwal;
    private Instruktur instruktur;
    private Instruktur instrukturPeganti;
    private Kelas kelas;
    private String tglJadwal;
    private String hariJadwal;
    private Integer sesiJadwal;
    private String statusJadwal;
    private String tglpresensi;
    private Integer mulaiGym;
    private String statusPresensi;
    private String keterangan;
}
