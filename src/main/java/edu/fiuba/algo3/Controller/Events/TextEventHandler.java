package edu.fiuba.algo3.Controller.Events;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TextEventHandler implements EventHandler<KeyEvent> {

    private Button botonEnviar;

    public TextEventHandler(Button botonEnviar) {
        this.botonEnviar = botonEnviar;
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            Event.fireEvent(botonEnviar, new ActionEvent());
        }
    }
}
