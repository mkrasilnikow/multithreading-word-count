package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by innopolis on 14.12.2016.
 */
public class ParseValidator {

    public static Map<String,Integer> wordList = new HashMap<String, Integer>();

    private static final Pattern urlPattern = Pattern.compile("^(https?:\\/\\/)?([\\w\\.]+)\\.([a-z]{2,6}\\.?)(\\/[\\w\\.]*)*\\/?$");

    private Matcher matcher;

public ParseValidator(String [] args) throws IOException {
    for (String arg: args) {
        matcher = urlPattern.matcher(arg);
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
