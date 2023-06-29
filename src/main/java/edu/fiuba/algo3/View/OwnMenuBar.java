package edu.fiuba.algo3.View;


import edu.fiuba.algo3.View.Events.AboutButtonEventHandler;
import edu.fiuba.algo3.View.Events.ExitButtonEventHandler;
import edu.fiuba.algo3.View.Events.FullScreenButtonEventHandler;
import edu.fiuba.algo3.View.Events.RestartButtonEventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;

public class OwnMenuBar extends MenuBar {

    MenuItem fullScreenOption = new MenuItem("Full screen");
    public OwnMenuBar(Stage stage) {

        Menu fileMenu = new Menu("File");
        Menu viewMenu = new Menu("View");
        Menu helpMenu = new Menu("Help");

        MenuItem exitOption = new MenuItem("Exit");
        MenuItem restartOption = new MenuItem("Restart");
        MenuItem aboutOption = new MenuItem("About...");

        ExitButtonEventHandler exitButtonEventHandler = new ExitButtonEventHandler();
        exitOption.setOnAction(exitButtonEventHandler);

        AboutButtonEventHandler aboutButtonEventHandler = new AboutButtonEventHandler();
        aboutOption.setOnAction(aboutButtonEventHandler);

        RestartButtonEventHandler restartButtonEventHandler = new RestartButtonEventHandler(stage);
        restartOption.setOnAction(restartButtonEventHandler);

        FullScreenButtonEventHandler fullScreenButtonEventHandler = new FullScreenButtonEventHandler(stage, this.fullScreenOption);
        this.fullScreenOption.setOnAction(fullScreenButtonEventHandler);


        this.fullScreenOption.setDisable(false);

        fileMenu.getItems().addAll(exitOption, new SeparatorMenuItem(),restartOption);
        helpMenu.getItems().addAll(aboutOption);
        viewMenu.getItems().addAll(this.fullScreenOption);

        this.getMenus().addAll(fileMenu,helpMenu,viewMenu);
    }


}
