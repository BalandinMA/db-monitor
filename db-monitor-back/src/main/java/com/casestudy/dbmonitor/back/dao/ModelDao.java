package com.casestudy.dbmonitor.back.dao;


import com.casestudy.dbmonitor.back.entitiy.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


@Repository
@AllArgsConstructor
@Log4j2
public class ModelDao {
    @PersistenceContext
    private EntityManager entityManager;

    private final Map<String, List<Table>> schemaMap;

    public Map<String, List<Map<String, String>>> getTransaction(String schemaName, String id) {
        List<Table> tables = schemaMap.get(schemaName);
        Map<String, List<Map<String, String>>> resultTablesNormal = new LinkedHashMap<>(16, 0.75f, true);
        boolean firstTable;
        ObjectMapper oMapper = new ObjectMapper();
        tables.forEach(table -> {
            Query query = makeQuery(id, table, resultTablesNormal);
            if (query != null) {
                query.getResultList().forEach(row -> {
                    List oneRow = oMapper.convertValue(row, List.class);
                    Map<String, List<String>> resultTable;
                    List<Map<String, String>> resultTableNormal;
                    if (resultTablesNormal.get(table.getName()) == null) {
                        resultTable = new LinkedHashMap<>(16, 0.75f, true);
                        resultTablesNormal.put(table.getName(), new ArrayList<>());
                        table.getColumns().forEach(columnName -> resultTable.put(columnName, new ArrayList<>()));
                    }
                    Map<String, String> rowData = new LinkedHashMap<>(16, 0.75f, true);
                    int i = 0;
                    for (String columnName : table.getColumns()) {
                        rowData.put(columnName, String.valueOf(oneRow.get(i)));
                        i++;
                    }
                    resultTablesNormal.get(table.getName()).add(rowData);
                });
            }
        });
        return resultTablesNormal;
    }

    private Query makeQuery(String id, Table table, Map<String, List<Map<String, String>>> resultTables) {
        String columns = String.join(", ", table.getColumns());
        String select = "SELECT " + columns + " FROM " + table.getName() + " ";
        String join = "";
        String joinCondition = "";
        String idCondition = "";
        if (table.getBy() == null) {
            idCondition = " WHERE " + table.getWhere() + " IN ('" + id + "')";
        } else if (resultTables.get(table.getBy().getTable()) != null) {
            join = ", " + table.getBy().getTable();
            joinCondition = " WHERE " + table.getName() + "." + table.getWhere()
                    + "=" + table.getBy().getTable() + "." + table.getBy().getColumn() + " AND ";
            Set<String> valuesSet = new HashSet<>();
            resultTables.get(table.getBy().getTable()).forEach(row -> valuesSet.add(row.get(table.getBy().getColumn())));
            String inList = String.join("', '", valuesSet);
            idCondition = table.getWhere() + " IN ('" + inList + "')";
        } else
            return null;
        log.info(select + join + joinCondition + idCondition);
        return entityManager.createNativeQuery(select + join + joinCondition + idCondition);
    }
}
