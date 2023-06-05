package edu.fiuba.algo3.Defenses.States;

public class UnderConstruction implements Builder {

    int timeofConstruction;
    private Builder finalizedState;

    public UnderConstruction (int timeOfConstruction, Builder finalizedState) {

        this.timeofConstruction = timeOfConstruction;
        this.finalizedState = finalizedState;
    }

    @Override
    public Builder nextBuild(){
        this.timeofConstruction -= 1;
        if( this.buildFinished() ){
            return this.finalizedState;
        }
        return this;
    }

    @Override
    public boolean buildFinished() {
         return (timeofConstruction <= 0);
    }
}
