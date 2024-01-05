package com.reclaite.servlet;

public class StatusResponse {

    private final int code;
    private final String message;

    public StatusResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
