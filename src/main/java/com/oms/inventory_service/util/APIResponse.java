package com.oms.inventory_service.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
public class APIResponse {

    private String message;
    private HttpStatus httpStatus;
    private Object data;

    private APIResponse() {
    }

    public static APIResponse success(String message, Object data, HttpStatus status) {
        return new APIResponse()
                .setMessage(message)
                .setHTTPStatus(status)
                .setData(data);
    }

    public static APIResponse error(String message, HttpStatus status) {
        return new APIResponse()
                .setMessage(message)
                .setHTTPStatus(status);
    }

    private APIResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    private APIResponse setData(Object data) {
        this.data = data;
        return this;
    }

    private APIResponse setHTTPStatus(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        return this;
    }

}