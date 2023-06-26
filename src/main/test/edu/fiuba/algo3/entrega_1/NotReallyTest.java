package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Defenses.Towers.SilverTower;
import edu.fiuba.algo3.Defenses.Towers.WhiteTower;
import edu.fiuba.algo3.Defenses.Traps.SandTrap;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Name.Name;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class NotReallyTest{

    @Test
    public void theDefensesReturnCorrectlyTheirTypeInStringAndTheImage() {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();


        ArrayList<Enemy> enemies = new ArrayList<>();
        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);


        WhiteTower whiteTower = new WhiteTower();
        SilverTower silverTower = new SilverTower();
        SandTrap sandTrap = new SandTrap(player);


        assert(whiteTower.toString() == "White Tower");
        assert(silverTower.toString() ==  "Silver Tower");
        assert(sandTrap.toString() ==  "SandTrap");

        assert(whiteTower.image() ==  "file:src/main/java/edu/fiuba/algo3/View/Images/whiteTower.png");
        assert(silverTower.image() == "file:src/main/java/edu/fiuba/algo3/View/Images/silverTower.png");
        assert(sandTrap.image() ==   "file:src/main/java/edu/fiuba/algo3/View/Images/sandTrap.jpeg");

    }


}
