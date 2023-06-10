package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Member;
import com.api.models.entities.TransaksiAktivasi;

public interface TransaksiAktivasiRepo extends JpaRepository<TransaksiAktivasi, String> {

    @Query("SELECT t FROM TransaksiAktivasi t WHERE t.status = 'A'")
    public List<TransaksiAktivasi> findAllAktivasi();

    @Query("SELECT t FROM TransaksiAktivasi t WHERE t.member = ?1")
    public List<TransaksiAktivasi> findAllNonAktivasi(Member member);

    @Query("SELECT ta from TransaksiAktivasi ta WHERE ta.member = ?1 order by ta.tglBerlaku desc limit 1")
    public TransaksiAktivasi findTAMember(Member member);

    @Query("SELECT ta from TransaksiAktivasi ta WHERE ta.member = ?1 order by ta.tglBerlaku desc")
    public List<TransaksiAktivasi> findAllTAMember(Member member);

    @Query("select sum(ta.jlhBayar) from TransaksiAktivasi ta where ta.status = 'P' and extract(month from ta.tglAktiviasi) = ?1 and extract(year from ta.tglAktiviasi) = ?2")
    public Integer findLaporanPendapatan(Integer bulan, Integer tahun);
}
