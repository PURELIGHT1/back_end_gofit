package com.api.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGymBulanan {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM YY")
    private List<Date> tgl;
    private List<Integer> member;
}
