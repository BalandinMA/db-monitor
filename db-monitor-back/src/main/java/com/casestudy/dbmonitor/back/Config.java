package com.casestudy.dbmonitor.back;

import com.casestudy.dbmonitor.back.entitiy.Model;
import com.casestudy.dbmonitor.back.entitiy.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class Config {
    @Bean
    @SneakyThrows
    public Map<String, List<Table>> getModel() {
//      todo:  set to module
        byte[] mapData = Files.readAllBytes(Paths.get("config/model.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        Model[] myModel = objectMapper.readValue(mapData, Model[].class);
        Map<String, List<Table>> modelMap = new HashMap<>();
        Arrays.stream(myModel).forEach(model -> modelMap.put(model.getName(), model.getTables()));
        return modelMap;
    }
}
