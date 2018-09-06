package com.casestudy.dbmonitor.back.controller;

import com.casestudy.dbmonitor.back.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GtbController {
    final TransactionService transactionService;

    @GetMapping("/test")
    public String hello3(@RequestParam String id) {
        return "Hello " + id;
    }

    @GetMapping("/getTransactionInfo")
    public Object getTransactionInfo(@RequestParam String id) {
        Map<String, List<Map<String, String>>> result = transactionService.getTransactionInfo(id);
        return result;
    }
}
