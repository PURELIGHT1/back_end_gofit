package com.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseKelasBulanan {
    private List<String> kelas;
    private List<Integer> jlhPeserta;
    private List<Integer> libur;
    private List<String> ins;
}
