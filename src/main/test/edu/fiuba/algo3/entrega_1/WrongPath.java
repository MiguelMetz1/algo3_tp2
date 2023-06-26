package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;

import java.util.LinkedList;
import java.util.ListIterator;

public class WrongPath {

    public LinkedList<Coordinate> getPath(){
        LinkedList<Coordinate> path = new LinkedList<>();
        path.add(new Coordinate(3,1));
        path.add(new Coordinate(3,2));

        return path;
    }

    public LinkedList<Coordinate> copyPath(){
        LinkedList<Coordinate> path = this.getPath();
        ListIterator<Coordinate> iterator = path.listIterator(0);
        LinkedList<Coordinate> newPath = new LinkedList<>();
        while (iterator.hasNext()){
            newPath.add(iterator.next());
        }
        return newPath;
    }
}
