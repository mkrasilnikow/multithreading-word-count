package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by innopolis on 14.12.2016.
 */
public class ResourceParser implements IValidator {
    //add метод с синхронизацией
    private static Map<String,Integer> wordList = new HashMap<String, Integer>();

    private final Logger logger = LoggerFactory.getLogger(ResourceParser.class);

    private List<Thread> threads = new ArrayList<Thread>();
    //isURLparser
    public ResourceParser(String [] args){
        for (String arg: args) {
            if (validate(arg)){
                threads.add(new Thread(new URLParser(arg)));
            } else{
                threads.add(new Thread(new TextParser(arg)));
            }
        }
    }

    public void startAllParsers(){
        for (Thread thread:threads) {
            thread.start();
        }
    }

    public static void Put(String key, Integer value){
        wordList.put(key, value);
    }

    public static Map<String, Integer> getWordList() {
        return wordList;
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
