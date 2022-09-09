package com.solvd.university.parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.university.parsers.IParse;
import com.solvd.university.structure.University;

import java.io.File;
import java.io.IOException;

public class JacksonParser implements IParse {

    @Override
    public University parse(String fileName) {
        University uni;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            File json = new File(fileName);
            uni = mapper.readValue(json, University.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uni;
    }
}