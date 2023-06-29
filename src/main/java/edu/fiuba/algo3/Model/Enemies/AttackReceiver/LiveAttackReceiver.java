package edu.fiuba.algo3.Model.Enemies.AttackReceiver;

import edu.fiuba.algo3.Model.TypeData.Buff.Attribute;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;

import java.util.ArrayList;

public class LiveAttackReceiver implements AttackReceiver {

    ArrayList<Attribute> attributes;

    public LiveAttackReceiver( ArrayList<Attribute> attributes ) {
        this.attributes = attributes;
    }

    @Override
    public void takeBuff(Buff buff) {
        for (Attribute attribute : attributes) {
            attribute.applyBuff(buff);
        }
    }


}
