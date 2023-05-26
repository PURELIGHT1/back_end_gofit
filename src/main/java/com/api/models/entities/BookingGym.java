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

@Entity
@Data
@Table(name = "booking_gym")
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class BookingGym {

    @Id
    @Column(name = "id_booking_gym", length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_member", nullable = false)
    private Member member;

    @Column(name = "tgl_booking_gym")
    // @Temporal(TemporalType.DATE)
    private String tglBooking;

    @Column(name = "tgl_create_booking_gym")
    @Temporal(TemporalType.DATE)
    private Date tglBuat;

    @Column(name = "sesi_booking_gym", length = 5)
    private Integer sesi;

    @Column(name = "status_booking_gym", length = 5)
    private String status;
}
