package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Defenses.Builder.NullBuilder;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Defenses.Builder.UnderConstructionAttacker;
import edu.fiuba.algo3.Defenses.Builder.Builder;
import edu.fiuba.algo3.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Buff.EnergyInstantDecrementerDebuff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Coordinate.HellsCoordinate;
import edu.fiuba.algo3.TypeData.Damage.AdditiveDamage;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Time;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Defense implements Placeable {

    protected Attacker attacker;

    protected Builder<Attacker<Enemy>> builder;

    protected Plot positionedPlot;

    protected Coordinate position;

    protected Time timeOfConstruction;

    protected ArrayList< String > rightPlots;

    public Defense() {
        this.builder = new NullBuilder();
        this.positionedPlot = new NullPlot();
        this.attacker = new NullAttacker();
        this.rightPlots = this.rightPlots();
        this.position = new HellsCoordinate();

    }

    public void attack(ArrayList<Enemy> enemies){
        this.attacker.attack(enemies);
    }

    public void continueWithTheConstruction() {
        this.attacker = this.builder.actualState();
    }

  /*  protected void setAttacker(Attacker attacker){
        this.attacker = attacker;
    }*/

    @Override
    public void locateIn( Coordinate position, Plot plot) throws WrongPlace {
        if( !this.isARightPlot(plot) ){
            throw new WrongPlace("The defense cant be located in this plot.");
        }
        Logger.getLogger("Placeable").log(Level.INFO, "A Tower has");

        plot.receive(this);
        this.positionedPlot = plot;
        this.position.updateTo(position);
        this.timeOfConstruction = new Time(timeOfConstruction());
        this.builder = new UnderConstructionAttacker(
                timeOfConstruction,
                this.getBuff(),
                this.position,
                new Distance(range())
        );

        this.rightPlots.clear();

    }

    protected Buff getBuff(){
        return new EnergyInstantDecrementerDebuff(new AdditiveDamage(damage()));
    }

    protected boolean isARightPlot( Plot plot ){
        for ( String rightPlotType : this.rightPlots ){
            if( plot.hasType( rightPlotType ) ){
                return true;
            }
        }
        return false;
    }

    protected abstract int range();

    protected abstract int timeOfConstruction();

    protected abstract int damage();

    public abstract String toString();

    protected ArrayList<String> rightPlots(){
        ArrayList<String> passablePlots = new ArrayList<>();
        passablePlots.add( Ground.class.getName() );
        return passablePlots;
    }

    public void destroyOn( LinkedList<Defense> activeDefenses ) {
        activeDefenses.remove(this);
        this.positionedPlot.remove(this);
    }

    public abstract String image();

    public void addImage(Map<Coordinate, Button> buttonMap, Map<Coordinate, StackPane> stackPaneMap) {


        Button button = buttonMap.get(this.position);
        StackPane stackPane = stackPaneMap.get(this.position);

        Image overlayImage1 = new Image(this.image());
        ImageView overlayImageView1 = new ImageView(overlayImage1);
        /*overlayImageView1.setStyle("-fx-max-height: 40px");
        overlayImageView1.setStyle("-fx-max-width: 40px");*/
        overlayImageView1.setFitHeight(30);
        overlayImageView1.setFitWidth(30);


        stackPane.getChildren().addAll(overlayImageView1);
        overlayImageView1.toBack();

        button.setGraphic(stackPane);
    }

    public void showRange(Coordinate coordinate, Button button) {
        if(!( coordinate.distanceTo(this.position).higher( new Distance(range()) ) )){
            button.setStyle("-fx-opacity: 0.8");
        }
    }

    public void findPosition(HashMap<Coordinate, ArrayList<String>> coordinateType) {
        ArrayList<String> types = coordinateType.getOrDefault(this.position, new ArrayList<>());
        types.add(this.toString());
        coordinateType.put(this.position, types);
    }

    public void remainingTime(Time timeOfConstruction) {
        timeOfConstruction.copy(this.timeOfConstruction);
    }
}