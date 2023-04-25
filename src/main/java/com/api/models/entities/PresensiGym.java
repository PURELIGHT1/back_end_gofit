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
@Table(name = "presensi_gym")
public class PresensiGym {

    @Id
    @Column(name = "id_presensi_gym", length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_booking_gym", nullable = false)
    private BookingGym bookingGym;

    @ManyToOne
    @JoinColumn(name = "id_pegawai", nullable = false)
    private Pegawai pegawai;

    @ManyToOne
    @JoinColumn(name = "id_member", nullable = false)
    private Member member;

    @Column(name = "tgl_presensi_gym")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglpresensi;

    @Column(name = "sesi_mulai_presensi")
    private String mulaiGym;

    @Column(name = "sesi_akhir_presensi")
    private String akhirGym;

    @Column(name = "status_presensi_gym")
    private Boolean status;
}
