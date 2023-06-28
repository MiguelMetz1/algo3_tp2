package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Attacker.EnemiesAttacker.TowersAttacker;
import edu.fiuba.algo3.Enemies.Advancer.DamagedOwlAdvancer;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.Ground;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.TypeData.Buff.Attribute;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Energy.Energy;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public class Owl extends KillableEnemy {

    Coordinate finalGangway;

    private static int id = 0;
    private int idPersonal;

    public Owl(GameMap map, Queue<Coordinate> path, Coordinate finalGangway) {
        super(map, path);
        this.addOwlPassablePlots();
        this.finalGangway = finalGangway;
        this.setAttacker( new TowersAttacker( actualPosition ) );
    }

    protected ArrayList<Attribute> getBuffeablesAttributes(){
        ArrayList<Attribute> attributes = super.getBuffeablesAttributes();
        attributes.remove(this.speed);
        return  attributes;
    }

    private void addOwlPassablePlots(){
        this.addPassablePlot(Rocky.class.getName());
        this.addPassablePlot(Ground.class.getName());
    }
    public void takeBuff(Buff buff) {
        super.takeBuff(buff);
        Energy halfLife = new Energy(this.getLife()*0.5);
        if( !this.actualLifeHigher(halfLife) ){
            this.setAdvancer(new DamagedOwlAdvancer(this, this.map, this.actualPosition, this.speed, this.finalGangway));
        }

    }

    @Override
    protected double getLife() {
        return 5;
    }

    @Override
    protected double getSpeed() {
        return 5;
    }

    protected String enemyImage(){return "file:src/main/java/edu/fiuba/algo3/View/Images/owl.png";}

    protected String getType(){
        return "Owl Life: "+  Double.toString(this.getLife());
    }

    protected File soundFile(){
        return new File("src/main/java/edu/fiuba/algo3/View/Sounds/owlSound.wav").getAbsoluteFile();
    }
}
