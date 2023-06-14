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
        ClassLoader classLoader = getClass().getClassLoader();
        String imagePath = classLoader.getResource("edu/fiuba/algo3/InterfazGrafica/javafx/Imagenes/rocky.jpg").toExternalForm();
        return new Image(imagePath);
    }
}
