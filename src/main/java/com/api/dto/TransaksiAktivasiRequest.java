package com.api.dto;

import java.util.Date;

import com.api.models.entities.Member;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransaksiAktivasiRequest {
    private String id;
    private String pegawai;
    private Member member;
    private Integer jlhBayar;
    @Temporal(TemporalType.DATE)
    private Date tglAktiviasi;
    @Temporal(TemporalType.DATE)
    private Date tglBerlaku;
    private String status;
}
