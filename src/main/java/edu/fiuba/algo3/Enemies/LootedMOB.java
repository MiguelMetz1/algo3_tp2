package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Players.Looter;


public class LootedMOB implements Inventory{
    @Override
    public void transferLootTo(Looter player){
        System.out.println("This mob was looted.");
    }
}
