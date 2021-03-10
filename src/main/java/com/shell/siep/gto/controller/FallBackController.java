package com.shell.siep.gto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/message")
    public String test() {
        return "Delayed response ... Timeout Occurred Please try after some time ";
    }

}