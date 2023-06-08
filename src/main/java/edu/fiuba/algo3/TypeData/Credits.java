package edu.fiuba.algo3.TypeData;

public class Credits {
    int credits;

    public Credits(int credits) {

        this.credits = credits;
    }

    public void wasteCredits(Credits credits) {

        this.credits -= credits.credits;
        //credits.credits = 0;
    }

    public void transferCreditsTo(Credits credits) {

        credits.credits += this.credits;
        this.credits = 0;
    }

    public boolean higherCredits(Credits credits) {

        return (credits.credits < this.credits);
    }

    public boolean sameCredits(Credits otherCredits) {

        return (this.credits == otherCredits.credits);
    }

    public void GiveCreditsTo(Credits creditsToGet) {
        credits += creditsToGet.credits;
    }
}


