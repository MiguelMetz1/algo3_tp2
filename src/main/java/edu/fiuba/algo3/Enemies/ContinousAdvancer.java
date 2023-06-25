package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Advancer.Advancer;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Distance;
import edu.fiuba.algo3.TypeData.Speed;

import java.util.Queue;

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
            Coordinate nextPosition = this.path.poll();
            this.locateTheEntity(nextPosition);
            this.remainingDistance.reduceIn(1);
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
            System.out.println("The entity can't advance to this place.");
        }
    }

}