package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Turn.Turner;
import edu.fiuba.algo3.TypeData.Coordinate;

public interface ComputerInterface extends Turner {

    public void makeDefensesAttack();

    public void build(Defense defense, Coordinate coordinate);

    public void advanceEnemies();

    public void spawnEnemies();

    public void buildDefenses();
}
