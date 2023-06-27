package edu.fiuba.algo3.View.Events;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.*;

public class ShowMessageButtonEventHandler implements EventHandler<ActionEvent> {

    String message;
    public ShowMessageButtonEventHandler(String message){
        this.message = message;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        //alert.setContentText(this.message);

        alert.setContentText(this.message);


        alert.show();
    }

}