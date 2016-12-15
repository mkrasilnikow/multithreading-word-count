package ru.innopolis.uni.course3.textParser;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by innopolis on 14.12.2016.
 */
public class URLParser implements Runnable {

    private String fileName;

    public URLParser(String fileName) throws IOException {
        this.fileName = fileName;
    }

    private void parseFile() throws FileNotFoundException, IOException {
        throw new FileNotFoundException("Обнаружен url-временно не обрабaтывается");

    }

    @Override
    public void run() {
        try {
            parseFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
