package com.casestudy.dbmonitor.back.serviceMock.controller;

import com.casestudy.dbmonitor.back.serviceMock.service.MockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mockProject")
@RequiredArgsConstructor
public class SimpleController {
    private final MockService mockService;

    @GetMapping("/insert")
    public String insert() {
        String id = mockService.addInbound();
        return "ID: " + id;
    }

    @GetMapping("/correctProcessing")
    public String correctProcessing() {
        String id = mockService.correctProcessing();
        return "ID: " + id;
    }

    @GetMapping("/invalidProcessing")
    public String invalidProcessing() {
        String id = mockService.invalidProcessing();
        return "ID: " + id;
    }
}
