package Controller.Commands;

import Models.Company;

import java.io.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class SaveSerializedData {

    @FXML
    private RadioButton loanRadioButton;
    @FXML
    private TextField carIDTextField;
    @FXML
    private Label invalidCarID;

    public void saveSerializedData(){

       // get current directory 
       String serializedDataFilePath = System.getProperty("user.dir") + "\\company-serialized-data.ser";
       
        try {

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
   }
}
