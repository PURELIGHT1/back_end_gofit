package com.api.models.entities;

import java.math.BigDecimal;
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
@Table(name = "transaksi_deposit_uang")
public class TransaksiDepositUang {
    @Id
    @Column(name = "id_deposit_uang", length = 20)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_pegawai", nullable = false)
    private Pegawai pegawai;

    @ManyToOne
    @JoinColumn(name = "id_member", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "id_promo", nullable = false)
    private Promo promo;

    @Column(name = "jlh_deposit_uang")
    private BigDecimal jlhDeposit;

    @Column(name = "tgl_deposit_uang")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglDeposit;

    @Column(name = "total_deposit_uang")
    private BigDecimal totalDeposit;

    @Column(name = "status_deposit_uang")
    private Boolean status;
}
