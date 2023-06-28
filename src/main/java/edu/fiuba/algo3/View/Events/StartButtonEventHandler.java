package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.TypeData.Name.Name;
import edu.fiuba.algo3.View.PrincipalContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {

    Stage stage;
    Scene nextScene;

    TextField textField;

    Label label;
    Name name;

    public StartButtonEventHandler(TextField textField, Label label, Stage stage, Name name){
        this.textField = textField;
        this.label = label;
        this.stage = stage;
        this.name = name;
    }
    public void handle(ActionEvent actionEvent){

        if (this.textField.getText().trim().equals("")) {

            this.label.setText("You must introduce a name");
            this.label.setTextFill(Color.web("#fafafa"));
            this.textField.requestFocus();

        }
        else if (this.textField.getText().trim().length() > 10 || this.textField.getText().trim().length() < 6 ) {
            this.label.setText("The name must be between 6 and 10 letters");
            this.label.setTextFill(Color.web("#fafafa"));
            this.textField.requestFocus();
        } else {
            this.name.setName(this.textField.getText());
            Game game = new Game(this.textField.getText());

            PrincipalContainer principalContainer = new PrincipalContainer(stage,game,name);
            Scene gameScene = new Scene(principalContainer,720,720);
            this.stage.setScene(gameScene);
            this.stage.setFullScreen(true);
            this.stage.setResizable(true);
        }


    }
}
