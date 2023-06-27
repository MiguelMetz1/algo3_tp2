package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.Towers.SilverTower;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PlotsTest {


    @Test
    public void rockyPlotCantRecieveTDefensesToBeBuilded(){



        Rocky rocky = new Rocky(new Coordinate(1,1));
        SilverTower silverTower = new SilverTower();
        assertThrows(WrongPlace.class, () -> rocky.receive(silverTower));



    }

    @Test
    public  void cantBePlacedTwoDefensesInTheSamePlot(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        SilverTower silverTower1 = new SilverTower();
        SilverTower silverTower2 = new SilverTower();
        assertDoesNotThrow( () -> map.locateEntityIn(silverTower1, new Coordinate(3,1)));
        assertThrows(WrongPlace.class, () -> map.locateEntityIn(silverTower2, new Coordinate(3,1)));

    }
}
