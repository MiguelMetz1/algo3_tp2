package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.image.Image;

public class Gangway extends Plot{

    private final Ground ground;

    public Gangway(Coordinate coordinate){
        super(coordinate);
        this.ground = new Ground(this.coordinate);
    }

    @Override
    public void receive(Defense defense) throws WrongPlace {
        ground.receive(defense);
    }

    @Override
    public void remove(Defense defense) {
        ground.remove(defense);
    }

    public Image getImage() {
        return new Image("file:src/main/java/edu/fiuba/algo3/View/Images/madera.jpg");
    }

    public String getName(){
        return "Gangway" + this.coordinate.toIntString();
    }
}
