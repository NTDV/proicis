package ru.ntdv.proicis.graphql.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.TimeZone;


import static java.time.OffsetDateTime.now;

@RestController
public
class HelloController {
    @RequestMapping(value = "/getTime", method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    public
    String getCurrentUserDateTime(TimeZone timeZone) {
        OffsetDateTime data = now(timeZone.toZoneId());
        //Я не смог найти простого способа для перевода и выбора для раставления склонений, и решил, что в данном случае
        // рациональнее просто через условия :(
        String month = "ошибка";
        switch (data.getMonth()){
            case JANUARY -> month="Января";
            case FEBRUARY -> month="Февраля";
            case MARCH -> month="Марта";
            case APRIL -> month="Апреля";
            case MAY -> month="Мая";
            case JUNE ->month="Июня";
            case JULY -> month="Июля";
            case AUGUST -> month="Августа";
            case SEPTEMBER -> month="Сентября";
            case OCTOBER -> month="Октября";
            case NOVEMBER -> month="Ноября";
            case DECEMBER -> month="Декабря";
        }
        return "<!DOCTYPE html>" +
                "<html lang=\"ru\">" +
                "<meta content='text/html; charset=UTF-8' http-equiv='Content-Type'/>" +
                "  <body>\n" +
                    "<p>" +"Текущее время: "+ data.getHour() + ":" + data.getMinute() + "</p>" +
                    "<p>" +"Дата: "+ data.getDayOfMonth() + " " + month + " " + data.getYear() +"</p>" +
                    "<p>"+ "English" +"</p>" +
                "  </body>\n" +
                "</html>";
//        return "<p>" + data.getHour() + ":" + data.getMinute() + " "
//                + data.getDayOfMonth() + " " + data.getMonth() + " " + data.getYear() + "</p>";
//        return Date.from(now(timeZone.toZoneId()).toInstant()).toString();
    }
}
