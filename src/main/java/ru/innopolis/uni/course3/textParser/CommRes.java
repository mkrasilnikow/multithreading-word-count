package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by innopolis on 18.12.2016.
 */
public class CommRes {

    private final Logger logger = LoggerFactory.getLogger(CommRes.class);

    private Map<String,Integer> wordList = new HashMap<String, Integer>();

    public void Put(String key, Integer value){
        wordList.put(key, value);
    }

    public Map<String, Integer> getWordList() {
        return wordList;
    }


}
