package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalHarian;

import java.util.Date;
import java.util.List;

public interface JadwalHarianRepo extends JpaRepository<JadwalHarian, String> {

    @Query("select j from JadwalHarian j where cast(j.instruktur as text)= ?1")
    public List<JadwalHarian> findJadwalIns(String ins);

    @Query("select j from JadwalHarian j where cast(j.instruktur as text) = ?1 " +
            "and extract(month from j.tglJadwal) = ?2 " +
            "and extract(year from j.tglJadwal) =?3")
    public List<JadwalHarian> findJadwalInsByMonthAndYear(String ins, Integer bulan, Integer tahun);

    @Query("select j from JadwalHarian j where cast(j.kelas as int) = ?1 and extract(month from j.tglJadwal) = ?2 and extract(year from j.tglJadwal) =?3")
    public List<JadwalHarian> findJadwalKelas(Integer kelas, Integer bulan, Integer tahun);

    @Query("select j from JadwalHarian j where cast(j.kelas as int) = ?1 order by j.instruktur, j.instrukturPeganti asc")
    public List<JadwalHarian> findJadwalKelasInsASC(Integer kelas);

    @Query("SELECT count(j.id) FROM JadwalHarian j WHERE cast(j.kelas as int) = ?1 and j.status = 'L' and extract(month from j.tglJadwal) = ?2 and extract(year from j.tglJadwal) = ?3")
    public Integer getTotalLibur(Integer kelas, Integer bulan, Integer tahun);

    @Query("select j from JadwalHarian j where j.instruktur = ?1 or j.instrukturPeganti = ?1 order by j.tglJadwal desc, j.sesiJadwal asc")
    public List<JadwalHarian> findJadwalInsAll(Instruktur ins);

    @Query("select j from JadwalHarian j where CAST(j.kelas as int) = ?1 order by j.tglJadwal desc, j.sesiJadwal asc limit 1")
    public JadwalHarian findJadwalInsOne(Integer idKelas);

    @Query("select count(j) from JadwalHarian j where CAST(j.kelas as int) = ?1 and j.status = 'L'")
    public Integer findJadwalInsOneLibur(Integer idKelas);

    @Query("select j from JadwalHarian j where CAST(j.tglJadwal as text) between ?1 and ?2")
    public List<JadwalHarian> findJadwalSatuMinggu(String tglAwal, String tglAkhir);

    @Query("select j from JadwalHarian j where j.tglJadwal between ?1 and ?2 and j.instruktur = ?3")
    public List<JadwalHarian> findJadwalSatuMingguByInstruktur(Date tglAwal, Date tglAkhir, Instruktur ins);

    @Query("select j from JadwalHarian j where j.tglJadwal between ?1 and ?2 and j.instrukturPeganti = ?3")
    public List<JadwalHarian> findJadwalSatuMingguByInstrukturPegganti(Date tglAwal, Date tglAkhir, Instruktur ins);

    @Query("select j from JadwalHarian j where j.hariJadwal LIKE ?1%")
    public List<JadwalHarian> findByNama(String hari);

    @Query("select j from JadwalHarian j where j.tglJadwal =?1")
    public List<JadwalHarian> findJadwalPerhari(Date hariIni);

    // @Query("select j from JadwalHarian j where CAST(j.tglJadwal as text) =?1 and
    // j.status =?2 and j.hariJadwal =?4 and CAST(j.instruktur as text) =?4")
    // public List<JadwalHarian> findJadwalPresensi(String hariIni, String status,
    // String hari, String ins);

    @Query("select j from JadwalHarian j where CAST(j.tglJadwal as text) =?1 and j.status =?2 and j.sesiJadwal =?3 and j.hariJadwal =?4 and CAST(j.instruktur as text) =?5")
    public JadwalHarian findJadwalPresensi(String hariIni, String status, Integer sesi, String hari, String ins);

    @Query("select j from JadwalHarian j where j.tglJadwal =?1 and j.hariJadwal = ?2 order by j.sesiJadwal asc")
    public List<JadwalHarian> findAllBookingGymByDate(String date, String hari);

    @Query("select j from JadwalHarian j where j.tglJadwal = ?1 and j.hariJadwal = ?3 and (j.hariJadwal LIKE ?2% or CAST(j.sesiJadwal as text) LIKE ?2% or j.status LIKE ?2%) order by j.sesiJadwal asc")
    public List<JadwalHarian> findAllBookingGymByDateAndCari(String date, String cari, String hari);

    @Query("select j from JadwalHarian j where j.hariJadwal = ?1 and j.sesiJadwal = ?2 and j.instruktur = ?3 and CAST(j.tglJadwal as text) between ?4 and ?5")
    public JadwalHarian findJadwalHarianInstruktur(String hari, Integer sesi, Instruktur instruktur, String awal,
            String akhir);

}
