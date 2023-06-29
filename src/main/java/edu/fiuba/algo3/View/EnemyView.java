package edu.fiuba.algo3.View;

import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.layout.AnchorPane;

public abstract class EnemyView extends Paintable{

    public EnemyView(AnchorPane grid, Coordinate coordinate) {
        super(grid, coordinate);
    }

    public void paint(){
        super.paint();
        this.makeEnemyYell();
    }

    private void makeEnemyYell() {
        Coordinate coordinate = new Coordinate(this.positions.get(0), this.positions.get(1));
        if( coordinate.equals( new ExternalResources().getPlayerCharacterCoordinate() ) ){
            this.makeSound();
        }
    }

    protected abstract String entitySound();
}
