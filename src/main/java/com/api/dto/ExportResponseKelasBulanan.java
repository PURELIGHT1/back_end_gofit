package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportResponseKelasBulanan {
    private String kelas;
    private String instruktur;
    private Integer peserta;
    private Integer libur;
}
