package edu.fiuba.algo3;

import edu.fiuba.algo3.AlgoDefense.AlgoDefense;
import edu.fiuba.algo3.View.GameStart;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
       /* GameStart.main(args);*/
        AlgoDefense algoDefense = new AlgoDefense();
        algoDefense.startGame();

    }

    @Override
    public void start(Stage stage) throws Exception {}
}
