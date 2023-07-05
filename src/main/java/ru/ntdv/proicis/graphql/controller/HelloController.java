package ru.ntdv.proicis.graphql.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.OffsetDateTime;
import java.util.TimeZone;

import static java.time.OffsetDateTime.now;

@Controller
public
class HelloController {
//    @Autowired
//    private HttpServletRequest request;
@QueryMapping
public static
OffsetDateTime getCurrentUserDateTime(TimeZone timeZone) {
    return now(timeZone.toZoneId());
}
}
