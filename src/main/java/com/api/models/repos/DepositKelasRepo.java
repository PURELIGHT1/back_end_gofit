package com.api.models.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Member;
import com.api.models.entities.TransaksiDepositKelas;

public interface DepositKelasRepo extends JpaRepository<TransaksiDepositKelas, String> {

    @Query("select dk from TransaksiDepositKelas dk where dk.member = ?1 order by dk.tglDeposit desc limit 1")
    public TransaksiDepositKelas cekTransaksiDepositKelas(Member member);

    @Query("select dk from TransaksiDepositKelas dk where dk.member = ?1 order by dk.tglBerlaku desc")
    public ArrayList<TransaksiDepositKelas> findAllTransaksiDepositKelas(Member member);
}
