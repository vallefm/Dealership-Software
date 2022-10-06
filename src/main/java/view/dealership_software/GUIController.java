package view.dealership_software;
import Controller.Commands;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;

public class GUIController {
    @FXML
    private Label welcomeText;

    Commands cmds = new Commands();

    @FXML
    public Button addCarButton = new Button("addCarID");


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    //exit program function
    public void exitProgram() {
        System.exit(0);
    }

    //read JSON function
    public void readJSON() throws FileNotFoundException {
        cmds.readJSON();
        //set addCarButton to visible
        addCarButton.setVisible(true);
    }

}