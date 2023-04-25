package com.api.models.entities;

import java.math.BigDecimal;
// import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "kelas")
public class Kelas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kelas")
    private Integer id;

    @Column(name = "nama_kelas", length = 100)
    private String nama;

    @Column(name = "harga_kelas")
    private BigDecimal harga;

    @Column(name = "slot_kelas")
    private Integer slot;
}
