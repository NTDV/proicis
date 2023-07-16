package ru.ntdv.proicis.graphql.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.time.OffsetDateTime.now;
import static java.util.TimeZone.getTimeZone;

@RestController
public
class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private String getBodyTime(OffsetDateTime data){
        String month;
        switch (data.getMonth()){ // Возможно этот switch тоже следовало бы в отдельную функцию,
            case JANUARY -> month="Января"; // но в моём случае мне пока не надо
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
            default -> month="Ошибка";
        }
        return "<p>" + "Текущее время: " + data.getHour() + ":" + data.getMinute() + "</p>" +
                "<p>" + "Дата: " + data.getDayOfMonth() + " " + month + " " + data.getYear() + "</p>";
    }
    @RequestMapping(value = "/getTime")
    public ResponseEntity<String> getCurrentUserDateTime(TimeZone timeZone, @RequestParam(required = false, name = "timeZone") String ID) {
        logger.info("Starting getCurrentUserDateTime"); // а делают из логирования про информацию отдельную
        long startTime = System.nanoTime(); // функцию-обёртку с параметром названия функции? Ведь сам
        OffsetDateTime data; // код должен часто повторяться
        if(ID==null) {
            data = now(timeZone.toZoneId());
        } else {
            data = now((getTimeZone(ID)).toZoneId());
        }
        StringBuffer bodeTime= new StringBuffer(getBodyTime(data));
        if(data.getHour()%2==0){
            for(int i=0;i<999;++i){
                bodeTime.append(getBodyTime(data));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "text/html;charset=UTF-8");
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        logger.info("Completing getCurrentUserDateTime in " + duration + "ns");
        return new ResponseEntity<>("<!DOCTYPE html>" +
                "<html lang=\"ru\">" +
                "<meta content='text/html; charset=UTF-8' http-equiv='Content-Type'/>" +
                "  <body>" +
                bodeTime +
                "  </body>" +
                "</html>", headers, HttpStatus.OK);
    }
}
