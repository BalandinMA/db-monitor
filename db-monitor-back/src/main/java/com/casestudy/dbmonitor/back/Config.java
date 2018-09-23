package com.casestudy.dbmonitor.back;

import com.casestudy.dbmonitor.back.dao.InitInfoHandler;
import com.casestudy.dbmonitor.back.dao.JdbcTemplateHolder;
import com.casestudy.dbmonitor.back.dao.ModelHandler;
import com.casestudy.dbmonitor.back.dao.TranslationHandler;
import com.casestudy.dbmonitor.back.entitiy.Environment;
import com.casestudy.dbmonitor.back.entitiy.InitInfo;
import com.casestudy.dbmonitor.back.entitiy.Model;
import com.casestudy.dbmonitor.back.entitiy.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Configuration
public class Config {

    @Bean
    @SneakyThrows
    public ModelHandler getModel() {
        ClassLoader classLoader = Config.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config/model.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Model[] models = objectMapper.readValue(inputStream, Model[].class);
        Map<String, List<Table>> modelMap = new HashMap<>();
        Arrays.stream(models).forEach(model -> modelMap.put(model.getName(), model.getTables()));
        return new ModelHandler(modelMap);
    }

    @Bean
    @SneakyThrows
    public JdbcTemplateHolder initDatasourceMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = Config.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config/environment.json");
        Environment[] environments = objectMapper.readValue(inputStream, Environment[].class);
        Map<String, JdbcTemplate> jdbcTemplateMap = new HashMap<>();
        Arrays.stream(environments).forEach(environment -> {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setUrl(environment.getUrl());
            dataSource.setUsername(environment.getUsername());
            dataSource.setPassword(environment.getPassword());
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplateMap.put(environment.getName(), jdbcTemplate);
        });
        return new JdbcTemplateHolder(jdbcTemplateMap);
    }

    @Bean
    @SneakyThrows
    public InitInfoHandler initInitInfo() {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = Config.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config/colors.json");
        Map<String, String> colorConfig = objectMapper.readValue(inputStream, Map.class);
        InitInfo initInfo = new InitInfo();
        initInfo.setColors(colorConfig);
        InputStream inputStreamEnvironment = classLoader.getResourceAsStream("config/environment.json");
        Environment[] environmentsData = objectMapper.readValue(inputStreamEnvironment, Environment[].class);
        List<String> environments = new ArrayList<>();
        Arrays.stream(environmentsData).forEach(environment -> environments.add(environment.getName()));
        initInfo.setEnvironments(environments);

        InputStream inputStreamModel = classLoader.getResourceAsStream("config/model.json");
        Model[] models = objectMapper.readValue(inputStreamModel, Model[].class);
        List<String> flows = new ArrayList<>();
        Arrays.stream(models).forEach(model -> flows.add(model.getName()));
        initInfo.setFlows(flows);
        return new InitInfoHandler(initInfo);
    }

    @Bean(name = "TranslationMap")
    @SneakyThrows
    public TranslationHandler initTranslationMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = Config.class.getClassLoader();
        InputStream tableColumnStream = classLoader.getResourceAsStream("config/translation.json");
        Map<String, String> tableColumnMap = objectMapper.readValue(tableColumnStream, Map.class);
        Map<String, String> translationMap = new HashMap<>();
        for (String tableColumn : tableColumnMap.keySet()) {
            String fileName = "config/" + tableColumnMap.get(tableColumn);
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .map(row -> row.split(","))
                    .forEach(dataArray -> translationMap.put(tableColumn + '.' + dataArray[0], dataArray[1]));
        }
        return new TranslationHandler(translationMap);
    }
}