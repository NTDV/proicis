package ru.ntdv.proicis.graphql.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
    @QueryMapping
    public String getHello(){ return "Hello";}
}
