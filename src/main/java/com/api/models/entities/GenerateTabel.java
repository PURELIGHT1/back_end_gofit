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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "generate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "generate")
public class GenerateTabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Generate_id_member")
    private Integer generateIdMember;

    @Column(name = "Generate_id_pegawai")
    private Integer generateIdPegawai;

    @Column(name = "Generate_id_instruktur")
    private Integer generateIdInstruktur;

    @Column(name = "Generate_struk")
    private Integer generateStruk;

    @Column(name = "Generate_jadwal")
    @Temporal(TemporalType.DATE)
    private Date generateJadwal;
}
