package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.BookingGym;
import com.api.models.entities.Member;

public interface BookingGymRepo extends JpaRepository<BookingGym, String> {

    @Query("SELECT bg FROM BookingGym bg WHERE bg.status = 'S'")
    public List<BookingGym> findAllBookingGym();

    @Query("SELECT bg FROM BookingGym bg WHERE bg.member = ?1")
    public List<BookingGym> findAllBookingGymById(Member member);

    @Query("SELECT bg FROM BookingGym bg where bg.member = ?1 and (bg.tglBooking LIKE ?2% or bg.status LIKE ?2% or CAST(bg.sesi as text) LIKE ?2%)")
    public List<BookingGym> findAllBookingGymByCari(Member member, String cari);

    @Query("select count(bg.id) as slot from BookingGym bg where bg.tglBooking = ?1 and bg.sesi = ?2")
    public Integer getSlotMember(String tgl, Integer sesi);

    @Query("select bg from BookingGym bg where bg.sesi =?1 and bg.tglBooking = ?2 and member = ?3")
    public BookingGym cekBooking(Integer sesi, String tgl, Member member);

    // @Query("select 10-count(bg.id) as slot from BookingGym bg where bg.sesi =?1
    // and bg.tglBooking = ?2")
    // public BookingGym cekSlotPerBooking(String tgl, Integer sesi, Member member);

}
