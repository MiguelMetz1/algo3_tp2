package edu.fiuba.algo3.TypeData;

public interface Reducible<T extends Reducible> {
    void reduceIn(T reducible);
}
