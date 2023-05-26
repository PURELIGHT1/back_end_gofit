package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaporanInstrukturResponse {
    private String nama;
    private Integer hadir;
    private Integer libur;
    private Integer terlambat;
}
