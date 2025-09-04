package com.learn.springboot.exception.model;


import java.util.List;

public class ListResponse<T> extends Response<List<T>> {
    private Meta meta;

    public ListResponse(int code, int status, List<T> data, String message, String traceId, String path, Meta meta) {
        super(code, status, data, message, traceId, path);
        this.meta = meta;
    }

    // getters and setters
}

