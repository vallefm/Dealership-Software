package Controller.GUIControllers;

import Controller.CommandManager;
import Controller.Converters;
import Models.Company;
import Models.Dealer;
import Models.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import view.dealership_software.GUI;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class mainMenuGUIController implements Initializable {

    //Add car text fields
    @FXML
    private TextField addCarMake;
    @FXML
    private TextField addCarModel;
    @FXML
    private TextField addCarDID;
    @FXML
    private TextField addCarID;
    @FXML
    private TextField addCarPrice;

    //add car error / success labels
    @FXML
    private Label invalid_DealerID;
    @FXML
    private Label invalid_CarID;
    @FXML
    private Label success;

    @FXML
    private ChoiceBox<String> addCarTypeChoiceBox;
    @FXML
    private ChoiceBox<String> currencyChoiceBox;
   //list
    @FXML
    private ObservableList<Object> carList1;
    @FXML
    private ListView carList;

    private CommandManager cmds = new CommandManager();

    public void readJson(ActionEvent event) throws IOException, ParserConfigurationException, SAXException {
        cmds.readJSON();
        loadCarList();

    }

    public void addCar(ActionEvent event) {
        String  carMake = addCarMake.getText();
        String carModel = addCarModel.getText();
        String carDID = addCarDID.getText();
        String carID = addCarID.getText();
        String carType = addCarTypeChoiceBox.getValue();
        String carPrice = addCarPrice.getText();
        String currencyType = currencyChoiceBox.getValue();

        boolean[] outcome = cmds.addCarGUI(carMake, carModel, carDID, carID, carType, carPrice, currencyType);

        loadCarList();

        //set error / success label to not visible
        invalid_CarID.setVisible(false);
        invalid_DealerID.setVisible(false);
        success.setVisible(false);

        //dealerID does not exist
        if(outcome[0]){
            invalid_DealerID.setText("Invalid");
            invalid_DealerID.setVisible(true);
        }
        //dealer is closed
        if(outcome[1]){
            invalid_DealerID.setText("Dealer Closed");
            invalid_DealerID.setVisible((true));
        }
        //non unique carID
        if(outcome[2]){invalid_CarID.setVisible(true);}

        //no errors -> success
        if(outcome[3]){
            success.setVisible(true);
        }

        loadCarList();
    }

    public void refresh(ActionEvent event) throws IOException {
        loadCarList();
    }


    private void loadCarList(){
        if(carList1 == null){
            carList1 = FXCollections.observableArrayList();
            carList.setItems(carList1);
        }
        carList1.clear();
        for (Dealer d : Company.getCompany()) {
            for(Vehicle i: d.getListOfCarsAtDealer()){
                //the time bit. I'm taking the
                //year only from it
                // so I split it at T and take the first half of the split which will always be
                // YEAR-MONTH-DAY
                carList1.add("Dealer ID: " + i.getDealership_id() + " | Car ID: " + i.getVehicle_id() + " | Car Price: " + i.getCurrencyType() + i.getPrice() + " | Car Acquisition Date: " + (Instant.ofEpochMilli(i.getAcquisition_date()).toString()).split("T")[0] + " | vehicle type: " + i.getVehicle_type() + " | vehicle manufacturer: " + i.getVehicle_manufacturer() + " | vehicle model: " + i.getVehicle_model() + " | loan status: " + i.getIsLoaned());
            }
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

    public void switchToTransferGUI(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("transferGUI.fxml"));
        Stage stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();

    }

    public void switchToLoanGUI(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(GUI.class.getResource("loanCarGUI.fxml"));
        Stage stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.show();
    }


    public void saveAndExit(ActionEvent event) {
        cmds.saveAndExit();
        System.exit(0);
    }

    //Clears the textbox and any error messages
    public void resetTextBoxAndErrors() {

        //Clears the error messages
        invalid_CarID.setVisible(false);
        invalid_DealerID.setVisible(false);
        success.setVisible(false);

        //Clears the textbox fields
        addCarMake.clear();
        addCarModel.clear();
        addCarDID.clear();
        addCarID.clear();
        addCarPrice.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //List of allowed cars
        ArrayList<String> allowedCarType = new ArrayList<>();
        allowedCarType.add("SUV");
        allowedCarType.add("Sedan");
        allowedCarType.add("Pick up");
        allowedCarType.add("Sports car");


        //Populate the carTypeChoiceBox with allowed vehicle type
        addCarTypeChoiceBox.getItems().addAll(allowedCarType);
        currencyChoiceBox.getItems().addAll("Dollars","Pounds");

        loadCarList();

    }
}
