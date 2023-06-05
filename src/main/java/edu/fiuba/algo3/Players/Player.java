package edu.fiuba.algo3.Players;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.Life;

public class Player {
    Life life;
    Credits credits;
    String name;

    public Player(){
        this.life = new Life(20);
        this.credits = new Credits(100);
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
}
