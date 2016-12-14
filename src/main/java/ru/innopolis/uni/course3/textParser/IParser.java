package ru.innopolis.uni.course3.textParser;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by innopolis on 14.12.2016.
 */
public interface IParser {
    void parseFile(String fileName) throws FileNotFoundException, IOException;
}
