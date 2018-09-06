package com.casestudy.dbmonitor.back.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@RequiredArgsConstructor
public class JdbcTemplateHolder {
   private final Map<String, JdbcTemplate> map;

    public JdbcTemplate getByName(String name) {
        return map.get(name);
    }
}
