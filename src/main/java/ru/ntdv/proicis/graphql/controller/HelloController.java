package ru.ntdv.proicis.graphql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.TimeZone;

import static java.time.OffsetDateTime.now;

@Controller
public
class HelloController {
@RequestMapping(path = "/getTime")
public
String getCurrentUserDateTime(TimeZone timeZone) {
    return now(timeZone.toZoneId()).toString();
}
}
