package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by innopolis on 14.12.2016.
 */
public class URLParser implements Runnable, IParser{

    private String fileName;

    private final Logger logger = LoggerFactory.getLogger(URLParser.class);

    public URLParser(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void parseFile(){
        logger.info("Запустился поток обработки url-ресурса " + fileName +"\n");
        logger.info("Обнаружен url-ресурс " + fileName +"\n");
        logger.warn("Парсинг url-ресурсов не поддерживается");
        TextParser.setStopWork(true);
        logger.info("Завершился поток обработки url-ресурса " + fileName +"\n");
    }

    @Override
    public void run() {
        parseFile();
    }
}
