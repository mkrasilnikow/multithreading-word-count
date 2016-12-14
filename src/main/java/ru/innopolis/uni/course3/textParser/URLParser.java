package ru.innopolis.uni.course3.textParser;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by innopolis on 14.12.2016.
 */
public class URLParser implements IParser {
    public void parseFile(String fileName) throws FileNotFoundException, IOException {
        throw new FileNotFoundException("Обнаружен url-временно не обрабaтывается");
    }
}
