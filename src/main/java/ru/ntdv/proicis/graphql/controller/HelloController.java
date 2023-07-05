package ru.ntdv.proicis.graphql.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.time.OffsetDateTime;
import java.util.Objects;

import static java.time.OffsetDateTime.now;

@Controller
public
class HelloController {
@Autowired
private HttpServletRequest request;

@QueryMapping
public
OffsetDateTime getCurrentUserDateTime() {
    return now(Objects.requireNonNull(RequestContextUtils.getTimeZone(request)).toZoneId());
}
}
