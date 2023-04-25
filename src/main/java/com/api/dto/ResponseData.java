package com.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseData<T> {
    private boolean status;
    private List<String> message = new ArrayList<>();
    private T payload;
}
