package edu.fiuba.algo3.Plots;


import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class FinalGangway extends Gangway {

    public FinalGangway(Coordinate coordinate){
        super(coordinate);
    }

    public String toString(){
        return "FinalGangway";
    }

    protected ArrayList<String> rightPlaceables(){
        ArrayList<String> rightPlaceables = super.rightPlaceables();
        rightPlaceables.add(Player.class.getName());
        return rightPlaceables;
    }

}


