package com.api.dto;

import java.util.List;

import com.api.models.entities.BookingGym;
import com.api.models.entities.PresensiGym;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingGymResponse {

    private List<BookingGym> listBookingGym;
    private List<PresensiGym> listPresensiGym;
}
