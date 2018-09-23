package com.casestudy.dbmonitor.back.controller;

import com.casestudy.dbmonitor.back.entitiy.BodyParamsHandler;
import com.casestudy.dbmonitor.back.entitiy.InitInfo;
import com.casestudy.dbmonitor.back.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class GtbController {
    final TransactionService transactionService;

    @GetMapping("/test")
    public Object test(@RequestParam String id) {
        Map<String, Object> result = transactionService.getTransactionInfo("e2e", "Full way", id);
        return result;
    }

    @GetMapping("/transactionInfo")
    public Object getTransactionInfo(@RequestParam String env, @RequestParam String flow, @RequestParam String id) {
        Map<String, Object> result = transactionService.getTransactionInfo(env, flow, id);
        return result;
    }

    @PostMapping("/transactionInfo")
    public Object getTransactionInfoPost(@RequestBody BodyParamsHandler requestBody) {
        Map<String, Object> result = transactionService.getTransactionInfo(requestBody.getEnv(), requestBody.getFlow(), requestBody.getId());
        log.info(result);
        return result;
    }
    
    @PostMapping("/initInfo")
    public InitInfo getInitInfo() {
        return transactionService.getInitInfo();
    }
}
