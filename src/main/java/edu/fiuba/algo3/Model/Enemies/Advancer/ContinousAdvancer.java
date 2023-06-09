package edu.fiuba.algo3.Model.Enemies.Advancer;

import edu.fiuba.algo3.Model.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;
import edu.fiuba.algo3.Model.TypeData.Speed.Speed;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContinousAdvancer implements Advancer {

    Speed speedToReach;

    Queue<Coordinate> path;

    Placeable entityToAdvance;

    GameMap map;

    Coordinate currentPosition;

    Distance remainingDistance;

    public ContinousAdvancer(GameMap map, Speed speedToReach, Coordinate currentPosition, Queue<Coordinate> path, Placeable entityToAdvance){
        this.map = map;
        this.speedToReach = speedToReach;
        this.path = path;
        this.entityToAdvance = entityToAdvance;
        this.currentPosition = currentPosition;
        this.remainingDistance = new Distance(0);
    }
    @Override
    public void advance() {

        this.remainingDistance = this.speedToReach.inDistancePerTurn().plus(this.remainingDistance);

        while ( this.mustToAdvance() ) {
            Coordinate nextPosition = this.path.peek();
            if( this.currentPosition.distanceTo(nextPosition).higher(this.remainingDistance) ) {
                this.currentPosition.updateTo(this.currentPosition.nextCoordinateInDirectionWithDistance(nextPosition, this.remainingDistance));
                this.remainingDistance = new Distance(0);
            }else {
                this.remainingDistance.reduceIn(this.currentPosition.distanceTo(nextPosition));
                this.locateTheEntity(this.path.poll());

            }
        }

        this.doTheLastStep();


    }

    private boolean mustToAdvance(){
        return ( (remainingDistance.higher( new Distance(1) ) || remainingDistance.equalsTo( new Distance(1)) ) && !path.isEmpty() );
    }

    private void doTheLastStep( ){
        if ( this.remainingDistance.higher(new Distance(0)) && !this.path.isEmpty()) {
            Coordinate lastAdvance = this.currentPosition.nextCoordinateInDirectionWithDistance(this.path.poll(), this.remainingDistance);
            this.locateTheEntity(lastAdvance);
        }
    }

    private void locateTheEntity( Coordinate position ){

        try {
            map.locateEntityIn(entityToAdvance, position);
        } catch (WrongPlace e) {
            Logger.getLogger("Advancer").log(Level.INFO, "The entity can't advance to this place.");
        }
    }

}
