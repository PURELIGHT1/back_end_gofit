package com.api.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IjinInstrukturResponse {
    private String id;
    private String idInstruktur;
    private String instruktur;
    private String inisialInstruktur;
    private Date tglpresensi;
    private Integer mulaiGym;
    private Integer akhirGym;
    private String status;
    private String idJadwalHarian;
    private Date tglJadwal;
}
