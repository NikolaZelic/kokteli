package com.zelic.demo.utilities.messages;

import org.springframework.stereotype.Component;

@Component
public interface StandardMessage {

    void setStatus(StandardMessageStatus status);
    void setMessage(String message);
    void addError(String error);
    void addData(String key, Object value);
}
