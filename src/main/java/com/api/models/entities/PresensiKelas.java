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
@Table(name = "presensi_kelas")
public class PresensiKelas {

    @Id
    @Column(name = "id_presensi_kelas", length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_booking_kelas", nullable = false)
    private BookingKelas bookingKelas;

    // @ManyToOne
    // @JoinColumn(name = "id_instruktur", nullable = false)
    // private Instruktur instruktur;

    // @ManyToOne
    // @JoinColumn(name = "id_member", nullable = false)
    // private Member member;

    // @Column(name = "tgl_booking_kelas")
    // @Temporal(TemporalType.DATE)
    // private Date tglBooking;

    @Column(name = "tgl_presensi_kelas")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglpresensi;

    @Column(name = "cretor_presensi")
    private String creator;

    @Column(name = "modifier_presensi")
    private Integer modifier;

    @Column(name = "modified_time_presensi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified_time;

    @Column(name = "status_presensi_kelas", length = 5)
    private String status;
}
