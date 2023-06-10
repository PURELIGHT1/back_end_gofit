package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Member;
import com.api.models.entities.TransaksiDepositUang;

public interface DepositUangRepo extends JpaRepository<TransaksiDepositUang, String> {

    @Query("select du from TransaksiDepositUang du where du.member = ?1 order by du.tglDeposit desc")
    public List<TransaksiDepositUang> findAllTransaksiDepositKelas(Member member);

    @Query("select sum(du.jlhDeposit) from TransaksiDepositUang du where du.status = 'P' and extract(month from du.tglDeposit) = ?1 and extract(year from du.tglDeposit) = ?2")
    public Integer findLaporanPendapatan(Integer bulan, Integer tahun);
}
