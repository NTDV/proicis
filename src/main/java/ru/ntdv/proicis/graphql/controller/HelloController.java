package ru.ntdv.proicis.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.RequestContextUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;

@Controller
public class HelloController {
    @Autowired
    private HttpServletRequest request;
    @QueryMapping
    public OffsetDateTime getCurrentUserDateTime(){
        if(request == null){
            System.out.println("Нет запроса");
            return now();
        }
        return now((RequestContextUtils.getTimeZone(request)).toZoneId());
    }
}
