package edu.fiuba.algo3.TypeData.Coordinate;

import edu.fiuba.algo3.TypeData.Distance.Distance;

import java.util.ArrayList;

public class Coordinate {
    double x;
    double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object coordinate) {
        Coordinate otherCoordinate = (Coordinate) coordinate;

        int integerX = (int) this.x;
        int integerY = (int) this.y;

        if( this.x - integerX > 0 ){
            integerX++;
        }

        if( this.y - integerY > 0 ){
            integerY++;
        }
        return ( otherCoordinate.x == (double) integerX && otherCoordinate.y == (double) integerY );
    }

    @Override
    public final int hashCode() {
        int digitsOfY = this.digitsOf(this.y);
        int integerX = (int) this.x;
        int integerY = (int) this.y;
        if( this.x - integerX > 0 ){
            integerX++;
        }
        if( this.y - integerY > 0 ){
            integerY++;
        }
        return integerX * Math.round((float) Math.pow(10,digitsOfY+1)) + integerY;
    }

    private int digitsOf(double number){
        int digits = 1;
        while( number >= 10 ){
            number = number/10;
            digits++;
        }
        return digits;
    }

    public Coordinate nextCoordinateInDirectionWithDistance( Coordinate coordinate, Distance distance ){
         Coordinate slope = coordinate.scalarMultiplication( -1 ).plus( this ).scalarMultiplication(-1);
         Coordinate normalizedSlope = slope.scalarMultiplication( 1/slope.norm() );
         Coordinate result = normalizedSlope.scalarMultiplication(distance).plus(this);
         return result;
    }

    public Coordinate perpendicularProjectionInVerticalLine(Coordinate toProject){
        return new Coordinate(this.x, toProject.y);
    }

    public void updateTo(Coordinate coordinate ){
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    public Coordinate scalarMultiplication( double scalar ){
        return new Coordinate( this.x*scalar, this.y*scalar );
    }

    public Coordinate scalarMultiplication( Distance distance ){
        return distance.multiplication((new Coordinate( this.x, this.y )));
    }

    public Coordinate plus( Coordinate otherCoordinate ){
        return new Coordinate( this.x + otherCoordinate.x, this.y + otherCoordinate.y);
    }

    private double norm(){
       return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Distance distanceTo( Coordinate otherDistance ){
        double distance = Math.sqrt(Math.pow(this.x- otherDistance.x, 2) + Math.pow(this.y - otherDistance.y,2));
        return new Distance(distance);
    }

    public String toString(){
        return ("("+this.x+","+this.y+")");
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String toIntString(){
        return ("("+(int)this.x+","+(int)this.y+")");
    }


}
