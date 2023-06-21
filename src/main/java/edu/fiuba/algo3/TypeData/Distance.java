package edu.fiuba.algo3.TypeData;


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

    public void reduceIn(double i) {
        this.distance -= i;
        if( this.distance < 0){
            this.distance = 0;
        }
    }
}
