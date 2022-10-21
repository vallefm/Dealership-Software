package Controller.GUIControllers;

import Controller.Commands;
import Models.Company;
import Models.Dealer;
import Models.Vehicle;
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

    private Vehicle currentVehicle;
    private Commands cmds = new Commands();

    public void search(ActionEvent event) throws IOException{

        invalidCarID.setVisible(false);

        String carID = carIDTextField.getText();
        currentVehicle = null;
        for(Dealer d : Company.getCompany()){
            for(Vehicle v : d.getListOfCarsAtDealer()){
                if(v.getVehicle_id().equals(carID)){
                    currentVehicle = v;
                }
            }
        }


        //if car does not exist error
        if(currentVehicle == null) {
            invalidCarID.setVisible(true);
            loanRadioButton.setVisible(false);
            return;
        }

        loanRadioButton.setVisible(true);
        if(currentVehicle.getIsLoaned()){
            loanRadioButton.setSelected(true);
        }
        else {
            loanRadioButton.setSelected(false);
        }
    }


    public void setLoanedStatus(ActionEvent event) throws IOException{
        if(currentVehicle.getIsLoaned()){
            currentVehicle.setIsLoaned(false);
        }
        else{currentVehicle.setIsLoaned(true);}
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
