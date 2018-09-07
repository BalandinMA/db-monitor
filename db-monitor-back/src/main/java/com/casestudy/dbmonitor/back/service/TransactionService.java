package com.casestudy.dbmonitor.back.service;

import com.casestudy.dbmonitor.back.dao.InitInfoHandler;
import com.casestudy.dbmonitor.back.dao.ModelDao;
import com.casestudy.dbmonitor.back.entitiy.InitInfo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionService {
    final ModelDao modelDao;
    final InitInfoHandler initInfoHandler;

    public Map<String, List<Map<String, String>>> getTransactionInfo(String envName, String schemaName, String id) {
        Map<String, List<Map<String, String>>> resultTables = modelDao.getTransaction(envName,schemaName, id);
        return resultTables;
    }

    public InitInfo getInitInfo() {
        return initInfoHandler.getInitInfo();
    }
}
