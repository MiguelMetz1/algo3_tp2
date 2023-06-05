package edu.fiuba.algo3.Attackers;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.RangeAttack;

public class AttackTowerSilver implements Attack{

    private RangeAttack rangeAttack;
    private GameMap map;


    public AttackTowerSilver(RangeAttack rangeAttack) {
        this.rangeAttack = rangeAttack;
        this.map = GameMap.getMap();
    }
    @Override
    public void attack(Enemy enemy) {
        
    }

    @Override
    public boolean withinRange() {
        //to implements...
        return false;
    }
}
