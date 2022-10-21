package com.im.port.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 오류 발생시에 대한 컨트롤러 입니다.
 * **/
@Slf4j
@Controller
public class ExceptionController implements ErrorController   {
    /**
     * 오류를 처리합니다.
     * **/
    @RequestMapping(value = "/error")
    public String handleNoHandlerFoundException(HttpServletResponse response, HttpServletRequest request) {
        int status = response.getStatus();
        log.info(" ##### ExceptionController");
        log.info(" ### status : " + status);
        log.info(" ### getRequestURI : " + request.getRequestURI());
    	
        if (Objects.equals(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            // Map<String, Object> body = Map.of("error", "Not Found", "timestamp", System.currentTimeMillis());
            log.info(" ##### Error ");
            return "index";
        }
        return "index";
    }
}