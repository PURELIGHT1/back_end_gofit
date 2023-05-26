package com.api.dto;

import com.api.models.entities.Member;
import com.api.models.entities.TransaksiAktivasi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileMemberResponse2 {
    private Member member;
    private TransaksiAktivasi transaksiAktivasi;
    private String jenisPaket;
    private Integer sisaPaket;
}
