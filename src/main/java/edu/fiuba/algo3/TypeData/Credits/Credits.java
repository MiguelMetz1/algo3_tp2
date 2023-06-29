package edu.fiuba.algo3.TypeData.Credits;

public class Credits {
    double credits;

    public Credits(double credits) {
        this.credits = credits;
    }

    public void wasteCredits(Credits credits) {

        this.credits -= credits.credits;
    }

    public void transferCreditsTo(Credits credits) {

        credits.credits += this.credits;
        this.credits = 0;
    }

    public boolean higherCreditsThan(Credits otherCredits) {

        return (this.credits > otherCredits.credits);
    }



    public boolean lowerCreditsThan(Credits otherCredits) {

        return (this.credits < otherCredits.credits);
    }

    public String toString(){
        return Double.toString(credits);
    }

}


