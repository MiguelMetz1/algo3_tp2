package edu.fiuba.algo3.InterfazGrafica.javafx.eventos.manejadores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SendButtonEventHandler implements EventHandler<ActionEvent> {

    private TextField textField;
    private Label label;

    public SendButtonEventHandler(TextField textField, Label label) {
        this.textField = textField;
        this.label = label;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if (this.textField.getText().trim().equals("")) {

            this.label.setText("Debe ingresar un texto");
            this.label.setTextFill(Color.web("#FF0000"));
            System.out.println("Arania");

        } else {

            this.label.setText("Su nombre es: "+this.textField.getText()+".\n"+"Tiene 20 puntos de vida y 100 creditos para gastar.");
            this.label.setWrapText(true);
            this.label.setTextFill(Color.web("#336600"));
        }
    }
}
