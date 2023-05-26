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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name = "transaksi_aktivasi")
public class TransaksiAktivasi {

    @Id
    @Column(name = "id_transaksi_aktivasi", length = 20)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_pegawai", nullable = false)
    private Pegawai pegawai;

    @ManyToOne
    @JoinColumn(name = "id_member", nullable = false)
    private Member member;

    @Column(name = "jlh_bayar_aktivasi", length = 255)
    private Integer jlhBayar;

    @Column(name = "tgl_aktivasi")
    @Temporal(TemporalType.DATE)
    private Date tglAktiviasi;

    @Column(name = "tgl_berlaku_aktivasi")
    @Temporal(TemporalType.DATE)
    private Date tglBerlaku;

    @Column(name = "status_aktivasi", length = 5)
    private String status;
}
