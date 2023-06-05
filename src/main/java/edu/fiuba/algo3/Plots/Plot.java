package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.Exceptions.EnemyNotFound;
import edu.fiuba.algo3.Exceptions.UnespawnablePlace;
import edu.fiuba.algo3.TypeData.Coordinate;

public abstract class Plot {
    Coordinate coordinate;
    Buildable buildable;


    public Plot(Coordinate coordinate, Buildable buildable) {
        this.coordinate = coordinate;
        this.buildable = buildable;
    }

    public void build(Defense defense) throws CannotBuild {
        this.buildable.build(defense);
    }

    public boolean canBuild() {
        return this.buildable.canBuild();
    }

    public boolean hasEnemies(){
        return false;
    }

    public Enemy returnEnemy() throws EnemyNotFound {
        throw new EnemyNotFound("No enemies in this plot.");
    }

    public Boolean canSpawnEnemies(){
        return false;
    }

    public void spawnEnemies() throws UnespawnablePlace {
        throw new UnespawnablePlace("Enemies cant be spawned in this place.");
    }

    public abstract String showPlotName();
}