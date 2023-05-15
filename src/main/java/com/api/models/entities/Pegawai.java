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

@Entity(name = "pegawai")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@ToString
@Table(name = "pegawai")
public class Pegawai {

    @Id
    @Column(name = "id_pegawai", length = 20)
    private String id;

    @Column(name = "nama_pegawai", length = 50)
    private String nama;

    @Column(name = "foto_pegawai")
    private String foto;

    @Column(name = "email_pegawai", length = 100)
    private String email;

    @Column(name = "alamat_pegawai", length = 300)
    private String alamat;

    @Column(name = "tgl_lahir_pegawai")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;

    @Column(name = "no_telp_pegawai", length = 20)
    private String noHp;

    @Column(name = "status_pegawai", length = 5)
    private String status;

    @Column(name = "cretor_pegawai")
    private String creator;

    @Column(name = "modifier_pegawai")
    private Integer modifier;

    @Column(name = "modified_time_pegawai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified_time;
}
