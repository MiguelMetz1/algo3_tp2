package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.image.Image;

public class FinalGangway extends Gangway {

    public FinalGangway(Coordinate coordinate){
        super(coordinate);
    }

    public Image getImage() {
        return new Image("file:src/main/java/edu/fiuba/algo3/View/Images/finalGangway.png");
    }

    public String getName(){
        return "Final Gangway";
    }

}


