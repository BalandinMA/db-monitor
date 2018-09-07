package com.casestudy.dbmonitor.back.entitiy;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class InitInfo {
    List<String> flows;
    List<String> environments;
    Map<String,String> colors;

}
