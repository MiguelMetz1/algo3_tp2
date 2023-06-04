package TypeData;

public class Credits {
    int credits;
    public Credits(int credits) {
        this.credits = credits;
    }

    public void wasteCredits(Credits credits) {
        this.credits -= credits.credits;
        //Si la torre ya se constryo, si se quiere volver a construir,
        // no va a gastar creditos porque ya esta construida
        credits.credits = 0;
    }

    public boolean lowerCredits(Credits credits) {
        return (credits.credits < this.credits);
    }
}
