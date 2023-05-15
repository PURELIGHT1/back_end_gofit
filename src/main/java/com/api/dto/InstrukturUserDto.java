package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstrukturUserDto {
    private String id;
    private String username;
    private String role;
    private String nama;
    private String foto;
    private String inisial;
    private String email;
    private String alamat;
    private String tglLahir;
    private String noHp;
    private Integer jlhHadir;
    private Integer jlhLibur;
    private Integer jlhTerlambat;
    private String status;
    private String creator;
}
