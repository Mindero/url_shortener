package org.example.exception;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private String msg;

    public ApiError(HttpStatus status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
