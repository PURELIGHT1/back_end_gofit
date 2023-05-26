package com.api.dto;

import java.util.Date;

import com.api.models.entities.BookingKelas;
import com.api.models.entities.Instruktur;
import com.api.models.entities.JadwalHarian;
import com.api.models.entities.PresensiKelas;

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
