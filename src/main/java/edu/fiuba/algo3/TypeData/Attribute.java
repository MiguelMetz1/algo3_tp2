package edu.fiuba.algo3.TypeData;

public interface Attribute {
    void reduceIn(int reducePoints);
    void changeInScale(int scale);
    void applyBuff( Buff buff );
    boolean typeOfBuffsEquals(String type);
    void quitBuffs();
}
