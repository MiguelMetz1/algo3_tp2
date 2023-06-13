package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Advancer.Advancer;

public class DeadAdvancer implements Advancer {
    @Override
    public void advance() {
        System.out.println("This entity can't advance.");
    }
}
