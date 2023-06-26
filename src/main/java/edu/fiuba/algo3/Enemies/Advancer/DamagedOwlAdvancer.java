package edu.fiuba.algo3.Enemies.Advancer;

import edu.fiuba.algo3.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Speed.Speed;

public class DamagedOwlAdvancer implements Advancer {

    Placeable owl;

    Coordinate actualPosition;

    Speed speed;

    GameMap map;

    Coordinate destination;

    public DamagedOwlAdvancer(Placeable owl, GameMap map, Coordinate actualPosition, Speed speed, Coordinate destination ){
        this.owl = owl;
        this.map = map;
        this.actualPosition = actualPosition;
        this.speed = speed;
        this.destination = destination;
    }
    @Override
    public void advance() {
        Distance distanceToAdvance = this.speed.inDistancePerTurn();
        Coordinate nextPosition = this.actualPosition.nextCoordinateInDirectionWithDistance( destination, distanceToAdvance );
        Distance distanceToReach = this.actualPosition.distanceTo(destination);

        if( distanceToReach.higher(distanceToAdvance) ) {
            this.locateEntityIn(nextPosition);
        }else{
            this.locateEntityIn(destination);
        }

    }

    private void locateEntityIn(Coordinate position ){

        try {
            this.map.locateEntityIn(owl, position);
            this.actualPosition.updateTo(position);

        } catch (WrongPlace e) {
            throw new RuntimeException(e);
        }

    }
}
