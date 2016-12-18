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

import static ru.innopolis.uni.course3.textParser.ResourceParser.*;


/**
 * Created by innopolis on 14.12.2016.
 */
public class TextParser implements Runnable, IValidator {

    private final Logger logger = LoggerFactory.getLogger(TextParser.class);

    private String fileName;

    private static volatile boolean stopWork = false;

    public TextParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
            parseFile();

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

    private void parseFile(){
        String line;
        String splittedArray[];
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName));) {
            logger.info("Запустился поток обработки текстового файла " + fileName +"\n");
            while ((line = fileReader.readLine()) != null && !stopWork) {
                if (validate(line)){
                    logger.error("Английские буквы в файле! Обработка файла " + fileName + " прекращена!");
                    stopWork = true;
                    break;
                }
                splittedArray = line.split("[^А-Яа-яёЁ]");
                synchronized (getWordList()){
                    for (String arg:splittedArray) {
                        if(getWordList().containsKey(arg)){
                            Put(arg,getWordList().get(arg)+1);
                        } else if(arg.hashCode() != 0){
                            Put(arg,1);
                        }
                        printList(getWordList());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("Невозможно найти файл!");

        } catch (IOException e) {
            logger.error("Ошибка ввода-вывода");
        } finally {
            logger.info("Завершился поток обработки текстового файла " + fileName +"\n");
        }

    }

    public void printList(Map<String,Integer> objects){
        for (Map.Entry<String, Integer> entry:objects.entrySet()) {
            logger.info("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
        logger.info("\n\n");
    }

    public static boolean isStopWork() {
        return stopWork;
    }

    public static void setStopWork(boolean stopWork) {
        TextParser.stopWork = stopWork;
    }

}
