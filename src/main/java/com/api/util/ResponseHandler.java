package com.api.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseHandler {
    public static ResponseEntity<Object> responseEntity(String message, HttpStatus statusHttp, Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", statusHttp.value());
        response.put("data", responseObject);

        return new ResponseEntity<>(response, statusHttp);
    }
}
