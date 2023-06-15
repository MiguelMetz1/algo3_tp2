package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.SilverTower;
import edu.fiuba.algo3.Defenses.WhiteTower;
import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class Ground extends Plot{

    private OccupationState constructionState;

    public Ground(Coordinate coordinate){
        super(coordinate);
        this.constructionState = new VacantPlot();
    }

    @Override
    public void receive(Placeable placeable) throws WrongPlace {
        this.constructionState.recieve(placeable);
        this.constructionState = new OccupiedPlot();
    }


}
