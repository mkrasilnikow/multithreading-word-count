package ru.innopolis.uni.course3.textParser;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

/**
 * Created by Krasilnikov Mikhail on 18.12.2016.
 */

/**
 * Данный класс проверяет public-методы класса ResourceParser
 */
public class ResourceParserTest {

    private ResourceParser parser;

    private Logger logger = LoggerFactory.getLogger(TextParserTest.class);

    private String [] args = {"C:/inno_code/2.ddd", "C:/inno_code/2.ddd"};

    /**
     * Инициализируем проверяемы класс. На вход подаем заранее определенный список аргументов
     */
    @Before
    public void init() {
        parser = new ResourceParser(args);
    }

    /**
     * Тест проверяет метод записи значений в HashMap
     */
    @Test
    public void put(){
        ResourceParser.putToMap("Key",2_147_483_647);
        assertTrue("HashMap содержит введенный ключ ",ResourceParser.getWordList().containsKey("Key"));
    }

    /**
     * Тест проверяет корректность работы метода validate(String lineToValidate).
     */
    @Test
    public void validate(){
        String pattern = "http://www.ya.ru";
        assertTrue("Ожидаемое поведение = true",parser.validate(pattern));
    }

}