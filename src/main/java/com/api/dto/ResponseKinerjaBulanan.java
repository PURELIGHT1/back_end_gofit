package com.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseKinerjaBulanan {
    private List<String> ins;
    private List<Integer> hadir;
    private List<Integer> telat;

    // private List<List<String>> jh;
    // private List<List<String>> booking;

    // private List<List<Integer>> jlhHadir;
    // // private List<Integer> jlhLibur;
    // private List<List<Integer>> jlhTerlambat;

}
