package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.SilverTower;
import edu.fiuba.algo3.Defenses.WhiteTower;
import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class Ground extends Plot{

    private ArrayList<Defense> defenses;

    public Ground(Coordinate coordinate){
        super(coordinate);
        this.defenses = new ArrayList<>();
    }

    @Override
    public void receive(Defense defense) throws WrongPlace {
        if( !this.defenses.isEmpty() ){
            throw new WrongPlace("The Plot is occupied, you can't ");
        }
        this.defenses.add(defense);
    }

    public void remove( Defense defense ){
        this.defenses.remove(defense);
    }

}
