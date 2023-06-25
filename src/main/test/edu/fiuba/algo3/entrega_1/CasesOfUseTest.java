package edu.fiuba.algo3.entrega_1;


import Defenses.TowerSilver;
import Defenses.TowerWhite;
import Exceptions.CannotBuild;
import GameMap.GameMap;
import Plots.FinalGangway;
import Plots.Ground;
import TypeData.Coordinate;
import edu.fiuba.algo3.Enemies.Spider;
import Exceptions.CannotAttack;
import Exceptions.CannotConstruction;
import Exceptions.InsuficientCredits;
import Players.Player;
import Plots.Rocky;
import Plots.Gangway;
import org.json.JSONArray;
import org.json.JSONObject;
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

* */import java.io.*;
import java.sql.Array;
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


    @Test
    public void defensesAttackEnemiesWithinTheExpectedRange(){


        String string = "";

        try {
            String stringActual = "";

            FileReader fileReader = new FileReader("src/mapa.json");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(stringActual != null){
                string += stringActual;
                stringActual = bufferedReader.readLine();
                System.out.println((stringActual));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject(string) ;

        JSONObject gameMap = jsonObject.getJSONObject("Mapa");


        Iterator<String> keys = gameMap.keys();

        while(keys.hasNext()) {
            JSONArray jsonArray = gameMap.getJSONArray(keys.next());
            Iterator<Object> it = jsonArray.iterator();
            while (it.hasNext()) {
                System.out.print(" - " + it.next());
            }
            System.out.println();

        }


        /* GameMap gameMap = GameMap.getMap();

        gameMap.printMap(); */


    }


}


