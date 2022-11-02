package Controller.Converters;

import java.io.*;
import java.util.List;

import Models.Company;
import Models.Dealer;

public class SerDataToObjArrays {

    @SuppressWarnings("unchecked")
    public static void serDataToObjArrays(String serializedDataPath){
        List<Dealer> listOfDealers = null;
        try {

            // This block of code  of creates an object of arrayList containing the vehicle objects to be loaded into our program further down
            FileInputStream serializedDataFile = new FileInputStream(serializedDataPath);
            ObjectInputStream inputObjects = new ObjectInputStream(serializedDataFile);
            listOfDealers = (List<Dealer>)inputObjects.readObject(); 
            serializedDataFile.close();
            inputObjects.close();            
            Company.getCompany().addAll(listOfDealers);

        } catch (IOException i) {

            i.printStackTrace();
            System.out.println("fail ioexception deserialize");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.out.println("fail classnotfoundexception deserialize");
        }
    }
}