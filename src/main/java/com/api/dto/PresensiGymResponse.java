package com.api.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PresensiGymResponse {
    private String id;
    private String idMember;
    private String member;
    private String tglpresensi;
    private Integer mulaiGym;
    private Integer akhirGym;
    private String status;
}
