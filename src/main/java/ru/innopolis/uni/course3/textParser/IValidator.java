package ru.innopolis.uni.course3.textParser;

/**
 * Created by Krasilnikov Mikhail on 15.12.2016.
 */

/**
 * Интерфейс определяет метод проверки строки на соответствие маске или паттерну
 */
public interface IValidator {
    boolean validate(String lineToValidate);
}
