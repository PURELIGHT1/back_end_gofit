package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JadwalUmumRequest {
    private String instruktur;
    private Integer kelas;
    private String hariJadwal;
    private Integer sesiJadwal;
}
