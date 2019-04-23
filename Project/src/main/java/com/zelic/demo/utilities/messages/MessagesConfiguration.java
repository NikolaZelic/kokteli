package com.zelic.demo.utilities.messages;


import org.springframework.stereotype.Component;

@Component
public class MessagesConfiguration {

    public StandardMessage standardMessage(StandardMessageStatus status){
        return new SimpleStandardMessage(status);
    }

    public StandardMessage standardMessage(StandardMessageStatus status, String message){
        return new SimpleStandardMessage(status, message);
    }
}
