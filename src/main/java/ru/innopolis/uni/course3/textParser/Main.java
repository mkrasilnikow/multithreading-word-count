package ru.innopolis.uni.course3.textParser;

import java.io.IOException;

/**
 * Created by Krasilnikov Mikhail on 14.12.2016.
 */

/**
 * Точка входа в программу, ресурсы подаются в качестве аргументов
 * Создается объект класса, хранящего ссылки на все парсеры, которые запускаются в потоках
 */
public class Main {
    public static void main(String[] args){
        ResourceParser parser = new ResourceParser(args);
        parser.startAllParsers();
    }
}
