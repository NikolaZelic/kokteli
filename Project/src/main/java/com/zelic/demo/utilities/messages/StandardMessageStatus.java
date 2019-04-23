package com.zelic.demo.utilities.messages;

public enum StandardMessageStatus {
    OK (200, "OK"),
    INVALID_REQUEST (400, "ERR");

    private int statusCode;
    private String defaultMessage;

    StandardMessageStatus(int statusCode){
        this.statusCode = statusCode;
    }

    StandardMessageStatus(int statusCode, String defaultMessage) {
        this.statusCode = statusCode;
        this.defaultMessage = defaultMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
