package Controller.Commands;

import Controller.Converters;
import Models.Company;
import Models.Dealer;
import Models.Vehicle;

import java.io.*;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class SaveAndExit {
    @FXML
    private RadioButton loanRadioButton;
    @FXML
    private TextField carIDTextField;
    @FXML
    private Label invalidCarID;

    public void saveAndExit(){

       // get current directory 
       String serializedDataFilePath = System.getProperty("user.dir") + "\\company-serialized-data.ser";

        try {

        //get a list of every vehicle so we can store the vehicles into a serialized list later on.
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
//        for(Dealer d: Company.getCompany()){
//            for(Vehicle v : d.getListOfCarsAtDealer()){
//                vehicleList.add(v);
//            }
//        }

        //create an ouput file named company-serialized-data.ser (serialized extension)
        FileOutputStream fileOut = new FileOutputStream(serializedDataFilePath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        //we proceed to serialize the list of vehicles to the specified filepath.
        out.writeObject(Company.getCompany());
        out.close();
        fileOut.close();
     } catch (IOException i) {
        i.printStackTrace();
        System.out.println("Failed to serialize specified data");
     } 

     Converters.deserializeData(serializedDataFilePath);
    }
}
