package com.team27.garbageSystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class ErrorController {

    @GetMapping("/error")
    public String home(){
        return "error";
    }


}

//garbage-system-team27