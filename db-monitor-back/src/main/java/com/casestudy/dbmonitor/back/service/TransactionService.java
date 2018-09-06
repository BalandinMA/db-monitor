package com.casestudy.dbmonitor.back.service;

import com.casestudy.dbmonitor.back.dao.ModelDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TransactionService {
    final ModelDao modelDao;

    public Map<String, List<Map<String, String>>> getTransactionInfo(String id) {
        Map<String, List<Map<String, String>>> resultTables = modelDao.getTransaction("Full way", id);
        return resultTables;
    }
}
