package Builders;

public class BuilderTowerSilver implements Builder {

    private int timeConstruction;

    public BuilderTowerSilver (int timeConstruction) {
        this.timeConstruction = timeConstruction;
    }
    @Override
    public boolean finished() {
        return this.timeConstruction == 0;
    }

    @Override
    public void progress() {
        this.timeConstruction -= 1;
    }
}
