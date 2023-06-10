package com.api.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransaksiAktivasiResponse {
    private String id;
    private String idPegawai;
    private String pegawai;
    private String idMember;
    private String member;
    private Integer jlhBayar;
    private Date tglAktiviasi;
    private Date tglBerlaku;
    private String status;
}
