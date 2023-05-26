package com.api.dto;

import com.api.models.entities.JadwalHarian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresensiMemberCustomeResponse {
    /*
     * private String idBookingkelas;
     * private String idPresensi;
     * private String idJadwalharian;
     * private BookingKelas bookingKelas;
     * private PresensiKelas presensiKelas;
     */
    private JadwalHarian jadwal;

    // private Instruktur instruktur;
    // private Date statusPresensiKelasSendiri;
    private String statusPresensiKelasSendiri;
    private Integer jumlahMemberKelas;
}
