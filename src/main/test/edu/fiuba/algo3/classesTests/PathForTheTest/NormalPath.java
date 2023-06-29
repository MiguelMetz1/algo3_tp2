package edu.fiuba.algo3.classesTests.PathForTheTest;

import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

import java.util.LinkedList;
import java.util.ListIterator;

public class NormalPath {

    /* this is a class to help testing, its have hardcoded the path of enemies to simplify
    the test cases */

    public LinkedList<Coordinate> getPath(){
        LinkedList<Coordinate> path = new LinkedList<>();
        path.add(new Coordinate(2,1));
        path.add(new Coordinate(2,2));
        path.add(new Coordinate(2,3));
        path.add(new Coordinate(2,4));
        path.add(new Coordinate(2,5));
        path.add(new Coordinate(2,6));
        path.add(new Coordinate(2,7));
        path.add(new Coordinate(3,7));
        path.add(new Coordinate(4,7));
        path.add(new Coordinate(5,7));
        path.add(new Coordinate(6,7));
        path.add(new Coordinate(7,7));
        path.add(new Coordinate(8,7));
        path.add(new Coordinate(9,7));
        path.add(new Coordinate(9,8));
        path.add(new Coordinate(9,9));
        path.add(new Coordinate(9,10));
        path.add(new Coordinate(9,11));
        path.add(new Coordinate(10,11));
        path.add(new Coordinate(11,11));
        path.add(new Coordinate(12,11));
        path.add(new Coordinate(13,11));
        path.add(new Coordinate(14,11));
        path.add(new Coordinate(15,11));
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
