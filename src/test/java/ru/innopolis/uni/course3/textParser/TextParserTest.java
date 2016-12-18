package ru.innopolis.uni.course3.textParser;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

/**
 * Created by innopolis on 18.12.2016.
 */
public class TextParserTest {

    private TextParser textParser;

    private Logger logger = LoggerFactory.getLogger(TextParserTest.class);

    private String fileName = "C:/";

    @Before
    public void init() {
        textParser = new TextParser(fileName);
    }
    @Test
    public void validate(){
        String pattern = "Такой text не проходит проверку";
        assertTrue("Ожидаемое поведение = true",textParser.validate(pattern));
    }

    @Test
    public void wrongFileOpening() throws IOException,FileNotFoundException{
        textParser.parseFile();
    }

    @Test
    public void parseBreak(){
        TextParser.setStopWork(true);
        textParser.parseFile();
        assertTrue("fd",ResourceParser.getWordList().size()==0);
    }

}