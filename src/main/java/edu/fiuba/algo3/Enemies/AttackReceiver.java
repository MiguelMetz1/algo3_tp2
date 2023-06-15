package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.TypeData.Buff;
import edu.fiuba.algo3.TypeData.Damage;

public interface AttackReceiver {
    void takeDamage(Damage damage);
    void takeBuff( Buff buff);
}
