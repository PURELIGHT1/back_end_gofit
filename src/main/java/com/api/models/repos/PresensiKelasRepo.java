package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.dto.PresensiMemberCustomeResponse;
import com.api.models.entities.BookingKelas;
import com.api.models.entities.Kelas;
import com.api.models.entities.Member;
import com.api.models.entities.PresensiKelas;

public interface PresensiKelasRepo extends JpaRepository<PresensiKelas, Integer> {

    @Query("SELECT pr FROM promo pr WHERE pr.jenis = ?1")
    public List<PresensiMemberCustomeResponse> findAllPresensiKelas(String jenis);

    @Query("select count(bk.id) from BookingKelas bk where CAST(bk.tglBooking as text) = ?1 and CAST(bk.kelas as int) = ?2")
    public Integer getSlotMember(String tgl, Integer kelas);

    @Query("SELECT pk FROM PresensiKelas pk WHERE pk.member = ?1")
    public List<PresensiKelas> findAllPresensiKelasMember(Member member);

}
