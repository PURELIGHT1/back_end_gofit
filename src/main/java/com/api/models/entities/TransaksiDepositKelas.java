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
@Table(name = "transaksi_deposit_kelas")
public class TransaksiDepositKelas {

    @Id
    @Column(name = "id_deposit_kelas", length = 20)
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

    @ManyToOne
    @JoinColumn(name = "id_kelas", nullable = false)
    private Kelas kelas;

    @Column(name = "tgl_deposit_kelas")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglDeposit;

    @Column(name = "tgl_berlaku_deposit_kelas")
    @Temporal(TemporalType.DATE)
    private Date tglBerlaku;

    @Column(name = "total_kelas")
    private Integer totalKelas;

    @Column(name = "sisa_kelas")
    private Integer sisaKelas;

    @Column(name = "total_deposit_kelas", length = 255)
    private Integer totalDeposit;

    @Column(name = "status_deposit_kelas", length = 5)
    private String status;
}
