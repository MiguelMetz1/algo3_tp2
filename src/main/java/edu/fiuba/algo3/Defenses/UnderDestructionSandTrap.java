package edu.fiuba.algo3.Defenses;

import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Attacker.InConstructionAttacker;
import edu.fiuba.algo3.Defenses.States.Builder;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.TypeData.Buff;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Distance;

public class UnderDestructionSandTrap implements Builder<Deleter> {

    Buff debuff;

    int timeOfConstruction;

    Coordinate position;

    Distance range;

    PlayerCharacter playerCharacter;

    public UnderDestructionSandTrap(int timeOfConstruction, PlayerCharacter playerCharacter) {
        this.timeOfConstruction = timeOfConstruction;
        this.playerCharacter = playerCharacter;
    }

    @Override
    public Deleter actualState() {
        this.timeOfConstruction--;
        if( this.timeOfConstruction <= 0 ){
            return new SandTrapDeleter(playerCharacter);
        }
        return new NullDeleter();
    }

}
