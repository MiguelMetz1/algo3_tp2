package edu.fiuba.algo3.entrega_1;


import Defenses.TowerSilver;
import Defenses.TowerWhite;
import Enemies.Spider;
import Exceptions.CannotAttack;
import Exceptions.CannotConstruction;
import Exceptions.InsuficientCredits;
import Players.Player;
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

* */import static org.junit.jupiter.api.Assertions.*;

public class CasesOfUseTest {

    @Test
    public void playerStartsWithCorrespondingCredits() {
        //Vida:20     Creditos:100
        Player player = new Player();

        TowerSilver towerSilver = new TowerSilver();

        for (int i = 0; i < 5; i++) {
            assertDoesNotThrow(() -> {
                player.buy(towerSilver);
            }, "Insuficient Credits");
        }

        assertThrows(InsuficientCredits.class, () -> {
            player.buy(towerSilver);
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

        Ground
    }

}
