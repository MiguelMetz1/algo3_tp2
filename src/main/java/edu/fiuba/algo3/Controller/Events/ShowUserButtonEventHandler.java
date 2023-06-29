package edu.fiuba.algo3.Controller.Events;

import edu.fiuba.algo3.Model.TypeData.Name.Name;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ShowUserButtonEventHandler implements EventHandler<ActionEvent> {

    Button user;
    Name name;
    public ShowUserButtonEventHandler(Button user, Name name){
        this.user = user;
        this.name = name;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        this.user.setText("User:" + name.getName());

    }

}
