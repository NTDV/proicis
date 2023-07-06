package ru.ntdv.proicis.graphql.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.TimeZone;


import static java.time.OffsetDateTime.now;

@RestController
public
class HelloController {
    @RequestMapping(path = "/getTime")
    public
    String getCurrentUserDateTime(TimeZone timeZone) {
        return Date.from(now(timeZone.toZoneId()).toInstant()).toString();
    }
}
