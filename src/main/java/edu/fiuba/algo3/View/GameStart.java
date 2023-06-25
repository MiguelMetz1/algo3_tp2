package edu.fiuba.algo3.View;

import edu.fiuba.algo3.AlgoDefense.AlgoDefense;
import edu.fiuba.algo3.TypeData.Name.Name;
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
        Name name = algoDefense.getName();

        stage.setTitle("AlgoDefense");

        PrincipalContainer principalContainer = new PrincipalContainer(stage,algoDefense,name);
        Scene gameScene = new Scene(principalContainer,720,720);

        WelcomeContainer welcomeContainer = new WelcomeContainer(stage,gameScene,name);
        Scene welcomeScene = new Scene(welcomeContainer,600,340 );

        stage.setScene(welcomeScene);
        stage.setFullScreen(false);


        stage.show();

    }
}
