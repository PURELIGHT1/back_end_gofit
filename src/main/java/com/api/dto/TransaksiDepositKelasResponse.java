package com.api.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransaksiDepositKelasResponse {
    private String id;
    private String idPegawai;
    private String pegawai;
    private String idMember;
    private String member;
    private Integer idKelas;
    private String kelas;
    private Integer idPromo;
    private String promo;
    private Integer sisaKelas;
    private Date tglBerlaku;
    private String status;
}
