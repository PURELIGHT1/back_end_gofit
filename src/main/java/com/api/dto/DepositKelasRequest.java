package com.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepositKelasRequest {
    private String pegawai;
    private Integer totalKelas;
    private String member;
    private Integer kelas;
}
