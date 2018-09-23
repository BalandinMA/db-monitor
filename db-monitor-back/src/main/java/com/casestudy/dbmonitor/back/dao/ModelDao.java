package com.casestudy.dbmonitor.back.dao;


import com.casestudy.dbmonitor.back.entitiy.Table;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
@RequiredArgsConstructor
@Log4j2
public class ModelDao {
    private final ModelHandler schemaMap;

    private final JdbcTemplateHolder jdbcTemplateHolder;

    public Map<String, List<Map<String, String>>> getTransactionDetails(String envName, String schemaName, String id) {
        List<Table> tables = schemaMap.getByName(schemaName);
        JdbcTemplate jdbcTemplate = jdbcTemplateHolder.getByName(envName);
        Map<String, List<Map<String, String>>> resultTablesNormal = new LinkedHashMap<>(16, 0.75f, false);
        if (tables == null || jdbcTemplate == null){
            return  resultTablesNormal;
        }
        tables.forEach(table -> {
            String query = makeQuery(id, table, resultTablesNormal);
            if (query != null) {
                jdbcTemplate.queryForList(query).forEach(row -> {
//                    if (resultTablesNormal.get(table.getName())==null){
//                        resultTablesNormal.put(table.getName(),new ArrayList<>());
//                    }
                    resultTablesNormal.computeIfAbsent(table.getName(), k -> new ArrayList<>());
                    Map<String, String> rowData = new LinkedHashMap<>(16, 0.75f, false);
                    for (String columnName : table.getColumns()) {
                        rowData.put(columnName, String.valueOf(row.get(columnName)));
                    }
                    resultTablesNormal.get(table.getName()).add(rowData);
                });
            }
        });
        return resultTablesNormal;
    }

    private String makeQuery(String id, Table table, Map<String, List<Map<String, String>>> resultTables) {
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
        return select + join + joinCondition + idCondition;
    }
}
