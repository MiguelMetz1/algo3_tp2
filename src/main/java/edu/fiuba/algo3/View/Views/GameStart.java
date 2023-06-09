package edu.fiuba.algo3.View.Views;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameStart extends Application {

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(final Stage stage){

        stage.setTitle("AlgoDefense");

        /*PrincipalContainer principalContainer = new PrincipalContainer(stage,algoDefense,name);
        Scene gameScene = new Scene(principalContainer,720,720);*/

        WelcomeContainer welcomeContainer = new WelcomeContainer(stage);
        Scene welcomeScene = new Scene(welcomeContainer,600,340 );

        stage.setScene(welcomeScene);
        stage.setFullScreen(false);


        stage.show();

    }
}
