package com.api.dto;

import com.api.models.entities.Instruktur;
import com.api.models.entities.Pegawai;
import com.api.models.entities.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AuthenticationResponse {
    private String token;
    private String username;
    private Long id;
    private Enum role;
    private Pegawai pegawai;
    private Instruktur instruktur;
    private Member member;
}
