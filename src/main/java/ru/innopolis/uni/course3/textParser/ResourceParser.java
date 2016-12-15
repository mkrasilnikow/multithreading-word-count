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
public class ResourceParser implements IValidator {
    //add метод с синхронизацией
    public static Map<String,Integer> wordList = new HashMap<String, Integer>();

    //isURLparser
    public ResourceParser(String [] args) throws IOException {
        for (String arg: args) {
            if (validate(arg)){
                Thread urlThread = new Thread(new URLParser(arg));
                urlThread.start();
            } else{
                Thread textThread = new Thread(new TextParser(arg));
                textThread.start();
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
