package com.api.dto;

import java.util.List;

import com.api.models.entities.BookingGym;
import com.api.models.entities.BookingKelas;
import com.api.models.entities.PresensiGym;
import com.api.models.entities.PresensiKelas;
import com.api.models.entities.TransaksiAktivasi;
import com.api.models.entities.TransaksiDepositKelas;
import com.api.models.entities.TransaksiDepositUang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileMemberResponse {
    // private Member member;
    // private BookingGymResponse BookingGym;
    private List<BookingGym> listBookingGym;
    private List<BookingKelas> listBookingKelas;
    private List<PresensiGym> listPresensiGym;
    private List<PresensiKelas> listPresensiKelas;
    private List<TransaksiAktivasi> listTransaksiAktivasi;
    private List<TransaksiDepositKelas> listTransaksiDepositKelas;
    private List<TransaksiDepositUang> listTransaksiDepositUang;
}
