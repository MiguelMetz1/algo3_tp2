package edu.fiuba.algo3.Turn;

public class NotChangedTurn implements Turner {

    public Turner returnTurn(){
        return new ChangedTurn();
    }
    @Override
    public boolean turnChanged() {
        return false;
    }
}
