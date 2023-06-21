package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.TypeData.Buff;
import edu.fiuba.algo3.TypeData.Damage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullAttackReceiver implements AttackReceiver{

    @Override
    public void takeBuff(Buff buff) {
        Logger.getLogger("AttackReceiver").log(Level.WARNING, "this entity can't receive attacks in this moments.");
    }
}
