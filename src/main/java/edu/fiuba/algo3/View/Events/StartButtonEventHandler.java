package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.TypeData.Name.Name;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartButtonEventHandler implements EventHandler<ActionEvent> {

    Stage stage;
    Scene nextScene;

    TextField textField;

    Label label;
    Name name;

    public StartButtonEventHandler(TextField textField, Label label, Stage stage, Scene nextScene, Name name){
        this.textField = textField;
        this.label = label;
        this.stage = stage;
        this.nextScene = nextScene;
        this.name = name;
    }
    public void handle(ActionEvent actionEvent){

        if (this.textField.getText().trim().equals("")) {

            this.label.setText("You must introduce a name");
            this.label.setTextFill(Color.web("#fafafa"));

        }
        else if (this.textField.getText().trim().length() > 10 || this.textField.getText().trim().length() < 5 ) {
            this.label.setText("The name must be between 5 and 10 letters");
            this.label.setTextFill(Color.web("#fafafa"));
        } else {
            this.name.setName(this.textField.getText());
            this.stage.setScene(this.nextScene);
            this.stage.setFullScreen(false);
        }


    }
}
