package ru.innopolis.uni.course3.textParser;

/**
 * Created by Krasilnikov Mikhail on 18.12.2016.
 */

/**
 * Интерфейс определяет, является ли класс парсером того или иного вида файлов,
 * в программе приведены примеры 2-х парсеров URLParser, TextParser.
 * Имеет лишь один метод - парсинг файла()
 */
public interface IParser {
    void parseFile();
}
