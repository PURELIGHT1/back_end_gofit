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

    // @OneToMany(mappedBy = "booking_kelas")
    // private Set<PresensiKelas> presensiKelas;

    @Id
    @Column(name = "id_booking_kelas", length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_member", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "id_kelas", nullable = false)
    private Kelas kelas;

    @Column(name = "tgl_booking_kelas")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglBooking;

    @Column(name = "status_booking_kelas")
    private Boolean status;
}
