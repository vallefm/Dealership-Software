package Controller.GUIControllers;

import Controller.CommandManager;
import Controller.Commands.LoanSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.dealership_software.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loanCarGUIController implements Initializable {

    @FXML
    private RadioButton loanRadioButton;
    @FXML
    private TextField carIDTextField;
    @FXML
    private Label invalidCarID;

    private static String currVehicleID; //string in carIDTextField when search button was clicked
    private CommandManager cmds = new CommandManager();

    public void loanSearch(ActionEvent event) throws IOException{

        String carID = carIDTextField.getText();
        currVehicleID = carIDTextField.getText();

        boolean[] outcome = cmds.loanSearch(carID);
        boolean invalid_CarID = outcome[0];
        boolean carLoanedStatus = outcome[1];



        //error message
        invalidCarID.setVisible(false);
        loanRadioButton.setVisible(false);

        //if car does not exist error
        if(invalid_CarID){
            invalidCarID.setVisible(true);
        }
        else{   //if car exists
            loanRadioButton.setVisible(true);

            loanRadioButton.setSelected(carLoanedStatus);
        }

    }

    public void setLoanStatus(ActionEvent event){
        cmds.setLoanStatus(currVehicleID);
    }

    public void switchToMainMenuGUI(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("mainMenuGUI.fxml"));
        Stage stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }
    public void exitProgram() {
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
