package Controller.GUIControllers;

import Controller.CommandManager;

import Models.Company;
import Models.Dealer;
import Models.Vehicle;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import view.dealership_software.GUI;

public class dealerGUIController implements Initializable {

    @FXML
    private TextField dealerIDField;
    @FXML
    private Label label_Invalid_DealerID;
    @FXML
    private Label label_Success;
    @FXML
    private ObservableList<Object> dealerList1;
    @FXML
    private ListView<Object> dealerList;

    private CommandManager cmds = new CommandManager();


    public void dealerOn(ActionEvent event) {

        hideMessages();

        String dealerID = dealerIDField.getText();
        cmds.dealerOn(dealerID);

        loadDealerInventory();
    }

    public void dealerOff(ActionEvent event) {

        hideMessages();

        String dealerID = dealerIDField.getText();
        cmds.dealerOff(dealerID);

        loadDealerInventory();
    }

    public void exportToJSON(ActionEvent event){

        String dealerID = dealerIDField.getText();

        boolean invalid_DealerID;
        invalid_DealerID = cmds.exportFromDealerToJSON(dealerID);

        //Error / Success message
        hideMessages();

        if(invalid_DealerID){

            label_Invalid_DealerID.setVisible(true);
            loadDealerList();
        }else{

            loadDealerInventory();
            label_Success.setVisible(true);
        }
    }

    public void hideMessages(){

        label_Invalid_DealerID.setVisible(false);
        label_Success.setVisible(false);
    }

    public void showDealerInventory(ActionEvent event){

        hideMessages();
        loadDealerInventory();
    }


    public void refreshDealerList(ActionEvent event) throws IOException {

        hideMessages();
        loadDealerList();
    }


    private void loadDealerInventory(){

        //clear current display
        dealerList1 = FXCollections.observableArrayList();

        String dealerID = dealerIDField.getText();

        Dealer currDealer = null;

        //find dealer
        for(Dealer d : Company.getCompany()){

            if(dealerID.equals(d.getDealer_id())){

                currDealer = d;
            }
        }

        //show error label if dealer not found
        if(currDealer == null){

            label_Invalid_DealerID.setVisible(true);
            loadDealerList();
        }else {

            label_Invalid_DealerID.setVisible(false);

            //display
            dealerList1.add("Dealer ID: " + currDealer.getDealer_id() + " | Name: " + currDealer.getName() + " | Activation Status: " + currDealer.getIsActivatedStatus());
            dealerList1.add("======================================================");

            for (Vehicle v : currDealer.getListOfCarsAtDealer()) {

                dealerList1.add("Dealer ID: " + v.getDealership_id() + " | Car ID: " + v.getVehicle_id() + " | Car Price: " + v.getCurrencyType() + v.getPrice() + " | Car Acquisition Date: " + Instant.ofEpochMilli(v.getAcquisition_date()) + " | vehicle type: " + v.getVehicle_type() + " | vehicle manufacturer: " + v.getVehicle_manufacturer() + " | vehicle model: " + v.getVehicle_model() + " | loan status: " + v.getIsLoaned());
            }
        }

        dealerList.setItems(dealerList1);
    }

    private void loadDealerList(){
        
        dealerList1 = FXCollections.observableArrayList();

        for (Dealer d : Company.getCompany()) {
            dealerList1.add("Dealer ID: " + d.getDealer_id() + " | Name: " + d.getName() + " | Activation Status: " + d.getIsActivatedStatus());
        }

        dealerList.setItems(dealerList1);
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

        cmds.saveSerializedData();
        System.exit(0);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void initialize(URL location, ResourceBundle resources) {

        hideMessages();

        loadDealerList();
        
        dealerList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String dealerId = "Dealer ID: ";
                if(newValue != null && newValue.toString().contains(dealerId)){
                    int start = newValue.toString().indexOf(dealerId)+dealerId.length();

                    int end = newValue.toString().indexOf("|");
                    String dId = newValue.toString().substring(start,end).trim();
                    dealerIDField.setText(dId);
                }

            }
        });
    }
}
