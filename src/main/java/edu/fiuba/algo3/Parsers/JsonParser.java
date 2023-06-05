package edu.fiuba.algo3.Parsers;

import java.io.BufferedReader;
import java.io.FileReader;

public abstract class JsonParser implements Parser{
    protected String fileName;

    public JsonParser(String fileName){
        this.fileName = fileName;
    }
    protected String readJson() {
        String string = "";

        FileReader fileReader;
        try {
            String stringActual = "";

            fileReader = new FileReader(this.fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (stringActual != null) {
                string += stringActual;
                stringActual = bufferedReader.readLine();
                System.out.println((stringActual));
            }
            fileReader.close();
        } catch (Exception e) {
            //fileReader.close();
            throw new RuntimeException(e);
        } finally {
            //fileReader.close();
        }

        return string;
    }
}
