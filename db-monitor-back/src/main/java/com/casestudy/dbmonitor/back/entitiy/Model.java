package com.casestudy.dbmonitor.back.entitiy;

import lombok.Data;

import java.util.List;

@Data
public class Model {
    String name;
    String input;
    List<Table> tables;
}
