package edu.fiuba.algo3.TypeData;

import java.util.ArrayList;

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
        return (this.x * 10000) + this.y;
    }

    public ArrayList<Coordinate> getAround(int distance) {
        ArrayList<Coordinate> tmpCoordinates = new ArrayList<Coordinate>();

        for (int i = this.x - distance; i <= this.x + distance; i++) {
            for (int j = this.y - distance; j <= this.y + distance; j++) {
                tmpCoordinates.add(new Coordinate(i, j));
            }
        }
        return tmpCoordinates;
    }
    public String returnCoordinate(){
        return ("("+this.x+","+this.y+")");
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
