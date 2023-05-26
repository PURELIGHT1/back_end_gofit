package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalHarian;
import com.api.models.entities.PresensiInstruktur;

import java.util.Date;
import java.util.List;

public interface PresensiInstrukturRepo extends JpaRepository<PresensiInstruktur, String> {
        @Query("SELECT pi From PresensiInstruktur pi where pi.status='PE' and pi.keterangan = 'presensi_izin'")
        public List<PresensiInstruktur> findPresensiIzin();

        @Query("SELECT pi From PresensiInstruktur pi where pi.instruktur= ?1 and pi.keterangan = 'presensi_kelas'")
        public List<PresensiInstruktur> findPresensiIzinById(Instruktur instruktur);

        @Query("select jh from JadwalHarian jh where jh.instruktur=?1 and jh.tglJadwal =?2 and jh.sesiJadwal between ?3 and ?4")
        public List<JadwalHarian> findJadwalHarianByIdInsBysesi(Instruktur idIns, Date tgl, Integer sesiAwal,
                        Integer sesiAkhir);

        @Query("select pi from PresensiInstruktur pi where " +
                        "pi.instruktur = ?1 " +
                        "and pi.tglpresensi = ?2 " +
                        "and pi.mulaiGym = ?3 " +
                        "and pi.keterangan = ?4 " +
                        "and pi.status= ?5")
        public PresensiInstruktur findPresensiInstruktur(Instruktur idIns, String tgl, Integer sesiAwal, String ket,
                        String status);
}
