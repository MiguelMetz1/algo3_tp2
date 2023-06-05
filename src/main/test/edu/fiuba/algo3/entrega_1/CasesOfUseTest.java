package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.Defenses.TowerSilver;
import edu.fiuba.algo3.Defenses.TowerWhite;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.Plots.FinalGangway;
import edu.fiuba.algo3.Plots.Ground;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.Enemies.Spider;
import edu.fiuba.algo3.Exceptions.CannotAttack;
import edu.fiuba.algo3.Exceptions.CannotConstruction;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.Plots.Gangway;
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


        //assertEquals("Hello world!", message.greet("us"));
    }

    /*  Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
        “operativas” cuando ya se terminaron de construir.*/

    @Test
    public void whiteTowerConstructionTimeIsCorrect(){
        TowerWhite towerWhite = new TowerWhite();

        //Intentar construir y que no tire error

        assertThrows(CannotAttack.class,()->{towerWhite.attack();});
        assertDoesNotThrow(()->{towerWhite.build();},"The defense completed its construction");

        assertDoesNotThrow(()->{towerWhite.attack();},"The defense isn't available to attack");
        assertThrows(CannotConstruction.class, ()->{towerWhite.build();});


    }

    @Test
    public void silverTowerConstructionTimeIsCorrect(){
        TowerSilver towerSilver= new TowerSilver();

        //Intentar construir y que no tire error

        assertThrows(CannotAttack.class,()->{towerSilver.attack();});
        assertDoesNotThrow(()->{towerSilver.build();},"The defense completed its construction");

        assertThrows(CannotAttack.class,()->{towerSilver.attack();});
        assertDoesNotThrow(()->{towerSilver.build();},"The defense completed its construction");

        assertDoesNotThrow(()->{towerSilver.attack();},"The defense isn't available to attack");
        assertThrows(CannotConstruction.class, ()->{towerSilver.build();});



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
    public void defensesAttackEnemiesWithinTheExpectedRange(){



        /* GameMap gameMap = GameMap.getMap();

        gameMap.printMap(); */


    //}


}


