package edu.fiuba.algo3.Defenses.States;
import edu.fiuba.algo3.Exceptions.CannotConstruction;

public class FinishedConstruction implements Builder {

    public Builder nextBuild() throws CannotConstruction {
        //TODO: Implement a dictionary for the message
        throw new CannotConstruction("The defense completed its construction");
    }

    @Override
    public boolean buildFinished() {
        return true;
    }
}


