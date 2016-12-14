package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static ru.innopolis.uni.course3.textParser.ParseValidator.*;


/**
 * Created by innopolis on 14.12.2016.
 */
public class TextParser implements IParser {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public void parseFile(String fileName){
        String line;
        String splittedArray[];
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName));) {
            while ((line = fileReader.readLine()) != null) {
                splittedArray = line.split("[^А-Яа-я$]");
                //printArr(splittedArray);
                for (String arg:splittedArray) {
                    if(wordList.containsKey(arg)){
                        wordList.put(arg,wordList.get(arg)+1);
                    } else{
                        wordList.put(arg,1);
                    }
                }
            }
            printList(wordList);
        } catch (FileNotFoundException e) {
            logger.error("Невозможно найти файл!");

        } catch (IOException e) {
            logger.error("Ошибка ввода-вывода");
        }
    }

    void printList(Map<String,Integer> objects){
        for (Map.Entry entry : objects.entrySet()) {

            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
    }

    void printArr(String[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
