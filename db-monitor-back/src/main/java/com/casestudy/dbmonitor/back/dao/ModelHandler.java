package com.casestudy.dbmonitor.back.dao;

import com.casestudy.dbmonitor.back.entitiy.Table;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ModelHandler {
    private final Map<String, List<Table>> map;

    public List<Table> getByName(String name) {
        return map.get(name);
    }
}
