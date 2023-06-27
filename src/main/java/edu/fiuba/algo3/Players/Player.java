package edu.fiuba.algo3.Players;
import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Enemies.Interface.Target;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Exceptions.WrongPlayerName;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.FinalGangway;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.Shop.Buyer;
import edu.fiuba.algo3.TypeData.Buff.Attribute;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Credits.Credits;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Life.Life;
import edu.fiuba.algo3.TypeData.Name.Name;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player implements Target, Placeable, Buyer, Looter {

    private Life life;

    private ArrayList<Attribute> attributes;

    private Coordinate position;

    private LinkedList<Defense> defenses;

    private ArrayList<Enemy> enemies;
    private Queue<ArrayList<Enemy>> troops;

    Credits credits;

    Name name;

    GameMap map;

    public Player(Name name, GameMap map, Coordinate coordinate, Queue<ArrayList<Enemy>> troops, ArrayList<Enemy> enemies) {
        if (!this.rightName(name.getName())) {
            throw new WrongPlayerName("The player needs as less a six characters name.");
        }
        this.troops = troops;
        this.enemies = enemies;
        this.life = new Life(20);
        this.attributes = new ArrayList<>();
        this.attributes.add(life);
        this.defenses = new LinkedList<>();
        this.map = map;
        this.position = new Coordinate(0, 0);
        this.locateCharacter(map, coordinate);
        this.credits = new Credits(playerCredits());
        this.name = name;
    }

    /*public PlayerCharacter( Name name, GameMap map, Coordinate coordinate ) {
        if( !this.rightName(name.getName())){
            throw new WrongPlayerName("The player needs as less a six characters name.");
        }
        this.life = new Life(20);
        this.positionedPlace = new NullPlot();
        this.attributes = new ArrayList<>();
        this.attributes.add(life);
        this.defenses = new LinkedList<>();
        this.map = map;
        this.position = new Coordinate(0,0);
        this.locateCharacter(map, coordinate);
        this.credits = new Credits(playerCredits());
        this.name = name;
        this.enemies = new ArrayList<>();
        this.troops = new LinkedList<>();
        this.defenses = new LinkedList<>();
    }*/

    private boolean rightName(String name) {
        return (name.length() >= 6);
    }

    private int playerCredits() {
        return 100;
    }

    private void locateCharacter(GameMap map, Coordinate position) {
        try {
            map.locateEntityIn(this, position);
        } catch (WrongPlace e) {
            Logger.getLogger("Placeable").log(Level.WARNING, "The player character was located in a bad place");
        }
    }

    public void locateLastDefense(Coordinate coordinate) throws WrongPlace {
        Defense lastDefense = defenses.getLast();
        this.map.locateEntityIn(lastDefense, coordinate);
    }

    @Override
    public void takeBuff(Buff buff) {
        if (!this.isDead()) {
            for (Attribute attribute : attributes) {
                attribute.applyBuff(buff);
            }
        }

    }

    public void addDefense(Defense defense) {
        if (!this.isDead()) {
            this.defenses.add(defense);
        }
    }

    public void attackFirstDefense() {

        if (!this.defenses.isEmpty()) {
            Defense defense = this.defenses.poll();
            defense.removeFromYourPlot();
        }

    }

    public void makeDefensesAttack() {
        for (Defense defense : defenses) {
            defense.attack(enemies);
            removeDeadEnemies();
        }
    }

    private void removeDeadEnemies() {

        ArrayList<Enemy> deadEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            enemy.finalizeYourWay(deadEnemies);
        }
        this.enemies.removeAll(deadEnemies);
    }

    @Override
    public void locateIn(Coordinate position, Plot plot) throws WrongPlace {
        if (!this.passablePlots().contains(plot.getClass().getName())) {
            throw new WrongPlace("The player character cannot be located here.");
        }
        this.position.updateTo(position);
    }

    @Override
    public boolean distanceToBiggerThan(Coordinate position, Distance attackDistance) {
        return this.position.distanceTo(position).higher(attackDistance);
    }

    public void buildDefenses() {
        for (Defense defense : defenses) {
            defense.continueWithTheConstruction();
        }
    }

    private boolean isDead() {
        Life deadEntityLife = new Life(0);
        return (deadEntityLife.higher(this.life) || deadEntityLife.equals(this.life));
    }

    public String won() {
        if (this.isDead()) {
            return "Lose.";
        }
        if (troops.isEmpty() && enemies.isEmpty()) {
            return "Won.";
        }
        return "In game.";
    }

    @Override
    public void transferCredits(Credits creditsToGive) {
        creditsToGive.transferCreditsTo(this.credits);
    }

    @Override
    public void wasteCredits(Credits amountToWaste) throws InsuficientCredits {
        if (amountToWaste.higherCreditsThan(this.credits)) {
            throw new InsuficientCredits("The player has not got sufficient credits.");
        }
        this.credits.wasteCredits(amountToWaste);
    }

    public ArrayList<String> passablePlots() {
        ArrayList<String> passablePlots = new ArrayList<>();
        passablePlots.add(FinalGangway.class.getName());
        return passablePlots;
    }

    public void destroyDefense(Defense defense) {
        this.defenses.remove(defense);
    }

    public void showCredits() {
        this.credits.showCredits();
    }

    public Name getName() {

        return this.name;
    }

    public String lastDefenseImage() {
        return this.defenses.getLast().image();
    }
}
