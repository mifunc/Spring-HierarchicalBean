package com.rumenz;


import org.springframework.context.annotation.Bean;

public class Config1 {

    @Bean
    public static Rumenz rumenz(){
        Rumenz r = new Rumenz();
        r.setId(123);
        r.setName("入门小站");
        return r;
    }
}
