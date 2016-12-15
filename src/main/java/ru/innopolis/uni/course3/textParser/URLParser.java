package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by innopolis on 14.12.2016.
 */
public class URLParser implements Runnable {

    private String fileName;

    private static final Logger logger = LoggerFactory.getLogger(URLParser.class);

    public URLParser(String fileName) throws IOException {
        this.fileName = fileName;
    }

    private void parseFile(){
        logger.info("Обнаружен url-ресурс");
        logger.warn("Парсинг url-файлов временно не поддерживается");
    }

    @Override
    public void run() {
        parseFile();
    }
}
