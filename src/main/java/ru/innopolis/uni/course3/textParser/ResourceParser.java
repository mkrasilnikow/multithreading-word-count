package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Krasilnikov Mikhail on 14.12.2016.
 */

/**
 * Класс хранит список потоков и запускается их при вызове метода startAllParsers()
 */
public class ResourceParser implements IValidator {

    /**
     * Создается HashMap - как общий ресурс для хранения слов, в качестве ключа используется само слово,
     * в качестве значения - сколько раз оно встречается
     */
    private static Map<String,Integer> wordList = new HashMap<String, Integer>();

    /**
     * Помещаем пару ключ-значение в HashMap
     * @param key - Слово, считанное из файл
     * @param value - Количество вхождений
     */
    public static void putToMap(String key, Integer value){
        wordList.put(key, value);
    }

    /**
     * Getter для приватной коллекции HashMap
     * @return текущее состояние HashMap
     */
    public static Map<String, Integer> getWordList() {
        return wordList;
    }

    /**
     * Создаем логгер для класса на базе библиотеки slf4j
     */
    private final Logger logger = LoggerFactory.getLogger(ResourceParser.class);

    /**
     * List хранящий в себе список всех потоков
     */
    private List<Thread> threads = new ArrayList<Thread>();

    /**
     * Метод проверяет с помощью @see #validate(String lineToValidate) к какому из типов файлов
     * соответсвует элемент входного массива,
     * после чего создает новый поток с соответствующим парсером
     * @param args - список ресурсов, которые необходимо обработать
     */
    public ResourceParser(String [] args){
        for (String arg: args) {
            if (validate(arg)){
                threads.add(new Thread(new URLParser(arg)));
            } else{
                threads.add(new Thread(new TextParser(arg)));
            }
        }
    }

    /**
     * Метод в цикле foreach запускает все потоки
     */
    public void startAllParsers(){
        for (Thread thread:threads) {
            thread.start();
        }
    }

    /**
     * Реализация метода интерфейса IValidator
     * Проверяется, является ли входной ресурс url-адресом
     * @param lineToValidate - название входного ресурса
     * @return Возвращает true - если ресурс url-адрес, false в иных случаях
     */
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
