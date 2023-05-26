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
@Table(name = "booking_kelas")
public class BookingKelas {

    @Id
    @Column(name = "id_booking_kelas", length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_member", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "id_kelas", nullable = false)
    private Kelas kelas;

    @ManyToOne
    @JoinColumn(name = "id_promo", nullable = true)
    private Promo promo;

    @Column(name = "tgl_booking_kelas")
    @Temporal(TemporalType.DATE)
    private Date tglBooking;

    @Column(name = "tgl_create_booking_kelas")
    @Temporal(TemporalType.DATE)
    private Date tglBuat;

    @ManyToOne
    @JoinColumn(name = "jadwal_booking_kelas", nullable = false)
    private JadwalHarian jadwal;

    @Column(name = "status_booking_kelas", length = 5)
    private String status;
}
