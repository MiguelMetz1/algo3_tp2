package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Time;
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

    public void defenseTime(Time timeOfConstruction) {

        if( !this.defenses.isEmpty() )
            this.defenses.get(0).remainingTime(timeOfConstruction);
    }

    public String getName(){
        return "Ground";
    }

}
