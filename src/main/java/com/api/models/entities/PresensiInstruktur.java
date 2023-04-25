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

    @Column(name = "tgl_presensi_instruktur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglpresensi;

    @Column(name = "sesi_mulai_presensi_instruktur")
    private String mulaiGym;

    @Column(name = "sesi_akhir_presensi_instruktur")
    private String akhirGym;

    @Column(name = "status_presensi_instruktur")
    private Boolean status;
}
