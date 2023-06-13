package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.Energy;

public class LiveAttackReceiver implements AttackReceiver {

    Energy energy;

    public LiveAttackReceiver( Energy energy ) {
        this.energy = energy;
    }


    @Override
    public void takeDamage(Damage damage) {
        damage.applyDamage(this.energy);
    }


}
