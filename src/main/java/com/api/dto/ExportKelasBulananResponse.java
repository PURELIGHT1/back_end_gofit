package com.api.dto;

import java.util.List;

import com.api.models.entities.Kelas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportKelasBulananResponse {
    private String kelas;
    private String instruktur;
    private Integer peserta;
    private Integer libur;
}
