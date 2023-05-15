package com.api.models.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "id_member", length = 20)
    private String id;

    @Column(name = "nama_member", length = 50)
    private String nama;

    @Column(name = "foto_member")
    private String foto;

    @Column(name = "email_member", length = 100)
    private String email;

    @Column(name = "alamat_member", length = 100)
    private String alamat;

    @Column(name = "tgl_lahir_member")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;

    @Column(name = "no_telp_member", length = 20)
    private String noHp;

    @Column(name = "sisa_deposit_member", length = 255)
    private Integer sisaDeposit;

    @Column(name = "status_member", length = 5)
    private String status;

    @Column(name = "cretor_member")
    private String creator;

    @Column(name = "modifier_member")
    private Integer modifier;

    @Column(name = "modified_time_member")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified_time;
}
