package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportPendapatanResponse {
    private Integer aktivasi;
    private Integer deposit;
    private String bulan;
}