package edu.fiuba.algo3.TypeData;

import java.util.ArrayList;

public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coordinate coordinate) {
        return ((this.x == coordinate.x) && (this.y == coordinate.y));
    }

    public int hashCode() {
        return (this.x * 10000) + this.y;
    }

    public ArrayList<Coordinate> getAround(int distance) {
        ArrayList<Coordinate> tmpCoordinates = new ArrayList<Coordinate>();

        for (int i=this.x-distance; i<=this.x+distance; i++){
            for (int j=this.y-distance; j<=this.y+distance; j++){
                tmpCoordinates.add(new Coordinate(i, j));
            }
        }
        return tmpCoordinates;
    }
}
