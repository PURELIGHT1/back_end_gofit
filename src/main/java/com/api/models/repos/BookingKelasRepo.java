package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.BookingKelas;
import com.api.models.entities.JadwalHarian;
import com.api.models.entities.Member;

public interface BookingKelasRepo extends JpaRepository<BookingKelas, String> {

    @Query("SELECT bk FROM BookingKelas bk WHERE bk.jadwal = ?1")
    public List<BookingKelas> findAllBookingKelasJadwal(JadwalHarian jadwal);

    @Query("SELECT count(bk.id) FROM BookingKelas bk WHERE bk.jadwal = ?1")
    public Integer findAllMemberBooking(JadwalHarian jadwal);

    @Query("SELECT bk FROM BookingKelas bk WHERE CAST(bk.jadwal as text) = ?1")
    public List<BookingKelas> findAllBookingJadwal(String jadwal);
    // @Query("SELECT count(bk.id) FROM BookingKelas bk WHERE cast(bk.jadwal as
    // text) = ?1")
    // public Integer findAllMember(String jadwal);

    @Query("SELECT bk FROM BookingKelas bk WHERE bk.member = ?1")
    public List<BookingKelas> findAllByMember(Member member);

}
