package com.casestudy.dbmonitor.back.controller;

import com.casestudy.dbmonitor.back.entitiy.BodyParamsHandler;
import com.casestudy.dbmonitor.back.entitiy.InitInfo;
import com.casestudy.dbmonitor.back.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GtbController {
    final TransactionService transactionService;

    @GetMapping("/test")
    public Object test(@RequestParam String id) {
        Map<String, List<Map<String, String>>> result = transactionService.getTransactionInfo("e2e", "Full way", id);
        return result;
    }

    @GetMapping("/transactionInfo")
    public Object getTransactionInfo(@RequestParam String env, @RequestParam String flow, @RequestParam String id) {
        Map<String, List<Map<String, String>>> result = transactionService.getTransactionInfo(env, flow, id);
        return result;
    }

    @PostMapping("/transactionInfo")
    public Object getTransactionInfoPost(@RequestBody BodyParamsHandler requestBody) {
        Map<String, List<Map<String, String>>> result = transactionService.getTransactionInfo(requestBody.getEnv(), requestBody.getFlow(), requestBody.getId());
        return result;
    }


    @PostMapping("/initInfo")
    public InitInfo getInitInfo() {
        return transactionService.getInitInfo();
    }
}
