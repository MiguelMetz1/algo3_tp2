package edu.fiuba.algo3.View;

import edu.fiuba.algo3.AlgoDefense.AlgoDefense;
import edu.fiuba.algo3.Interface.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameStart extends Application {

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(final Stage stage){

        AlgoDefense algoDefense = new AlgoDefense();

        stage.setTitle("AlgoDefense");

        PrincipalConteiner principalConteiner = new PrincipalConteiner(stage,algoDefense);
        Scene gameScene = new Scene(principalConteiner,720,720);

        WelcomeConteiner welcomeConteiner = new WelcomeConteiner(stage,gameScene);
        Scene welcomeScene = new Scene(welcomeConteiner,600,340 );

        stage.setScene(welcomeScene);
        stage.setFullScreen(false);

        /*AlgoDefense algoDefense = new AlgoDefense(principalConteiner);
        algoDefense.startGame();*/

        stage.show();

    }
}
