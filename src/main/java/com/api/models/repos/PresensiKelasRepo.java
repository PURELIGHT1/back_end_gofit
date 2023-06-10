package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.models.entities.PresensiKelas;

public interface PresensiKelasRepo extends JpaRepository<PresensiKelas, Integer> {

    // @Query("SELECT pr FROM promo pr WHERE pr.jenis = ?1")
    // public List<PresensiMemberCustomeResponse> findAllPresensiKelas(String
    // jenis);

    // @Query("select count(bk.id) from BookingKelas bk where CAST(bk.tglBooking as
    // text) = ?1 and CAST(bk.kelas as int) = ?2")
    // public Integer getSlotMember(String tgl, Integer kelas);

    // @Query("SELECT pk FROM PresensiKelas pk WHERE pk.member = ?1")
    // public List<PresensiKelas> findAllPresensiKelasMember(Member member);

}
