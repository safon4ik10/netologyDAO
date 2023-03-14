package com.example.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class Repository {
    private final String query = read("/selectProducts.sql");

    @Autowired
    private NamedParameterJdbcTemplate template;

    public String getProductName(String name) {
        List<String> result = template.queryForList(query, Map.of("name", name), String.class);
        return result.isEmpty() ? "Nothing found" : String.join(", ", result);
    }

    @SuppressWarnings("SameParameterValue")
    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}