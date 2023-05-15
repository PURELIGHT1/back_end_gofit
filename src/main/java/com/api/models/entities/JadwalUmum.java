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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "jadwal_umum")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name = "jadwal_umum")
public class JadwalUmum {
    @Id
    @Column(name = "id_jadwal_umum", length = 20)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_instruktur", nullable = false)
    private Instruktur instruktur;

    @ManyToOne
    @JoinColumn(name = "id_kelas", nullable = false)
    private Kelas kelas;

    @Column(name = "tgl_jadwal_umum")
    @Temporal(TemporalType.DATE)
    private LocalDate tglJadwal;

    @Column(name = "hari_jadwal_umum", length = 20)
    private String hariJadwal;

    @Column(name = "sesi_jadwal_umum", length = 5)
    private Integer sesiJadwal;

    @Column(name = "status_jadwal_umum", length = 5)
    private String status;
}