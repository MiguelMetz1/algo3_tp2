package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.TypeData.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Ground extends Plot{
    public Ground(){
        super(new BuildablePlot());
    }

    public String showPlotName(){
        return "Tierra";
    }

    public Color getColor(){
        return Color.SADDLEBROWN;
    }

    public Image getImage() {
        ClassLoader classLoader = getClass().getClassLoader();
        String imagePath = classLoader.getResource("edu/fiuba/algo3/InterfazGrafica/javafx/Imagenes/ground.png").toExternalForm();
        return new Image(imagePath);
    }

}
