package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Ant;
import edu.fiuba.algo3.Enemies.Spider;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class Rocky extends Plot{

    public Rocky(Coordinate coordinate){
        super(coordinate);
    }

    @Override
    protected ArrayList<String> rightPlaceables() {
        ArrayList<String> rightPlaceables = new ArrayList<>();
        return rightPlaceables;
    }


    public String showPlotName(){
        return "Piedra";
    }

}
