package edu.fiuba.algo3.Model.Players;
import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Model.Enemies.Enemy;
import edu.fiuba.algo3.Model.Enemies.Interface.Target;
import edu.fiuba.algo3.Model.Exceptions.InsufficientCredits;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.Exceptions.WrongPlayerName;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.TypeData.Name.Name;
import edu.fiuba.algo3.Model.Plots.FinalGangway;
import edu.fiuba.algo3.Model.Plots.Plot;
import edu.fiuba.algo3.Model.Shop.Buyer;
import edu.fiuba.algo3.Model.TypeData.Buff.Attribute;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Credits.Credits;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;
import edu.fiuba.algo3.Model.TypeData.Life.Life;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player implements Target, Placeable, Buyer, Looter {

    private Life life;

    private ArrayList<Attribute> attributes;

    private Coordinate position;

    private LinkedList<Defense> activeDefenses;

    private LinkedList<Defense> boughtDefenses;

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
        this.activeDefenses = new LinkedList<>();
        this.boughtDefenses = new LinkedList<>();
        this.map = map;
        this.position = new Coordinate(0, 0);
        this.locateCharacter(map, coordinate);
        this.credits = new Credits(playerCredits());
        this.name = name;
    }

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

    public void locateDefenses(Coordinate coordinate) throws WrongPlace {
        if( !boughtDefenses.isEmpty() ) {
            Defense firstBoughtdefense = boughtDefenses.getFirst();
            this.map.locateEntityIn(firstBoughtdefense, coordinate);
            boughtDefenses.remove(firstBoughtdefense);
            this.activeDefenses.add(firstBoughtdefense);
        }
    }

    @Override
    public void takeBuff(Buff buff) {
        if (!this.isDead()) {
            for (Attribute attribute : attributes) {
                attribute.applyBuff(buff);
            }
            Logger.getLogger("Player").log(Level.INFO, "The player has received an attack, actual life: " + this.life);
        }

    }

    public void giveDefense(Defense defense) {
        if (!this.isDead()) {
            this.boughtDefenses.add(defense);
        }
    }

    public void attackFirstDefense() {
        int amountOfDefenses = this.activeDefenses.size();
        ArrayList<Defense> defensesCopy = new ArrayList<>(this.activeDefenses);
        Iterator<Defense> defenseIterator = defensesCopy.iterator();
        while ( defenseIterator.hasNext() && amountOfDefenses == this.activeDefenses.size() ) {
                defenseIterator.next().destroyOn(this.activeDefenses);
        }

    }

    public void makeDefensesAttack() {
        for (Defense defense : activeDefenses) {
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
        for (Defense defense : activeDefenses) {
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
        Logger.getLogger("Player").log(Level.INFO, "Credits was transferred to player, actual credits: " + this.credits);
    }

    @Override
    public void wasteCredits(Credits amountToWaste) throws InsufficientCredits {
        if (amountToWaste.higherCreditsThan(this.credits)) {
            throw new InsufficientCredits("The player has not got sufficient credits.");
        }
        this.credits.wasteCredits(amountToWaste);
        Logger.getLogger("Player").log(Level.INFO, "The player wasted credits, remaining creits: " + this.credits);
    }

    public ArrayList<String> passablePlots() {
        ArrayList<String> passablePlots = new ArrayList<>();
        passablePlots.add(FinalGangway.class.getName());
        return passablePlots;
    }

    public void destroyDefense(Defense defense) {
        this.activeDefenses.remove(defense);
    }

    public Name getName() {

        return this.name;
    }

    public String remainingLife() {
        return this.life.toString();
    }

    public String remainingCredits() {
        return this.credits.toString();
    }

    public void findTowersPosition(HashMap<Coordinate, ArrayList<String>> coordinateType) {
        for( Defense defense: activeDefenses){
            defense.findPosition(coordinateType);
        }
    }
}
