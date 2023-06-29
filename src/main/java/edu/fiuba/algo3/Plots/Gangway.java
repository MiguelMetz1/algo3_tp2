package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Time;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


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

    public void defenseTime(Time timeOfConstruction) {
        this.ground.defenseTime(timeOfConstruction);
    }

    @Override
    public void remove(Defense defense) {
        ground.remove(defense);
    }

    public Image getImage() {
        return new Image("file:src/main/java/edu/fiuba/algo3/View/Images/gangway.jpg");
    }

    public String getName(){
        return "Gangway";
    }

    public void showRange(Coordinate coordinate, Button button){}
}
