package ru.ntdv.proicis.graphql.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.TimeZone;


import static java.time.OffsetDateTime.now;
import static java.time.OffsetDateTime.of;

@RestController
public
class HelloController {
    @RequestMapping(path = "/getTime")
    public
    String getCurrentUserDateTime(TimeZone timeZone) {
        OffsetDateTime data = now(timeZone.toZoneId());
        return "<h1>Is this work?</h1>";
//        return Date.from(now(timeZone.toZoneId()).toInstant()).toString();
    }
}
