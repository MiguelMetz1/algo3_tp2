package edu.fiuba.algo3.Controller.Events;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExitButtonEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Platform.exit(); // Cierra la aplicación
        System.exit(0); // Termina el proceso Java
    }

}
