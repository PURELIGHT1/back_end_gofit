package com.api.models.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "promo")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name = "promo")
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promo")
    private Integer id;

    @Column(name = "nama_promo", length = 100)
    private String nama;

    @Column(name = "jenis_promo", length = 100)
    private String jenis;

    @Column(name = "mulai_promo")
    @Temporal(TemporalType.DATE)
    private Date mulai;

    @Column(name = "akhir_promo")
    @Temporal(TemporalType.DATE)
    private Date akhir;

    @Column(name = "deskripsi_promo", length = 500)
    private String deskripsi;

    @Column(name = "status_promo", length = 5)
    private String status;

}
