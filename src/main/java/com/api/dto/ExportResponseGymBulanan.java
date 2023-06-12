package com.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportResponseGymBulanan {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM YY")
    private Date tanggal;
    private Integer jlhMember;
}
