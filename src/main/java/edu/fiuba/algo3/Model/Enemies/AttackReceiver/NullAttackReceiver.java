package edu.fiuba.algo3.Model.Enemies.AttackReceiver;

import edu.fiuba.algo3.Model.TypeData.Buff.Buff;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullAttackReceiver implements AttackReceiver {

    @Override
    public void takeBuff(Buff buff) {
        Logger.getLogger("AttackReceiver").log(Level.WARNING, "this entity can't receive attacks in this moments.");
    }
}
