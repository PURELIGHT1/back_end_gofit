package com.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponsePresensiKelas {
    private String id;
    private String member;
    private String idMember;
    private String instruktur;
    private String idInstruktur;
    private String kelas;
    private Integer idKelas;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d MMMM y")
    private Date tgl;
}
