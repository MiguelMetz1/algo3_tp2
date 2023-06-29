package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        if( !defenses.isEmpty() ) {
            this.defenses.remove(defense);
            Logger.getLogger("Remover").log(Level.INFO, "A defense in " + this.coordinate.toString() + "has been destroyed.");
        }
    }

    public void defenseTime(Time timeOfConstruction) {

        if( !this.defenses.isEmpty() )
            this.defenses.get(0).remainingTime(timeOfConstruction);
    }

    public String getName(){
        return "Ground";
    }

}
