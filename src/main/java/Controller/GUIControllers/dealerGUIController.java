package Controller.GUIControllers;

import Controller.Commands;
import Models.Dealer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.dealership_software.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dealerGUIController implements Initializable {

    @FXML
    private TextField DealerIDField;
    @FXML
    private ObservableList<Object> dealerList1 = FXCollections.observableArrayList();
    @FXML
    private ListView dealerList;
    private Commands cmds = new Commands();

    public void dealerOn(ActionEvent event) {
        String dealerID = DealerIDField.getText();
        cmds.dealerOn(dealerID);
    }
    public void dealerOff(ActionEvent event) {
        String dealerID = DealerIDField.getText();
        cmds.dealerOff(dealerID);
    }

    public void refreshList(ActionEvent event) throws IOException {
        for (Dealer d : cmds.listOfDealers) {
            boolean b = dealerList1.contains("Dealer ID: " + d.getDealer_id());
            if (b == false){
                //This boolean serves to make sure that we do not add duplicates to list
                dealerList1.add("Dealer ID: " + d.getDealer_id());
            }

            dealerList.setItems(dealerList1);

        }
    }

    public void switchToCreateDealerGUI(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("createDealerGUI.fxml"));
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("createDealerGUI.fxml"));
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
