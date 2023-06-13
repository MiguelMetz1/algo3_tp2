package edu.fiuba.algo3.Enemies;


import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.*;

import java.util.Queue;


public class Spider extends Enemy{

    public Spider(GameMap map){
        super(map);
    }

    public Spider(GameMap map, Queue<Coordinate> path){
        super(map, path);
    }

    protected int amountOfCredits(){
        int random = (int) Math.round(Math.random());
        return 10*(random);
    }

    @Override
    protected int damage() {
        return 2;
    }

    @Override
    protected int energy() {
        return 2;
    }

    @Override
    protected int speed() {
        return 2;
    }

    public String toString(){
        return "Spider";
    }

}
