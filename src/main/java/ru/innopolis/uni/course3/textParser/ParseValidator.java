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
public class ParseValidator implements IValidator {

    public static Map<String,Integer> wordList = new HashMap<String, Integer>();

    public ParseValidator(String [] args) throws IOException {
        for (String arg: args) {
            if (validate(arg)){
                URLParser urlParser = new URLParser(arg);
            } else{
                Thread thread = new Thread(new TextParser(arg));
                thread.start();
            }
        }
    }

    @Override
    public boolean validate(String lineToValidate) {
        final Pattern urlPattern = Pattern.compile("^(https?:\\/\\/)?([\\w\\.]+)\\.([a-z]{2,6}\\.?)(\\/[\\w\\.]*)*\\/?$");
        Matcher matcher = urlPattern.matcher(lineToValidate);
        if (matcher.matches()){
            return true;
        } else{
            return false;
        }

    }
}
