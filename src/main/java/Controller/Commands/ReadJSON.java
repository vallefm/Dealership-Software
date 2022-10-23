package Controller.Commands;

import Controller.Converters;
import Models.Company;
import Models.Dealer;
import Models.Vehicle;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadJSON {

    public void readJSON() throws IOException, ParserConfigurationException, SAXException {
        JButton open = new JButton();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/Users"));
        // Titles the text box
        fileChooser.setDialogTitle("JSON file to Converter");
        if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {

        }
        // Gets the absolute path of the file
        String fileAbsolutePath = fileChooser.getSelectedFile().getAbsolutePath();

        List<Dealer> listOfDealers;
        List<Vehicle> listOfCars = new ArrayList<>();


        //list of dealers contains all cars read from json file
        Converters c = new Converters();
        if(fileAbsolutePath.contains(".xml")){
            File file = new File(fileAbsolutePath);
            listOfDealers = c.fromXmlToArr(file);
        }
        else{
            FileReader file = new FileReader(fileAbsolutePath);
            listOfDealers = c.fromJsonToInvArr(file);
        }


        //////////////////////////////
        //add new dealers to company//
        //////////////////////////////

        /////////////////////////////////////////////////////////////////////////////////////////
        //go threw all cars in the listOfDealers and all cars in Company and if there are matches,
        // delete the car that is in the listOfDealers

        //check all incoming cars to see if they are in any of the company dealers
        for(Dealer newDealer : listOfDealers){
            for(int i = 0; i < newDealer.getListOfCarsAtDealer().size(); i++){
                Vehicle newVehicle = newDealer.getListOfCarsAtDealer().get(i);
                for(Dealer companyDealer : Company.getCompany()){
                    for(Vehicle companyVehicle : companyDealer.getListOfCarsAtDealer()){
                        if(newVehicle.getVehicle_id().equals(companyVehicle.getVehicle_id())){
                            newDealer.getListOfCarsAtDealer().remove(newVehicle);
                            i=-1;
                            break;
                        }
                    }
                }
            }
        }

        //get dealerIDs for all dealers in Company
        List<String> companyDealerIDs = new ArrayList<>();
        for(Dealer d : Company.getCompany()){
            companyDealerIDs.add(d.getDealer_id());
        }

        //if a Dealer id from listOfDealers does not exist in companyDealerIDs, add the new dealer to company
        List<Dealer> deleteFromListOfDealers = new ArrayList<>();
        for(Dealer d : listOfDealers){
            if(!companyDealerIDs.contains(d.getDealer_id())){
                Company.getCompany().add(d);
                deleteFromListOfDealers.add(d);
            }

        }

        //delete the dealers that have been added to Company
        for(Dealer d : deleteFromListOfDealers){
            listOfDealers.remove(d);
        }

        //merge remaining new dealers with company dealers
        for(Dealer newDealer : listOfDealers){
            for(Dealer companyDealer : Company.getCompany()){

                //check if company dealer has a name, if not and the new dealer does,
                //added the name of the new dealer to the company dealer
                if(newDealer.getDealer_id().equals(companyDealer.getDealer_id())){
                    if(companyDealer.getName().equals("") && !newDealer.getName().equals("")){
                        companyDealer.setName(newDealer.getName());
                    }


                    for(Vehicle v : newDealer.getListOfCarsAtDealer()){
                        companyDealer.getListOfCarsAtDealer().add(v);
                    }
                }
            }
    }

//
//        // if listOfDealers is empty, add the dealer of the first car in listOfCars to
//        // listOfDealers
//        if (listOfDealers.size() == 0 && listOfCars.size() > 0) {
//            listOfDealers.add(new Dealer(listOfCars.get(0).getDealership_id(), true));
//        }
//        // put cars from json file into dealers
//        // create new dealer if car's dealership_id does not match any existing dealers
//        for (Vehicle car : listOfCars) {
//            for (int i = 0; i < listOfDealers.size(); i++) {
//                Dealer dealer = listOfDealers.get(i);
//                if (car.getDealership_id().equals(dealer.getDealer_id())) {
//                    dealer.addToListOfCarsAtDealer(car);
//                    break;
//                }
//                if (dealer == listOfDealers.get(listOfDealers.size() - 1)) {
//                    listOfDealers.add(new Dealer(car.getDealership_id(), true));
//                }
//
//            }
//        }
//
    }
}
