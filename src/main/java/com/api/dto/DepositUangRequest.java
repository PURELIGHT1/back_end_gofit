package com.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepositUangRequest {
    private String pegawai;
    private Integer jlhDeposit;
    private String member;
}