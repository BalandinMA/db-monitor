package com.casestudy.dbmonitor.back.entitiy;

import lombok.Data;

import java.util.List;

@Data
public class Table {
    String name;
    List<String> columns;
    String where;
    By by;
    String order;
}
