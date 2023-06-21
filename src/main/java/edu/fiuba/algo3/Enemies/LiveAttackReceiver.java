package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.TypeData.Attribute;
import edu.fiuba.algo3.TypeData.Buff;

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
