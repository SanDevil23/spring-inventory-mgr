package com.oms.inventory_service.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class APIResponse {

    private String message;
    private Object data;

    private APIResponse() {
    }

    public static APIResponse success(String message, Object data) {
        return new APIResponse()
                .setMessage(message)
                .setData(data);
    }

    public static APIResponse error(String message) {
        return new APIResponse()
                .setMessage(message);
    }

    private APIResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    private APIResponse setData(Object data) {
        this.data = data;
        return this;
    }

}