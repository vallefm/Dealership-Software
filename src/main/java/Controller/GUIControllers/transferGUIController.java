package Controller.GUIControllers;

import Controller.Commands;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.dealership_software.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class transferGUIController implements Initializable {

    @FXML
    private Label transfer_From_InvalidDealerID;
    @FXML
    private Label transfer_To_InvalidDealerID;
    @FXML
    private Label transfer_InvalidCarID;
    @FXML
    private Label transfer_Success;
    @FXML
    private TextField transfer_From_DealerID;
    @FXML
    private TextField transfer_To_DealerID;
    @FXML
    private TextField transfer_CarID;
    @FXML
    private Button transfer;
    @FXML
    private TextField DealerIDField;

    Commands cmds = new Commands();

    public void transferCar(ActionEvent event) {
        String from = transfer_From_DealerID.getText();
        String carID = transfer_CarID.getText();
        String to = transfer_To_DealerID.getText();
        boolean[] outcome = cmds.transferCar(from, carID, to);

        //error / success message

        //hide all error / success messages
        transfer_Success.setVisible(false);
        transfer_From_InvalidDealerID.setVisible(false);
        transfer_InvalidCarID.setVisible(false);
        transfer_To_InvalidDealerID.setVisible(false);

        //display success
        if (outcome[0]) {
            transfer_Success.setVisible(true);
        }

        //display invalid from dealer id
        if (outcome[1]) {
            transfer_From_InvalidDealerID.setVisible(true);
        }

        //display
        if (outcome[2]) {
            transfer_InvalidCarID.setVisible(true);
        }

        //display invalid to dealer id
        if (outcome[3]) {
            transfer_To_InvalidDealerID.setVisible(true);
        }
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
