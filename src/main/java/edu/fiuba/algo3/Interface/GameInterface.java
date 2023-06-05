package edu.fiuba.algo3.Interface;


import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Turn.ChangedTurn;
import edu.fiuba.algo3.Turn.NotChangedTurn;
import edu.fiuba.algo3.Turn.Turner;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class GameInterface {

    ArrayList<Defense> defenses;
    Player player;
    Turner turner;

    public GameInterface(Player player){
        this.turner = new NotChangedTurn();
        this.player = player;
        this.defenses = new ArrayList<>();
    }

    public void requireAction(){

    }

    private void build(Defense defense, Coordinate coordinate){

    }
    public void makeDefensesAttack(){

    }
    public void advanceEnemies(){

    }
    private void changeTurn(){
        this.turner = new ChangedTurn();

    }

    public boolean turnChanged(){
        return turner.turnChanged();
    }





}
