package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Ant;
import edu.fiuba.algo3.Enemies.Spider;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class Gangway extends Plot{

    public Gangway(Coordinate coordinate){
        super(coordinate);
    }

    @Override
    protected ArrayList<String> rightPlaceables() {
        ArrayList<String> rightPlaceables = new ArrayList<>();
        rightPlaceables.add( Spider.class.getName() );
        rightPlaceables.add( Ant.class.getName() );
        return rightPlaceables;
    }

    public String toString(){
        return "Gangway";
    }


}
