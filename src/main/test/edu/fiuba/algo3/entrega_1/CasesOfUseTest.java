package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.TowerSilver;
import edu.fiuba.algo3.Defenses.TowerWhite;
import edu.fiuba.algo3.Enemies.Ant;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.FinalGangway;
import edu.fiuba.algo3.Plots.Ground;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.Enemies.Spider;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.Plots.Gangway;
import edu.fiuba.algo3.TypeData.Credits;
import org.junit.jupiter.api.Test;


/*
Caso de uso 1
●Verificar que jugador empieza con la vida y los créditos correspondientes.
Caso de uso 2
●Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
“operativas” cuando ya se terminaron de construir.
Caso de uso 3
●Verificar que se disponga de credito para realizar las construcciones.
Caso de uso 4
●Verificar solo se pueda construir defensas sobre tierra (y verificar lo contrario)
Caso de uso 5
●Verificar que las defensas ataquen dentro del rango esperado (y verificar lo contrario)
Caso de uso 6
●Verificar que las unidades enemigas son dañadas acorde al ataque recibido.
Caso de uso 7
●Verificar que las unidades enemigas solo se muevan por la parcela autorizada.
Caso de uso 8
●Verificar que al destruir una unidad enemiga, el jugador cobra el crédito que le
corresponde.

* */

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class CasesOfUseTest {

    @Test
    public void playerStartsWithCorrespondingCredits() {
        //Vida:20     Creditos:100
        Player player = new Player();


        for (int i = 0; i < 5; i++) {
            assertDoesNotThrow(() -> {
                player.buy(new TowerSilver());
            }, "Insuficient Credits");
        }



        assertThrows(InsuficientCredits.class, () -> {
            player.buy(new TowerSilver());
        });
    }
    @Test
    public void playerStartsWithCorrespondingLife() {
        Player player = new Player();

        Spider spider = new Spider();


        for (int i=0;i<9;i++){
            spider.attack(player);
            assertEquals(false, player.isDead());
        }
        spider.attack(player);

        assertEquals(true, player.isDead());


    }

    /*  Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
        “operativas” cuando ya se terminaron de construir.*/

    @Test
    public void whiteTowerConstructionTimeIsCorrect(){
        TowerWhite towerWhite = new TowerWhite();
        try {
            towerWhite.buy(new Credits(100));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        towerWhite.putIn(new Coordinate(3,1));
        //Intentar construir y que no tire error

        assertThrows(CannotAttack.class,()->{towerWhite.attack();});
        assertDoesNotThrow(()->{towerWhite.build();},"The defense is under construction.");

        assertDoesNotThrow(()->{towerWhite.attack();}, "An Attacker in construction cant attack.");
        assertThrows(CannotConstruction.class, ()->{towerWhite.build();});


    }

    @Test
    public void silverTowerConstructionTimeIsCorrect(){
        TowerSilver silverTower = new TowerSilver();
        try {
            silverTower.buy(new Credits(100));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        silverTower.putIn(new Coordinate(3,1));
        //Intentar construir y que no tire error

        assertThrows(CannotAttack.class,()->{silverTower.attack();});
        assertDoesNotThrow(()->{silverTower.build();},"The defense is under construction.");

        assertThrows(CannotAttack.class,()->{silverTower.attack();});
        assertDoesNotThrow(()->{silverTower.build();},"The defense is under construction.");

        assertDoesNotThrow(() -> {silverTower.attack();}, "An Attacker in construction cant attack." );
        assertThrows(CannotConstruction.class, ()->{silverTower.build();});



    }

    //Verificar que se disponga de credito para realizar las construcciones

     @Test
     public void amountOfCreditsIsSuficientToBuy(){
        Player player = new Player();

        TowerSilver towerSilver = new TowerSilver();

        TowerWhite towerWhite = new TowerWhite();

        assertDoesNotThrow(()->{player.buy(towerWhite);}, "Insuficient Credits");
        assertDoesNotThrow(()->{player.buy(towerSilver);}, "Insuficient Credits");

     }

     //Verificar solo se pueda construir defensas sobre tierra (y verificar lo contrario)

    @Test
    public void defensesCanOnlyBeBuiltOnGround(){
        TowerSilver towerSilver = new TowerSilver();


        Ground ground = new Ground(new Coordinate(0,0));
        Rocky rocky = new Rocky(new Coordinate(1,1));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(3,3));
        Gangway gangway = new Gangway(new Coordinate(2,2), finalGangway);


        assertDoesNotThrow(() ->{ground.build(towerSilver);}, "Can't build in this plot.");
        assertThrows(CannotBuild.class, () ->  {rocky.build(towerSilver);});
        assertThrows(CannotBuild.class, () ->  {gangway.build(towerSilver);});

    }

/*
    @Test
    public void defensesAttackEnemiesWithinTheExpectedRange() {
        Coordinate coord = new Coordinate(0,0);
        ArrayList<Coordinate> around =  coord.getAround(5);
        Iterator<Coordinate> coordinateIterator = around.iterator();

        while (coordinateIterator.hasNext()) {
            Coordinate uajhsu = coordinateIterator.next();
            uajhsu.showCoords();
        }

    }
*/


    @Test
    public void SilverTowerAttackEnemiesWithinTheExpectedRange(){



        TowerSilver towerSilver = new TowerSilver();


        try {
            towerSilver.buy(new Credits(10000));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        towerSilver.putIn(new Coordinate(6,1));

        try {
            towerSilver.build();
            towerSilver.build();

        } catch (CannotConstruction e) {
            throw new RuntimeException(e);
        }

        GameMap.getMap().spawnEnemies();
        try {
            towerSilver.attack();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertDoesNotThrow(() -> {towerSilver.attack();} , "There's no enemies arround");

        towerSilver.putIn(new Coordinate(7,1));
        assertThrows(EnemyNotFound.class, () -> {towerSilver.attack();});




    }
    @Test
    public void WhiteTowerAttackEnemiesWithinTheExpectedRange(){



        TowerWhite whiteTower = new TowerWhite();
        try {
            whiteTower.buy(new Credits(10000));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        whiteTower.putIn(new Coordinate(4,1));

        try {
            whiteTower.build();
        } catch (CannotConstruction e) {
            throw new RuntimeException(e);
        }

        GameMap.getMap().spawnEnemies();
        try {
            whiteTower.attack();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertDoesNotThrow(() -> {whiteTower.attack();} , "There's no enemies arround");
        whiteTower.putIn(new Coordinate(5,1));
        assertThrows(EnemyNotFound.class, () -> {whiteTower.attack();});


    }
    @Test
    public void enemiesAreDamagedAcordingToRecievesAttack(){

        TowerWhite whiteTower = new TowerWhite();
        try {
            whiteTower.buy(new Credits(10));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        whiteTower.putIn(new Coordinate(4,1));

        try {
            whiteTower.build();
        } catch (CannotConstruction e) {
            throw new RuntimeException(e);
        }

        GameMap.getMap().spawnEnemies();
        try {
            //Mata dos hormigas
            whiteTower.attack();
            GameMap.getMap().spawnEnemies();
            whiteTower.attack();
            //Mata una arania
            whiteTower.attack();
            whiteTower.attack();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThrows(EnemyNotFound.class,()-> {whiteTower.attack();});

/*
        Credits expectedCredits = new Credits(4);

        Credits myCredits = new Credits(0);

        whiteTower.transferPickedCreditsTo(myCredits);

        assert(myCredits.sameCredits(expectedCredits));*/
    }



}


