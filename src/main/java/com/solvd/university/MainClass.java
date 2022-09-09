package com.solvd.university;

import com.solvd.university.parsers.IParse;
import com.solvd.university.parsers.jackson.JacksonParser;
import com.solvd.university.parsers.jaxb.JaxbParser;
import com.solvd.university.parsers.stax.Parser;

public class MainClass {

    public static void main(String[] args) {
        String fileName = "./src/main/resources/uni.xml";
        IParse demo = new Parser();
        demo.parse(fileName);

        IParse jaxb = new JaxbParser();
        jaxb.parse(fileName);

        String fileName2 = "./src/main/resources/uni.json";
        IParse json = new JacksonParser();
        json.parse(fileName2);
    }
}