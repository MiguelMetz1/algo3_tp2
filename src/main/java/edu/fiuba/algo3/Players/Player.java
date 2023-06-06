package edu.fiuba.algo3.Players;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.Life;

public class Player {
    private static Player instance = registerPlayer();
    Life life;
    Credits credits;
    String name;

    public Player(){
        this.life = new Life(20);
        this.credits = new Credits(100);
    }

    public void setName(String name){
        this.name = name;
    }

    public static Player getPlayer(){
        return instance;
    }

    private static Player registerPlayer(){
        return new Player();
        // TODO: 6/7/2023 Implementar nombre, validacion, etc
        // TODO: 6/7/2023 Se puede hacer publico para llamarlo una sola vez cuando se crea el juego.
    }

    public void buy(Defense defense) throws InsuficientCredits {
        defense.buy(this.credits);
    }

    public void receiveAttack(Damage damage) {
        damage.applyDamage(this.life);
    }

    public boolean isDead() {
        return life.isEmpty();
    }

    public static void resetplayer(){
        instance = registerPlayer();
    }

}
