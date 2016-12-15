package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.innopolis.uni.course3.textParser.ParseValidator.*;


/**
 * Created by innopolis on 14.12.2016.
 */
public class TextParser implements Runnable, IValidator {

    private static final Logger logger = LoggerFactory.getLogger(TextParser.class);

    private String fileName;

    public TextParser(String fileName) {
        this.fileName = fileName;
    }

    private void parseFile(){
        logger.info("Запустился поток обработки текстовых файлов\n");
        String line;
        String splittedArray[];
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName));) {
            while ((line = fileReader.readLine()) != null) {
                if (validate(line)){
                    logger.error("Английские буквы в файле! Обработка файла " + fileName + " прекращена!");
                    throw new IOException("English letters in file!");
                }
                splittedArray = line.split("[^А-Яа-яёЁ]");
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

    private void printList(Map<String,Integer> objects){
        for (Map.Entry entry : objects.entrySet()) {
            logger.info("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
        logger.info("\n\n");
    }

/*    void printArr(String[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }*/

    @Override
    public void run() {
        synchronized (wordList) {
            if(!Thread.currentThread().isInterrupted()){
                parseFile();
            }
        }
    }

    @Override
    public boolean validate(String lineToValidate) {
        final Pattern engLettersPattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = engLettersPattern.matcher(lineToValidate);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }
}
