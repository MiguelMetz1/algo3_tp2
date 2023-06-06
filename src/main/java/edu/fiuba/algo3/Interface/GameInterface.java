package edu.fiuba.algo3.Interface;


import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.CannotAttack;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.Exceptions.EnemyNotFound;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Turn.ChangedTurn;
import edu.fiuba.algo3.Turn.NotChangedTurn;
import edu.fiuba.algo3.Turn.Turner;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;
import java.util.Scanner;

public class GameInterface implements ComputerInterface, PlayerInterface{
    ArrayList<Defense> defenses;
    Player player;
    Turner turner;

    public GameInterface(Player player){
        this.turner = new NotChangedTurn();
        this.player = player;
        this.defenses = new ArrayList<>();
    }

    public void requireAction(){
        //System.out.println("Que quiere hacer? pasar o construir");
        //Scanner scanner = new Scanner(System.in);
        //String action = scanner.nextLine();
        /*switch (action){
            case "pasar":
                changeTurn();
                break;
            case "construir":
                //chooseConstruction();
                break;
            default:
                break;

        }*/
        changeTurn();
    }

    public void build(Defense defense, Coordinate coordinate) {
        if (GameMap.getMap().canBuild(defense, coordinate)){
            try {
                player.buy(defense);
                defense.putIn(coordinate);
                defenses.add(defense);
            } catch (InsuficientCredits e) {
                //Send message to Graphic interface : "You haven't got sufficient credits."
            } catch (CannotBuild e){
                //Send message to Graphic interface : "You cant build in this plot."
            }
        }
    }

    public void makeDefensesAttack(){
        for (Defense defense:defenses) {
            try {
                defense.attack();
            } catch (Exception e) {
                //throw new RuntimeException(e);
            }
        }
        this.checkGameStatus();
    }

    public void advanceEnemies(){
        GameMap.getMap().advanceEnemies();
    }

    public void changeTurn(){
        this.turner = this.turner.returnTurn();
    }

    public boolean turnChanged(){
        return turner.turnChanged();
    }

    public Turner returnTurn(){
        return turner.returnTurn();
    }

    public void spawnEnemies(){
        GameMap.getMap().spawnEnemies();
    }

    public void buildDefenses(){
        for (Defense defense:defenses) {
            try {
                defense.build();
            } catch (Exception e) {
                //throw new RuntimeException(e);
            }
        }
    }

    private boolean checkGameStatus(){
        if ( (!GameMap.getMap().MapHasEnemies()) && (!this.player.isDead()) ) {
            return true;
        }
        return false;
    }

    public String gameWon() {
        if (this.checkGameStatus()) {
            return "You Won";
        }
        return "Game Over";
    }

}
