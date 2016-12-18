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
 * Данный класс проверяет public-методы класса TextParser
 */
public class TextParserTest {

    private TextParser textParser;

    private Logger logger = LoggerFactory.getLogger(TextParserTest.class);

    private String fileName = "C:/inno_code/2.ddd";

    /**
     * Инициализируем проверяемы класс. Очищаем коллекцию, устанавливаем флаг остановки потоков в false
     */
    @Before
    public void init() {
        textParser = new TextParser(fileName);
        ResourceParser.getWordList().clear();
        TextParser.setStopWork(true);
    }

    /**
     * Тест проверяет корректность работы метода validate(String lineToValidate):
     * Ожидаемый результат - текст не пройдет проверку, т.к. содержит некириллические символы
     */
    @Test
    public void validate(){
        String pattern = "Такой text не проходит проверку";
        assertTrue("Ожидаемое поведение = true",textParser.validate(pattern));
    }

    /**
     * Проверка остановки потока, если флаг stopWork == true.
     * Ожидаемый результат: цикл чтения файла прервет свое выполнение,
     * в общий ресурс HashMap wordList ничего не запишется.
     */
    @Test
    public void parseBreak(){
        textParser.parseFile();
        assertTrue("HashMap пуст",ResourceParser.getWordList().isEmpty()== true);
    }

}