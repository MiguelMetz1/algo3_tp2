package Players;

import Defenses.Defense;
import Defenses.TowerSilver;
import Exceptions.InsuficientCredits;
import TypeData.Credits;
import TypeData.Damage;
import TypeData.Life;

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
