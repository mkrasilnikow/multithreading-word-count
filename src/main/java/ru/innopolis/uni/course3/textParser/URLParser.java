package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Krasilnikov Mikhail on 14.12.2016.
 */

/**
 * Класс-заглушка для парсинга url-ресурсов
 */
public class URLParser implements Runnable, IParser{

    /**
     * Адрес ресурса для парсинга, задается через конструктор класса
     */
    private String fileName;

    /**
     * Создаем логгер для класса на базе библиотеки slf4j
     */
    private final Logger logger = LoggerFactory.getLogger(URLParser.class);

    /**
     * Конструктор класса, в качестве параметра принимает адрес ресурса
     * @param fileName - Адрес ресурса для парсинга
     */
    public URLParser(String fileName){
        this.fileName = fileName;
    }

    /**
     * Метод заглушка для парсинга url-ресурсов. В случае обнаружения url-ресурса,
     * выводит запись в лог о невозможности обработать ресурс и прерывает все потоки.
     */
    @Override
    public void parseFile(){
        logger.info("Запустился поток обработки url-ресурса " + fileName +"\n");
        logger.info("Обнаружен url-ресурс " + fileName +"\n");
        logger.warn("Парсинг url-ресурсов не поддерживается");
        TextParser.setStopWork(true);
        logger.info("Завершился поток обработки url-ресурса " + fileName +"\n");
    }

    /**
     * Реализация интерфейса Runnable, запуск потока на исполнение
     */
    @Override
    public void run() {
        parseFile();
    }
}
