package com.api.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransaksiDepositUangResponse {
    private String id;
    private String idPegawai;
    private String pegawai;
    private String idMember;
    private String member;
    private Integer idPromo;
    private String promo;
    private Integer jlhDeposit;
    private Date tglDeposit;
    private Integer totalDeposit;
    private String status;
}
