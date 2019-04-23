package com.zelic.demo.utilities.messages;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SimpleStandardMessage implements StandardMessage {

    private StandardMessageStatus status;
    private String message;
    private List<String> errors = new ArrayList<>();
    private Map<String, Object> data = new HashMap<>();

    @Override
    public void setStatus(StandardMessageStatus status) {
        this.status = status;
        if(status.getDefaultMessage()!=null)
            this.message = status.getDefaultMessage();
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void addError(String error) {
        errors.add(error);
    }

    @Override
    public void addData(String key, Object value) {
        data.put(key, value);
    }

    public SimpleStandardMessage() {
        setStatus(StandardMessageStatus.OK);
    }

    public SimpleStandardMessage(StandardMessageStatus status) {
        setStatus(status);
    }

    public SimpleStandardMessage(StandardMessageStatus status, String message) {
        setStatus(status);
        this.message = message;
    }

    public StandardMessageStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    @Override
    public String toString() {
        return "SimpleStandardMessage{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                ", data=" + data +
                '}';
    }

    public Map<String, Object> getData() {
        return Collections.unmodifiableMap(data);
    }

}
