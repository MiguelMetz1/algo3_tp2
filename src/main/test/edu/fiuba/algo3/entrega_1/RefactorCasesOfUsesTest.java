package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Defenses.TowerWhite;
import edu.fiuba.algo3.Exceptions.CannotConstruction;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Credits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RefactorCasesOfUsesTest {


    @Test
    public void whiteTowerConstructionTimeIsCorrect(){
        TowerWhite towerWhite = new TowerWhite();
        try {
            towerWhite.buy(new Credits(100));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        towerWhite.putIn(new Coordinate(1,3));
        //Intentar construir y que no tire error

        //assertThrows(CannotAttack.class,()->{towerWhite.attack();});
        assertDoesNotThrow(()->{towerWhite.build();},"The defense is under construction.");

        //assertDoesNotThrow(()->{towerWhite.attack();}, "An Attacker in construction cant attack.");
        assertThrows(CannotConstruction.class, ()->{towerWhite.build();});


    }
}
