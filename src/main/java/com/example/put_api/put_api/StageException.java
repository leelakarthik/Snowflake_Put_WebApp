package com.example.put_api.put_api;

public class StageException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String msg;

    public StageException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
