package com.example.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RestBean<T> {
    int code;
    String reason;
    List<T> data;

    public RestBean(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

}
