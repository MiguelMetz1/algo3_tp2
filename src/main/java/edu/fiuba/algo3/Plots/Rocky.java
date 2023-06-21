package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.TypeData.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Rocky extends Plot{

    public Rocky(){
        super(new UnbuildablePlot());
    }

    public String showPlotName(){
        return "Piedra";
    }

    public Color getColor(){
        return Color.GRAY;
    }

    public Image getImage() {
        return new Image("file:src/main/java/edu/fiuba/algo3/View/Imagenes/rocky.jpg");
    }
}
