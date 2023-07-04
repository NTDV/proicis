package ru.ntdv.proicis.graphql.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.RequestContextUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.util.TimeZone;

@Controller
public class HelloController {
    @QueryMapping
    public TimeZone getCurrentUserDateTime(HttpServletRequest request){
        return RequestContextUtils.getTimeZone(request);
    }
}
