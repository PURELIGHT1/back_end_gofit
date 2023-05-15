package com.api.models.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "presensi_instruktur")
public class PresensiInstruktur {
    @Id
    @Column(name = "id_presensi_instruktur", unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_instruktur", nullable = false)
    private Instruktur instruktur;

    @Column(name = "tgl_presensi_instruktur", length = 20)
    private String tglpresensi;

    @Column(name = "sesi_mulai_presensi_instruktur")
    private Integer mulaiGym;

    @Column(name = "sesi_akhir_presensi_instruktur")
    private Integer akhirGym;

    @Column(name = "status_presensi_instruktur", length = 5)
    private String status;
}
