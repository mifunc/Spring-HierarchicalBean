package com.rumenz;


import org.springframework.context.annotation.Bean;

public class Config {

    @Bean
    public SuperRumenz superRumenz(){
        SuperRumenz s=new SuperRumenz();
        s.setKey("超级管理员");
        return s;
    }
}
