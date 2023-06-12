package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.models.entities.PresensiKelas;

public interface PresensiKelasRepo extends JpaRepository<PresensiKelas, Integer> {

    // @Query("SELECT pr FROM promo pr WHERE pr.jenis = ?1")
    // public List<PresensiMemberCustomeResponse> findAllPresensiKelas(String
    // jenis);

    // @Query("select count(pk.id) from PresensiKelas pk where cast(pk.bookingKelas
    // as text) = ?1 and ")
    // public Integer getJlhPresensi(String booking);

    // @Query("SELECT pk FROM PresensiKelas pk WHERE pk.member = ?1")
    // public List<PresensiKelas> findAllPresensiKelasMember(Member member);

}
