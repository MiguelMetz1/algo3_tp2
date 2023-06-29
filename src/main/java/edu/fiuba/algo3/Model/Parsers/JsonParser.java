package edu.fiuba.algo3.Model.Parsers;

import java.io.BufferedReader;
import java.io.FileReader;

public class JsonParser {

    protected String fileName;

    public JsonParser(String fileName){
        this.fileName = fileName;
    }

    protected String readJson() {

        String completeString = "";

        try {
            String currentLine = "";

            FileReader fileReader = new FileReader(this.fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (currentLine != null) {
                completeString += currentLine;
                currentLine = bufferedReader.readLine();
            }
            fileReader.close();
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return completeString;
    }

}
