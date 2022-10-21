package com.im.port.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    
    @GetMapping({ "", "/", "/error" })
    public String index() {
        log.info(" ##### MainController index");
        // 머스테치 기본폴더 src/main/resources/
        // 뷰리졸버 설정 : templates (prefix), .mustache (suffix) 생략가능
        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        log.info(" ##### MainController loginSuccess ");
        return "redirect:/PortChat";
    }
    
}
