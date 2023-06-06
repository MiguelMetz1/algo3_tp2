package edu.fiuba.algo3.Turn;

public class ChangedTurn implements Turner{

    public Turner returnTurn(){
        return new NotChangedTurn();
    }
    @Override
    public boolean turnChanged() {
        return true;
    }
}
