
package edu.fiuba.algo3.InterfazGrafica.javafx.eventos.manejadores;

        import edu.fiuba.algo3.TypeData.Life;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.scene.control.*;
        import javafx.scene.paint.Color;

public class AddEnemiesButtonEventHandler implements EventHandler<ActionEvent> {

    private Label label2;
    private Label label3;
    private String texto;
    private Life life;



    public AddEnemiesButtonEventHandler(String text, Label label2, Label label3, Life life) {
        this.label2 = label2;
        this.label3 = label3;
        this.texto = text;
        this.life = life;

    }

    @Override
    public void handle(ActionEvent actionEvent) {


        if(this.texto.equals("Add spider")) {
            this.label2.setText(this.label2.getText() + " spider");
            this.life.reduce(2);
            System.out.println("Spider");
            if(!this.life.isEmpty()) {
                this.label3.setText("Your remaining life: " + this.life.returnLife());
            }
            else
                this.label3.setText("Your remaining life: 0");
        }
        if(this.texto.equals("Add ant")) {
            this.label2.setText(this.label2.getText() + " ant");
            this.life.reduce(1);
            System.out.println("Ant");
            if(!this.life.isEmpty()) {
                this.label3.setText("Your remaining life: " + this.life.returnLife());
            }
            else
                this.label3.setText("Your remaining life: 0");
        }
        this.label2.setTextFill(Color.web("#336600"));
        this.label2.setWrapText(true);

    }
}

