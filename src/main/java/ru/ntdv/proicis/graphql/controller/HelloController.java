package ru.ntdv.proicis.graphql.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.TimeZone;


import static java.time.OffsetDateTime.now;

@RestController
public
class HelloController {
    @RequestMapping(path = "/getTime")
    public
    String getCurrentUserDateTime(TimeZone timeZone) {
        OffsetDateTime data = now(timeZone.toZoneId());
        return "<p>" + data.getHour() + ":" + data.getMinute() + " "
                + data.getDayOfMonth() + " " + data.getMonth() + " " + data.getYear() + "</p>";
//        return Date.from(now(timeZone.toZoneId()).toInstant()).toString();
    }
}
