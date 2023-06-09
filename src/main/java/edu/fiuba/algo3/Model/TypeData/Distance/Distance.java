package edu.fiuba.algo3.Model.TypeData.Distance;


import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

public class Distance {
    double distance;

    public Distance(double distance) {
        this.distance = distance;
        if( distance < 0){
            this.distance = 0;
        }

    }

    public boolean higher( Distance otherDistance ){
        return (this.distance > otherDistance.distance);
    }

    public boolean equalsTo( Distance otherDistance ){
        return this.distance == otherDistance.distance;
    }

    public Coordinate multiplication(Coordinate coordinate) {
        return coordinate.scalarMultiplication(this.distance);
    }

    public Distance plus( Distance otherDistance ){
        return new Distance( this.distance + otherDistance.distance );
    }

    public void reduceIn(Distance otherDistance) {
        this.distance -= otherDistance.distance;
    }
}
