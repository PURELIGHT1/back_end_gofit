package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class PresensiInstrukturRequest {
    private String id;
    private String instruktur;
    private String tglpresensi;
    private Integer mulaiGym;
    private Integer akhirGym;
    private String status;
}
