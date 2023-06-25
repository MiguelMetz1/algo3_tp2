package Builders;

public class BuilderTowerWhite implements Builder{

    int timeConstruction;

    public BuilderTowerWhite (int timeConstruction) {
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
