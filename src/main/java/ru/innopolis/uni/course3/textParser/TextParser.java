package ru.innopolis.uni.course3.textParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.innopolis.uni.course3.textParser.ResourceParser.*;


/**
 * Created by Krasilnikov Mikhail on 14.12.2016.
 */

/**
 * Класс - парсер текстовых файлов: открывает файлы, разбивает на лексемы, выводит на печать
 */
public class TextParser implements Runnable, IValidator,IParser {

    /**
     * Создаем логгер для класса на базе библиотеки slf4j
     */
    private final Logger logger = LoggerFactory.getLogger(TextParser.class);

    /**
     * Адрес ресурса для парсинга, задается через конструктор класса
     */
    private String fileName;

    /**
     * Переменная stopWork служит для остановки потока в случае нахождения в файле некиррилических символов.
     */
    private static volatile boolean stopWork = false;

    /**
     * Конструктор класса, в качестве параметра принимает адрес ресурса
     * @param fileName - Адрес ресурса для парсинга
     */
    public TextParser(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Реализация интерфейса Runnable, запуск потока на исполнение
     */
    @Override
    public void run() {
        parseFile();
    }

    /**
     *Реализация метода интерфейса IValidator
     * Проверяет, есть ли в тексте некиррилические символы
     * @param lineToValidate - название входного ресурса
     * @return Возвращает true - если есть некиррилические символы, false - если нет
     */
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

    /**
     * Метод открывает на чтение файл, используя BufferedReader и построчно в цикле while() считывает его.
     * Если строка не проходит валидацию, то цикл прерывается и метод завершает свою работу.
     * В лог записывается ошибка.
     * Если строка проходит валидацию, то она разбивается на лексемы с помощью метода split() по регулярному
     * выраженнию, указанному в параметрах. Далее строка записывается в массив splittedArray().
     * В блоке синхронизации в качестве монитора используется HashMap wordList,
     * в которую с помощью метода @see #ResourceParser.putToMap(Key, Value) помещаются лексемы со считанных  строк
     * Далее HashMap построчно выводится в режиме реального времени
     * Все ошибки обратываются и выводятся в лог.
     */
    @Override
    public void parseFile(){
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
                            putToMap(arg,getWordList().get(arg)+1);
                        } else if(arg.hashCode() != 0){
                            putToMap(arg,1);
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

    /**
     * Метод выводит на печать HashMap в виде "Key: Ключ Value: Значение"
     * @param objects - Map, которую необходимо напечатать в лог
     */
    public void printList(Map<String,Integer> objects){
        logger.info("Новая итерация");
        for (Map.Entry<String, Integer> entry:objects.entrySet()) {
            logger.info("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
        logger.info("\n\n");
    }

    /**
     * Метод возвращает состояние флага проверки
     * @return boolean stopWork - флаг остановки потока
     */
    public static boolean isStopWork() {
        return stopWork;
    }

    /**
     * Метод установки флага проверки
     * @param stopWork - флаг остановки потока
     */
    public static void setStopWork(boolean stopWork) {
        TextParser.stopWork = stopWork;
    }

}
