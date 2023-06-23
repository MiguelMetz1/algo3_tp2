package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.TypeData.Coordinate;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class FinalGangway extends Gangway {

    public FinalGangway(Coordinate coordinate){
        super(coordinate);
    }

    public Image getImage() {
        return new Image("file:src/main/java/edu/fiuba/algo3/View/Images/orangeWool.png");
    }

    public String getName(){
        return "Final Gangway" + this.coordinate.toIntString();
    }

}


