package com.casestudy.dbmonitor.back.service;

import com.casestudy.dbmonitor.back.dao.InitInfoHandler;
import com.casestudy.dbmonitor.back.dao.ModelDao;
import com.casestudy.dbmonitor.back.dao.TranslationHandler;
import com.casestudy.dbmonitor.back.entitiy.InitInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionService {
    final ModelDao modelDao;
    final InitInfoHandler initInfoHandler;
    final TranslationHandler translationHandler;

    public Map<String, Object> getTransactionInfo(String envName, String schemaName, String id) {
        Map<String, List<Map<String, String>>> transactionDetails = modelDao.getTransactionDetails(envName, schemaName, id);
        Map<String, String> translationDetails = new HashMap<>();
        transactionDetails.forEach((tableName, rows) ->
                rows.forEach(row ->
                        row.forEach((column, value) -> {
                            String tableColumnValue = tableName + '.' + column + '.' + value;
                            String replacedValue = translationHandler.getTranslationMap().get(tableColumnValue);
                            if (replacedValue != null) {
                                translationDetails.put(tableColumnValue, replacedValue);
                            }
                        }))
        );
        Map<String, Object> transactionInfo = new LinkedHashMap<>();
        transactionInfo.put("transactionDetails", transactionDetails);
        transactionInfo.put("translationDetails", translationDetails);
        return transactionInfo;
    }

    public InitInfo getInitInfo() {
        return initInfoHandler.getInitInfo();
    }
}
