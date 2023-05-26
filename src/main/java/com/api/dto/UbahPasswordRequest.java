package com.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UbahPasswordRequest {
    private String passwordLama;
    private String passwordBaru;
}
