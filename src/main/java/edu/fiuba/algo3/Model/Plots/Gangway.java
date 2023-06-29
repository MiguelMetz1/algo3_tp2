package edu.fiuba.algo3.Model.Plots;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.TypeData.Time;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

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

    public String getName(){
        return "Gangway";
    }

   }
