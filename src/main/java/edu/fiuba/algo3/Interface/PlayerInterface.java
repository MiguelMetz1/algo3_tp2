package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Turn.Turner;
import edu.fiuba.algo3.TypeData.Coordinate;

public interface PlayerInterface extends Turner {

    public void requireAction();

    public void build(Defense defense, Coordinate coordinate);

}
