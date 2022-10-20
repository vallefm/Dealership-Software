package Controller;
import Controller.Commands;
import Models.Dealer;
import Models.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import view.dealership_software.GUI;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    Commands cmds = new Commands();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ActionEvent tempEvent;


    //ListView is Car List, and Observable list is the input required to populate the listView
    @FXML
    private ListView carList;
    @FXML
    private ListView dealerList;
    @FXML
    private ObservableList<Object> carList1 = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Object> dealerList1 = FXCollections.observableArrayList();

    //These are addCar textFields
    @FXML
    private TextField addCarMake;
    @FXML
    private TextField addCarModel;
    @FXML
    private TextField addCarDID;
    @FXML
    private TextField addCarID;
    @FXML
    private TextField addCarType;
    @FXML
    private TextField addCarPrice;
    @FXML
    private Button addCarButton;

    //////////////////////////////////////////////////////////////
    //transferGUI
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
/////////////////////////////////////////////////////////////////////////////////////////

    private String carMake;
    private String carModel;
    private String carDID;
    private String carID;
    private String carType;
    private String carPrice;




    public void readJson(ActionEvent event) throws IOException, ParserConfigurationException, SAXException {
        cmds.readJSON();
//        Parent root = FXMLLoader.load(getClass().getResource("mainMenuGUI.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenuGUI.fxml"));
//        loader.setController(this);
//        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    public void switchToCreateDealerGUI(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("createDealerGUI.fxml"));
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("createDealerGUI.fxml"));
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDealerGUI(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("DealerGUI.fxml"));
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("DealerGUI.fxml"));
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMainMenuGUI(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("mainMenuGUI.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenuGUI.fxml"));
//        loader.setController(this);
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("mainMenuGUI.fxml"));
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //switch to transfer window
    public void switchToTransferGUI(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("transferGUI.fxml"));

        FXMLLoader root = new FXMLLoader(GUI.class.getResource("transferGUI.fxml"));
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();

    }

    public void transferCar(ActionEvent event){
        String from = transfer_From_DealerID.getText();
        String carID = transfer_CarID.getText();
        String to = transfer_To_DealerID.getText();
        int outcome = cmds.transferCar(from, carID, to);

        //error / success message
        if(outcome == 0){
            transfer_Success.setVisible(true);
            transfer_From_InvalidDealerID.setVisible(false);
            transfer_InvalidCarID.setVisible(false);
            transfer_To_InvalidDealerID.setVisible(false);
        }
        if(outcome == 1){
            transfer_Success.setVisible(false);
            transfer_From_InvalidDealerID.setVisible(true);
            transfer_InvalidCarID.setVisible(false);
            transfer_To_InvalidDealerID.setVisible(false);
        }
        if(outcome == 2){
            transfer_Success.setVisible(false);
            transfer_From_InvalidDealerID.setVisible(false);
            transfer_InvalidCarID.setVisible(true);
            transfer_To_InvalidDealerID.setVisible(false);
        }
        if(outcome == 3){
            transfer_Success.setVisible(false);
            transfer_From_InvalidDealerID.setVisible(false);
            transfer_InvalidCarID.setVisible(false);
            transfer_To_InvalidDealerID.setVisible(true);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Loan out car window
    public void switchToLoanGUI(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("loanCarGUI.fxml"));
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("loanCarGUI.fxml"));
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void loadList(ActionEvent event) throws IOException {
        for (Dealer d : cmds.listOfDealers) {
            for(Vehicle i: d.getListOfCarsAtDealer()){
                //This boolean serves to make sure that we do not add duplicates to list
                boolean b = carList1.contains("Dealer ID: " + i.getDealership_id() + " | Car ID: " + i.getVehicle_id() + " | Car Price: " + i.getPrice() + " | Car Acquisition Date: " + Instant.ofEpochMilli(i.getAcquisition_date()) + " | vehicle type: " + i.getVehicle_type() + " | vehicle manufacturer: " + i.getVehicle_manufacturer() + " | vehicle model: " + i.getVehicle_model());
                if (b == false) {
                    carList1.add("Dealer ID: " + i.getDealership_id() + " | Car ID: " + i.getVehicle_id() + " | Car Price: " + i.getPrice() + " | Car Acquisition Date: " + Instant.ofEpochMilli(i.getAcquisition_date()) + " | vehicle type: " + i.getVehicle_type() + " | vehicle manufacturer: " + i.getVehicle_manufacturer() + " | vehicle model: " + i.getVehicle_model());
                }
            }
        }

        
        carList.setItems(carList1);

        }

    public void loadDealerList(ActionEvent event) throws IOException {
        for (Dealer d : cmds.listOfDealers) {
            boolean b = dealerList1.contains("Dealer ID: " + d.getDealer_id());
            if (b == false){
                //This boolean serves to make sure that we do not add duplicates to list
                dealerList1.add("Dealer ID: " + d.getDealer_id());
            }

            dealerList.setItems(dealerList1);

        }
    }

    private void refreshList(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("mainMenuGUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void addCar(ActionEvent event) {
        carMake = addCarMake.getText();
        carModel = addCarModel.getText();
        carDID = addCarDID.getText();
        carID = addCarID.getText();
        carType = addCarType.getText();
        carPrice = addCarPrice.getText();
        cmds.addCarGUI(carMake, carModel, carDID, carID, carType, carPrice);
    }

    public void dealerOn(ActionEvent event) {
        String dealerID = DealerIDField.getText();
        cmds.dealerOn(dealerID);
    }
    public void dealerOff(ActionEvent event) {
        String dealerID = DealerIDField.getText();
        cmds.dealerOff(dealerID);
    }


    //exit program function
    public void exitProgram() {
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}