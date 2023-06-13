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
}
