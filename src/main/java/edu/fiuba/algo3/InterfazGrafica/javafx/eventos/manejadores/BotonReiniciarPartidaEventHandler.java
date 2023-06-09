package edu.fiuba.algo3.InterfazGrafica.javafx.eventos.manejadores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BotonReiniciarPartidaEventHandler implements EventHandler<ActionEvent> {


    private Button boton;
    private Label texto1;
    private Label texto2;

    public BotonReiniciarPartidaEventHandler(Button boton, Label texto1, Label texto2) {

        this.boton = boton;
        this.texto1 = texto1;
        this.texto2 = texto2;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.boton.setText("Start Game");
        this.texto1.setText("");
        this.texto2.setText("");
    }



}
