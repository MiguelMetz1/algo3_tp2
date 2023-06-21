package edu.fiuba.algo3.Players;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Exceptions.WrongPlayerName;
import edu.fiuba.algo3.Shop.Buyer;
import edu.fiuba.algo3.TypeData.*;

public class Player implements Looter, Buyer {
    private Credits credits;
    private String name;

    public Player(String name) {
        if( !this.rightName(name)){
            throw new WrongPlayerName("The player needs as less a six characters name.");
        }
        this.credits = new Credits(playerCredits());
        this.name = name;
    }

    private boolean rightName( String name ){
        return (name.length() >= 6);
    }

    public void transferCredits(Credits credits) {
        credits.transferCreditsTo(this.credits);
    }

    public void wasteCredits(Credits amountToWaste) throws InsuficientCredits {
        if( amountToWaste.higherCreditsThan(this.credits)){
            throw new InsuficientCredits("The player has not got sufficient credits.");
        }
        this.credits.wasteCredits(amountToWaste);
    }

    @Override
    public void addDefense(Defense defense) {

    }

    private int playerCredits(){
        return 100;
    }
    public String toString(){
        return "Player";
    }
}
