package ru.ntdv.proicis.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.RequestContextUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.TimeZone;


import static java.time.OffsetDateTime.now;

@Controller
public class HelloController {
//    @Autowired
//    private HttpServletRequest request;
    @QueryMapping
    public Date getCurrentUserDateTime(){
        OffsetDateTime offsetDateTime = now((LocaleContextHolder.getTimeZone()).toZoneId());
        Date date = Date.from(offsetDateTime.toInstant());
        return date; // не уверен что стоит переводить конткретно в этот формат но тут более наглядно.
    }
}
