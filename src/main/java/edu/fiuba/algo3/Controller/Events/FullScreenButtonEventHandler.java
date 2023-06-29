package edu.fiuba.algo3.Controller.Events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class FullScreenButtonEventHandler implements EventHandler<ActionEvent> {

    Stage stage;
    MenuItem fullScreenOption;

    public FullScreenButtonEventHandler(Stage stage, MenuItem fullScreenOption){
        this.stage = stage;
        this.fullScreenOption =  fullScreenOption;
    }

    @Override
    public void handle(ActionEvent actionEvent){
        if(!this.stage.isFullScreen()){
            this.stage.hide();
            this.stage.setFullScreen(true);
            this.fullScreenOption.setText("Exit full screen");
            this.stage.show();
        }
        else{
            this.stage.hide();
            this.stage.setFullScreen(false);
            this.fullScreenOption.setText("Full screen");
            this.stage.show();
        }

    }

    public void fullScreen(){
        this.stage.setFullScreen(true);
        this.fullScreenOption.setDisable(false);
    }
}
