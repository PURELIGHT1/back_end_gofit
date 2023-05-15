package com.api.dto;

import java.sql.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private String nama;
    private String email;
    private String alamat;
    @Temporal(TemporalType.DATE)
    private Date tglLahir;
    private String noHp;
    private String creator;
    private Integer modifier;

    private String pegawai;
}
