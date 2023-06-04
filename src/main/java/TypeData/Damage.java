package TypeData;

public class Damage {
    int damage;

    public Damage(int damage){
        this.damage = damage;
    }

    public void applyDamage(Life life) {
        life.reduceLife(this.damage);
    }
}
