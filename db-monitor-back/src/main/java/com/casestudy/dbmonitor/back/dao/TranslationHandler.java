package com.casestudy.dbmonitor.back.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class TranslationHandler {
    private final Map<String, String> translationMap;
}
