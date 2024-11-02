package com.home.trupper.rest.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PruebaMain {

    //Esta clase la utilice para generar un password encriptado ----
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}
