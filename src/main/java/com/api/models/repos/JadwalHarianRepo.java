package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.JadwalHarian;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface JadwalHarianRepo extends JpaRepository<JadwalHarian, String> {

    @Query("select j from JadwalHarian j where j.tglJadwal between ?1 and ?2")
    public List<JadwalHarian> findJadwalSatuMinggu(LocalDate tglAwal, LocalDate tglAkhir);

    @Query("select j from JadwalHarian j where j.hariJadwal LIKE ?1%")
    public List<JadwalHarian> findByNama(String hari);

    @Query("select j from JadwalHarian j where j.tglJadwal =?1")
    public List<JadwalHarian> findJadwalPerhari(Date hariIni);
}
