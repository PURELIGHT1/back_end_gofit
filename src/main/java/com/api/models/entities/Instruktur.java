package com.api.models.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "instruktur")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name = "instruktur")
public class Instruktur {

    @Id
    @Column(name = "id_instruktur", length = 20)
    private String id;

    @Column(name = "nama_instruktur", length = 50)
    private String nama;

    @Column(name = "foto_instruktur")
    private String foto;

    @Column(name = "insial_instruktur", length = 7)
    private String inisial;

    @Column(name = "email_instruktur", length = 100)
    private String email;

    @Column(name = "alamat_instruktur", length = 100)
    private String alamat;

    @Column(name = "tgl_lahir_instruktur")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;

    @Column(name = "no_telp_instruktur", length = 20)
    private String noHp;

    @Column(name = "jumlah_hadir_instruktur", columnDefinition = "integer default 0")
    private Integer jlhHadir;

    @Column(name = "jumlah_libur_instruktur", columnDefinition = "integer default 0")
    private Integer jlhLibur;

    @Column(name = "jumlah_terlambat_instruktur", columnDefinition = "integer default 0")
    private Integer jlhTerlambat;

    @Column(name = "status_instruktur", length = 5)
    private String status;

    @Column(name = "cretor_instruktur")
    private String creator;

    @Column(name = "modifier_instruktur")
    private Integer modifier;

    @Column(name = "modified_time_instruktur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified_time;
}
