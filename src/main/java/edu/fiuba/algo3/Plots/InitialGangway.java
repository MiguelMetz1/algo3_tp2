package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.TypeData.Coordinate;
import javafx.scene.image.Image;

public class InitialGangway extends Gangway {

    public InitialGangway(Coordinate coordinate){
        super(coordinate);
    }

    public Image getImage() {
        return new Image("file:src/main/java/edu/fiuba/algo3/View/Images/redWool.png");
    }

    public String getName(){
        return "Initial Gangway" + this.coordinate.toIntString();
    }
}
