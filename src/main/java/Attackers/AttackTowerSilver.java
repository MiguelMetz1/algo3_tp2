package Attackers;

import Enemies.Enemy;
import Map.Map;
import TypeData.RangeAttack;

public class AttackTowerSilver implements Attack{

    private RangeAttack rangeAttack;
    private Map map;


    public AttackTowerSilver(RangeAttack rangeAttack) {
        this.rangeAttack = rangeAttack;
        this.map = Map.getMap();
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
