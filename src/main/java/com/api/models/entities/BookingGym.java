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
@Table(name = "booking_gym")
public class BookingGym {

    // @OneToMany(mappedBy = "booking_gym")
    // private Set<PresensiGym> presensiGym;

    @Id
    @Column(name = "id_booking_gym", length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_member", nullable = false)
    private Member member;

    @Column(name = "tgl_booking_gym")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglBooking;

    @Column(name = "status_booking_gym")
    private Boolean status;
}
