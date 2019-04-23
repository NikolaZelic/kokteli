package com.zelic.demo;

import org.mindrot.jbcrypt.BCrypt;

public class TestBCrypt {

    public static void main(String[] args) {
        String asdf = BCrypt.hashpw("asdf", BCrypt.gensalt(4));
        System.out.println(asdf);
    }
}
