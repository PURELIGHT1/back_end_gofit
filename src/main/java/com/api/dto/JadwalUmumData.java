package com.api.dto;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
// import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JadwalUmumData {

    @Temporal(TemporalType.DATE)
    private Date tglJadwal;
    private String hariJadwal;
    private String sesiJadwal;
}
