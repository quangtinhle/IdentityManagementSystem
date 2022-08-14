package com.example.egovernmentportal.controller;

import com.example.egovernmentportal.model.InformationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
public class Ageover18 {

    @GetMapping("/checks")
    public ResponseEntity<Void> redirect() {
            return ResponseEntity.status(HttpStatus.OK)
                    .location(URI.create("https://google.com"))
                    .build();
        }

    @GetMapping("/check1")
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "http://localhost:8080/");
        httpServletResponse.setStatus(200);
    }

    @GetMapping("/checkcu")
    public ModelAndView method() {
        InformationResponse res = new InformationResponse("Ketqua tu 8089", true);
        ModelAndView modelAndView = new ModelAndView("redirect:" + "http://localhost:8080/users/created","ketqua",res);
        return modelAndView;
    }




}
