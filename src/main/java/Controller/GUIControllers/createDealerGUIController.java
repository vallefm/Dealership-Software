package Controller.GUIControllers;

import Controller.CommandManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.dealership_software.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class createDealerGUIController implements Initializable {

    @FXML
    private TextField dealerID;
    @FXML
    private TextField dealerName;
    @FXML
    private Label invalid_DealerID;
    @FXML
    private Label success;

    CommandManager cmds = new CommandManager();

    public void create(ActionEvent event){
        String dID = dealerID.getText();
        String dName = dealerName.getText();

        boolean bool_Invalid_DealerID = cmds.createDealer(dID, dName);

        //hide error / success message
        invalid_DealerID.setVisible(false);
        success.setVisible(false);

        if(bool_Invalid_DealerID){
            invalid_DealerID.setVisible(true);
        } else{
            success.setVisible(true);
        }

    }
    public void switchToDealerGUI(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("DealerGUI.fxml"));
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("DealerGUI.fxml"));
        Stage stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainMenuGUI(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("mainMenuGUI.fxml"));
        Stage stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }

    public void exitProgram() {
        cmds.saveSerializedData();
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invalid_DealerID.setVisible(false);
        success.setVisible(false);

    }
}
