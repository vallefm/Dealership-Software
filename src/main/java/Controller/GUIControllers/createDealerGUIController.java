package Controller.GUIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.dealership_software.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class createDealerGUIController implements Initializable {


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
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
