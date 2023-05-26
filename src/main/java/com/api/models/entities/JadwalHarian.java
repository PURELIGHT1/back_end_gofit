package com.api.models.entities;

import java.time.LocalDate;
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
@Table(name = "jadwal_harian")
public class JadwalHarian {
    @Id
    @Column(name = "id_jadwal_harian", length = 20)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_instruktur", nullable = true)
    private Instruktur instruktur;

    @ManyToOne
    @JoinColumn(name = "instruktur_pengganti", nullable = true)
    private Instruktur instrukturPeganti;

    @ManyToOne
    @JoinColumn(name = "id_kelas", nullable = false)
    private Kelas kelas;

    @Column(name = "tgl_jadwal_harian")
    // @Temporal(TemporalType.DATE)
    private String tglJadwal;

    @Column(name = "hari_jadwal_harian", length = 20)
    private String hariJadwal;

    @Column(name = "sesi_jadwal_harian", length = 5)
    private Integer sesiJadwal;

    @Column(name = "status_jadwal_harian", length = 5)
    private String status;
}
