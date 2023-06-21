package edu.fiuba.algo3.View;


import edu.fiuba.algo3.View.Events.ExitButtonEventHandler;
import edu.fiuba.algo3.View.Events.FullScreenButtonEventHandler;
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
        MenuItem openOption = new MenuItem("Open");
        MenuItem aboutOption = new MenuItem("About...");

        ExitButtonEventHandler exitButtonEventHandler = new ExitButtonEventHandler();
        exitOption.setOnAction(exitButtonEventHandler);

        FullScreenButtonEventHandler fullScreenButtonEventHandler = new FullScreenButtonEventHandler(stage, this.fullScreenOption);
        this.fullScreenOption.setOnAction(fullScreenButtonEventHandler);


        this.fullScreenOption.setDisable(false);

        fileMenu.getItems().addAll(exitOption, new SeparatorMenuItem(),openOption);
        helpMenu.getItems().addAll(aboutOption);
        viewMenu.getItems().addAll(this.fullScreenOption);

        this.getMenus().addAll(fileMenu,helpMenu,viewMenu);
    }


}
