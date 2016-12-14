package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by innopolis on 14.12.2016.
 */
public class ParseValidator {

    public static HashMap<String,Integer> wordList = new HashMap<String, Integer>();

    public static final Pattern pattern = Pattern.compile("^(https?:\\/\\/)?([\\w\\.]+)\\.([a-z]{2,6}\\.?)(\\/[\\w\\.]*)*\\/?$");
    Matcher matcher;

public ParseValidator(String [] args) {
    for (String arg: args) {
        matcher = pattern.matcher(arg);
        if (matcher.matches()){
            URLParser urlParser = new URLParser();
            urlParser.parseFile(arg);
        } else{
            TextParser textParser = new TextParser();
            textParser.parseFile(arg);
        }
    }
    }
}
