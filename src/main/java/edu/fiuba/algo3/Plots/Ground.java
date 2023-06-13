package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.SilverTower;
import edu.fiuba.algo3.Defenses.WhiteTower;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.IncorrectPlaceable;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class Ground extends Plot{

    public Ground(Coordinate coordinate){
        super(coordinate);
    }

    @Override
    public void receive(Placeable placeable) throws IncorrectPlaceable {
        super.receive(placeable);
        this.rightPlaceables.clear();
    }

    @Override
    protected ArrayList<String> rightPlaceables() {
        ArrayList<String> rightPlaceables = new ArrayList<>();
        rightPlaceables.add(WhiteTower.class.getName());
        rightPlaceables.add(SilverTower.class.getName());
        return rightPlaceables;
    }

    public String showPlotName(){
        return "Tierra";
    }

}
