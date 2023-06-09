
package edu.fiuba.algo3.InterfazGrafica.javafx.eventos.manejadores;

        import edu.fiuba.algo3.TypeData.Life;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.scene.control.*;
        import javafx.scene.image.ImageView;
        import javafx.scene.paint.Color;
        import javafx.scene.text.Text;

public class BotonAgregarEnemigosEventHandler implements EventHandler<ActionEvent> {

    private Label label1;
    private Label label2;
    private String texto;
    private Life life;



    public BotonAgregarEnemigosEventHandler(String texto, Label label1, Label label2, Life life) {
        this.label1 = label1;
        this.label2 = label2;
        this.texto = texto;
        this.life = life;

    }

    @Override
    public void handle(ActionEvent actionEvent) {


        if(this.texto == "Agregar Arania") {
            this.label1.setText(this.label1.getText() + " arania");
            this.life.reduce(2);
            if(!this.life.isEmpty()) {
                this.label2.setText("Su vida Restante es: " + this.life.returnLife());
            }
            else
                this.label2.setText("Su vida Restante es: 0");
        }
        if(this.texto == "Agregar Hormiga") {
            this.label1.setText(this.label1.getText() + " hormiga");
            this.life.reduce(1);
            if(!this.life.isEmpty()) {
                this.label2.setText("Su vida Restante es: " + this.life.returnLife());
            }
            else
                this.label2.setText("Su vida Restante es: 0");
        }
        this.label1.setTextFill(Color.web("#336600"));
        this.label1.setWrapText(true);

    }
}

