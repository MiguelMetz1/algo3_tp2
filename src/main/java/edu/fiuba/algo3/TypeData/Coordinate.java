package edu.fiuba.algo3.TypeData;
public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object coordinate) {
        return ((this.x == ((Coordinate) coordinate).x) && (this.y == ((Coordinate) coordinate).y));
    }

    @Override
    public final int hashCode() {
        int digitsOfY = this.digitsOf(this.y);
        return (this.x * Math.round((float) Math.pow(10,digitsOfY))) + this.y;
    }

    private int digitsOf(int number){
        int digits = 1;
        while( number >= 10 ){
            number = number/10;
            digits++;
        }
        return digits;
    }

    public Distance distanceTo( Coordinate otherDistance ){
        double distance = Math.sqrt(Math.pow(this.x- otherDistance.x, 2) + Math.pow(this.y - otherDistance.y,2));
        return new Distance(distance);
    }

    public String toString(){
        return ("("+this.x+","+this.y+")");
    }

}
