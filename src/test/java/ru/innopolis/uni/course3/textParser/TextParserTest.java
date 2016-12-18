package ru.innopolis.uni.course3.textParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

/**
 * Created by innopolis on 18.12.2016.
 */
public class TextParserTest {

    private TextParser textParser;

    private Logger logger = LoggerFactory.getLogger(TextParserTest.class);

    private String fileName = "C:/inno_code/2.ddd";

    @Before
    public void init() {
        textParser = new TextParser(fileName);
        ResourceParser.getWordList().clear();
        TextParser.setStopWork(true);
    }

    @Test
    public void validate(){
        String pattern = "Такой text не проходит проверку";
        assertTrue("Ожидаемое поведение = true",textParser.validate(pattern));
    }

    @Test
    public void parseBreak(){
        textParser.parseFile();
        assertTrue("HashMap пуст",ResourceParser.getWordList().isEmpty()== true);
    }

}