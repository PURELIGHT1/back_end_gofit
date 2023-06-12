package com.api.implement.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.BookingGymResponse;
import com.api.dto.ProfileMemberResponse;
import com.api.dto.ProfileMemberResponse2;
import com.api.implement.BookingGymImpl;
import com.api.implement.BookingKelasImpl;
import com.api.implement.DepositKelasImpl;
import com.api.implement.DepositUangImpl;
import com.api.implement.MemberImpl;
import com.api.implement.PresensiGymImpl;
// import com.api.implement.PresensiKelasImpl;
import com.api.implement.TransaksiAktivasiImpl;
import com.api.models.entities.BookingGym;
import com.api.models.entities.BookingKelas;
import com.api.models.entities.Member;
import com.api.models.entities.PresensiGym;
import com.api.models.entities.TransaksiAktivasi;
import com.api.models.entities.TransaksiDepositKelas;
import com.api.models.entities.TransaksiDepositUang;

@Service
public class ProfileMemberImpl {

    @Autowired
    private MemberImpl memberImpl;

    @Autowired
    private BookingGymImpl bookingGymImpl;

    @Autowired
    private BookingKelasImpl bookingKelasImpl;

    @Autowired
    private PresensiGymImpl presensiGymImpl;

    // @Autowired
    // private PresensiKelasImpl presensiKelasImpl;

    @Autowired
    private TransaksiAktivasiImpl transaksiAktivasiImpl;

    @Autowired
    private DepositUangImpl depositUangImpl;

    @Autowired
    private DepositKelasImpl depositKelasImpl;

    public ProfileMemberResponse getAll(String id) {
        ProfileMemberResponse DB = new ProfileMemberResponse();

        // Member memberDB = memberImpl.findByIdMember(id);
        List<BookingGym> listBookingGym = bookingGymImpl.findAllById(id);
        // List<PresensiGym> listPresensiGyym = presensiGymImpl.findAllByMember(id);
        List<BookingKelas> listBookingKelas = bookingKelasImpl.findAllByMember(id);
        // List<PresensiGym> listPresensiGym = presensiGymImpl.findAllByMember(id);
        // List<PresensiKelas> listPresensiKelas =
        // presensiKelasImpl.findAllByMember(id);
        List<TransaksiAktivasi> listTransaksiAktivasi = transaksiAktivasiImpl.findByMember(id);
        List<TransaksiDepositKelas> listTransaksiDepositKelas = depositKelasImpl.findAllByMember(id);
        List<TransaksiDepositUang> listTransaksiDepositUang = depositUangImpl.findAllByMember(id);

        BookingGymResponse bookingGymResponse = new BookingGymResponse();
        bookingGymResponse.setListBookingGym(listBookingGym);
        // bookingGymResponse.setListPresensiGym(listPresensiGyym);
        // DB.builder()
        // .member(memberDB)
        // .listBookingGym(listBookingGym)
        // .listBookingKelas(listBookingKelas)
        // .listPresensiGym(listPresensiGym)
        // .listPresensiKelas(listPresensiKelas)
        // .transaksiAktivasi(transaksiAktivasi)
        // .listTransaksiDepositUang(listTransaksiDepositUang)
        // .listTransaksiDepositKelas(listTransaksiDepositKelas)
        // .build();
        // DB.setBookingGym(bookingGymResponse);
        DB.setListBookingGym(listBookingGym);
        DB.setListBookingKelas(listBookingKelas);
        // DB.setListPresensiGym(listPresensiGym);
        // DB.setListPresensiKelas(listPresensiKelas);
        DB.setListTransaksiAktivasi(listTransaksiAktivasi);
        DB.setListTransaksiDepositUang(listTransaksiDepositUang);
        DB.setListTransaksiDepositKelas(listTransaksiDepositKelas);
        return DB;
    }

    public ProfileMemberResponse2 getAll2(String id) {
        ProfileMemberResponse2 DB = new ProfileMemberResponse2();

        Member memberDB = memberImpl.findByIdMember(id);

        TransaksiAktivasi transaksiAktivasi = transaksiAktivasiImpl.findAllByMember(id);
        ArrayList<TransaksiDepositKelas> listTransaksiDepositKelas = depositKelasImpl.findAllByMember(id);
        if (listTransaksiDepositKelas.size() == 0) {
            DB.setMember(memberDB);
            DB.setTransaksiAktivasi(transaksiAktivasi);
            DB.setJenisPaket("Tidak ada paket");
            DB.setSisaPaket(0);
        } else {
            DB.setMember(memberDB);
            DB.setTransaksiAktivasi(transaksiAktivasi);
            DB.setJenisPaket(listTransaksiDepositKelas.get(0).getKelas().getNama());
            DB.setSisaPaket(listTransaksiDepositKelas.get(0).getSisaKelas());
        }
        // DB.builder()
        // .member(memberDB)
        // .listBookingGym(listBookingGym)
        // .listBookingKelas(listBookingKelas)
        // .listPresensiGym(listPresensiGym)
        // .listPresensiKelas(listPresensiKelas)
        // .transaksiAktivasi(transaksiAktivasi)
        // .listTransaksiDepositUang(listTransaksiDepositUang)
        // .listTransaksiDepositKelas(listTransaksiDepositKelas)
        // .build();

        return DB;
    }
}
