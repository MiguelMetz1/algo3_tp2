package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Rocky extends Plot{

    public Rocky(Coordinate coordinate){
        super(coordinate);
    }

    @Override
    public void receive(Defense defense) throws WrongPlace {
        throw new WrongPlace("The plot is not a construction place.");
    }

    @Override
    public void remove(Defense defense) {
        Logger.getLogger("Placeable").log(Level.INFO, "This plot has nothing to remove.");
    }

}
