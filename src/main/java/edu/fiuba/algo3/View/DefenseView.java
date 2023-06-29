package edu.fiuba.algo3.View;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Time;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class DefenseView extends Paintable{

    Game game;

    public DefenseView(Game game, AnchorPane grid, Coordinate coordinate) {
        super(grid, coordinate);
        this.game = game;
    }

    private void addButtonHandler() {

        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/redCircle.png");
        ImageView rangeCircleView = new ImageView(image);

        rangeCircleView.setOpacity(0.3);
        double range = range();
        rangeCircleView.setLayoutX( (positions.get(0) - range) * 45 );
        rangeCircleView.setLayoutY( (positions.get(1) - range) * 45 );
        rangeCircleView.setFitHeight( (2*range + 1) * 45 );
        rangeCircleView.setFitWidth((2*range + 1)*45);

        towerImageView.setOnMouseEntered(e->{
            grid.getChildren().add(rangeCircleView);
            towerImageView.toFront();
        });

        towerImageView.setOnMouseExited(e->{
            this.grid.getChildren().remove(rangeCircleView);
        });

    }

    public void paint(){
        super.paint();
        this.addButtonHandler();
        this.updateState();
    }

    protected void updateState(){
        Coordinate coordinate = new Coordinate(this.positions.get(0), this.positions.get(1));
        Time timeOfConstruction = new Time(0);
        this.game.remainingTime(coordinate, timeOfConstruction);
        if( timeOfConstruction.higher(new Time(0)) ){
            this.towerImageView.setOpacity(0.5);
        }
        if( timeOfConstruction.equals(new Time(0)) ){
            makeSound();
        }
    }



    protected String entitySound(){
        return "src/main/java/edu/fiuba/algo3/View/Sounds/towerConstructed.wav";
    }

    protected  abstract double range();

}
