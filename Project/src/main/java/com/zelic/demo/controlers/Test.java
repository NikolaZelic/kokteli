package com.zelic.demo.controlers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zelic.demo.utilities.PasswordEncoder;
import com.zelic.demo.utilities.messages.MessagesConfiguration;
import com.zelic.demo.utilities.messages.StandardMessage;
import com.zelic.demo.utilities.messages.StandardMessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/test")
public class Test {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MessagesConfiguration messagesConfiguration;

    @GetMapping
    public StandardMessage test(){
        StandardMessage message = messagesConfiguration.standardMessage(StandardMessageStatus.OK);
        message.addData("asdf", "asjdjhad");
        return message;
    }

    @PostMapping
    public void test2(@RequestBody ObjectNode objectNode){
        System.out.println(objectNode);

    }
}


class A implements JsonSerializable {
    private String a, b;

    public A(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "{" +
                "\"a\": \"" + a + '"' +
                '}';
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("a");
        gen.writeString(a);
        gen.writeEndObject();
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
//        gen.writeStartObject();
//        gen.writeFieldName("a");
//        gen.writeString(a);
//        gen.writeEndObject();
    }
}