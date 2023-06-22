package edu.fiuba.algo3.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(final Stage stage){
        stage.setTitle("AlgoDefense");

        PrincipalConteiner principalConteiner = new PrincipalConteiner(stage);
        Scene gameScene = new Scene(principalConteiner,720,720);

        WelcomeConteiner welcomeConteiner = new WelcomeConteiner(stage,gameScene);
        Scene welcomeScene = new Scene(welcomeConteiner,640,480 );

        stage.setScene(welcomeScene);
        stage.setFullScreen(false);

        stage.show();

    }
}
