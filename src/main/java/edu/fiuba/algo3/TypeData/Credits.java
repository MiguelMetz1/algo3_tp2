package edu.fiuba.algo3.TypeData;

public class Credits {
    int credits;

    public Credits() {
        this.credits = this.getCredits();
    }
    public Credits(int credits) {
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

    public boolean sameCredits(Credits otherCredits) {

        return (this.credits == otherCredits.credits);
    }

    public boolean lowerCreditsThan(Credits otherCredits) {

        return (this.credits < otherCredits.credits);
    }

    protected int getCredits(){ //Ver si se dejan las clases que heredan de esta y volver este metodo y está clase abstractos.
        return 0;
    }

    public void showCredits(){
        System.out.println("Remaining credits: " + this.credits);
    }

}


