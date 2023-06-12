package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportResponseKinerjaBulanan {
    private String instruktur;
    private Integer hadir;
    private Integer telat;
    private Integer libur;
}
