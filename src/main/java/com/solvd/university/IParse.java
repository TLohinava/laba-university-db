package com.solvd.university;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public interface IParse {

    void parse(String fileName) throws FileNotFoundException, XMLStreamException;
}
