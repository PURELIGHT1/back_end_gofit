package com.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseData2<T> {
    private Boolean status;
    private List<String> message = new ArrayList<>();
    private T data;
}
