package com.solvd.university;

import com.solvd.university.structure.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbParser implements IParse {

    private static final Logger LOGGER = LogManager.getLogger(JaxbParser.class);

    @Override
    public University parse(String fileName) {
        University university = null;
        try {
            JAXBContext context = JAXBContext.newInstance(University.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File xml = new File(fileName);
            university = (University) unmarshaller.unmarshal(xml);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
        return university;
    }
}