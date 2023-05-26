package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresensiGymBookingResponse {
    private String member;
    private String tglBooking;
    private Integer mulai;
    private Integer selesai;
    private String tglPresensi;
    private String status;
}
