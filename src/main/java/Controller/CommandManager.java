package Controller;

import Models.Company;
import Models.Dealer;

import java.util.ArrayList;

import java.io.IOException;

import Controller.Commands.LoanSearch;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;


//The GUIControllers call this class. This class calls the command class that the GUIController needs to use


public class CommandManager {

    public void readJSON() throws IOException, ParserConfigurationException, SAXException {
        Controller.Commands.ReadJSON command = new Controller.Commands.ReadJSON();
        command.readJSON();
    }
    public void dealerOff(String dealerID){
        Controller.Commands.DealerOff command = new Controller.Commands.DealerOff();
        command.dealerOff(dealerID);
    }
    public void dealerOn(String dealerID){
        Controller.Commands.DealerOn command = new Controller.Commands.DealerOn();
        command.dealerOn(dealerID);
    }
    public boolean[] addCarGUI(String carMake, String carModel, String carDID, String carID, String carType, String carPrice){
        Controller.Commands.AddCar command = new Controller.Commands.AddCar();
        return command.addCarGUI(carMake, carModel, carDID, carID, carType, carPrice);
    }
    public boolean[] transferCar(String fromDealer, String carID, String toDealer){
        Controller.Commands.TransferCar command = new Controller.Commands.TransferCar();
        return command.transferCar(fromDealer, carID, toDealer);
    }

    public boolean[] loanSearch(String carID){
        Controller.Commands.LoanSearch command = new Controller.Commands.LoanSearch();
        return command.loanSearch(carID);
    }

    public void setLoanStatus(String carID){
        Controller.Commands.SetLoanStatus command = new Controller.Commands.SetLoanStatus();
        command.setLoanStatus(carID);
    }


    public boolean createDealer(String dID, String dName){
        Controller.Commands.CreateDealer command = new Controller.Commands.CreateDealer();
        return command.createDealer(dID, dName);
    }
    public boolean exportFromDealerToJSON(String dealerID){
        Controller.Commands.ExportDealerToJSON command = new Controller.Commands.ExportDealerToJSON();
        return command.exportDealerToJSON(dealerID);
    }
    public void saveAndExit(){
        Controller.Commands.SaveAndExit command = new Controller.Commands.SaveAndExit();
        command.saveAndExit();
    }



}
//    JFileChooser fileChooser = new JFileChooser();
//
//    JButton open = new JButton();
//    public static List<Dealer> listOfDealers = new ArrayList<>();
//    public List<Vehicle> listOfCars;
//
//    Converters c = new Converters();
//
//    String outputMessage = "";
//    String outputMessage2 = "";
//    boolean dealershipIDFound = false;
//
//    Scanner input = new Scanner(System.in);
//
//    //reads User selected json and put cars into their corresponding dealer.
//    //if a car from a dealer that is not yet created is read, the dealer is created, and
//    //the car is stored in it
//    public void readJSON() throws IOException, ParserConfigurationException, SAXException {
//
//        fileChooser.setCurrentDirectory(new java.io.File("C:/Users"));
//        // Titles the text box
//        fileChooser.setDialogTitle("JSON file to Converter");
//        if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
//
//        }
//        // Gets the absolute path of the file
//        String fileAbsolutePath = fileChooser.getSelectedFile().getAbsolutePath();
//
//        outputMessage2 = "The file you chose to read: " + fileAbsolutePath + ".\n";
//
//
//
//
//
//        //list of cars contains all cars read from json file
//        if(fileAbsolutePath.contains(".xml")){
//            File file = new File(fileAbsolutePath);
//            listOfDealers = c.fromXmlToArr(file);
//        }
//        else{
//            FileReader file = new FileReader(fileAbsolutePath);
//            listOfDealers = c.fromJsonToInvArr(file);
//        }
//
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
//    }
//
//    //NEED TO ADD RESTRICTIONS TO INPUTS NEEDS TO CHANGE FOR IMPORTING A FILE AS WELL
//    public boolean[] addCarGUI(String carMake, String carModel, String carDID, String carID, String carType, String carPrice)  {
//
//
////        for (int i = 0; i < listOfDealers.size(); i++) {
////
////            // If the vehicle acquisition status is false, dealer can't add cars
////            if (listOfDealers.get(i).getIsActivatedStatus() == true) {
////                //find dealer to add vehicle too
////                if (carDID.equals(listOfDealers.get(i).getDealer_id())) {
////                    String vehicle_type = carType;
////                    String vehicle_manufacturer = carMake;
////                    String vehicle_model = carModel;
////                    String vehicle_id = carID;
////                    int vehicle_price = Integer.parseInt(carPrice);
////                    long acquisition_date = System.currentTimeMillis();
////                    Vehicle car = new Vehicle(carDID, vehicle_type, vehicle_manufacturer, vehicle_model, vehicle_id, vehicle_price, acquisition_date);
////                    listOfDealers.get(i).addToListOfCarsAtDealer(car);
////                }
////
////            }
////            else {
////                //pop up window saying dealer is not activated
////            }
////        }
////
//        //outcome array will be returned to determine which error / success labels become visible
//        boolean invalid_dealerID = false;
//        boolean invalid_DealerClosed = false;
//        boolean invalid_carID = false;
//        boolean invalid_Type = false;
//        boolean success = false;
//        boolean[] outcome = {invalid_dealerID, invalid_DealerClosed, invalid_carID, invalid_Type, success};
//
//        //find if dealer exists
//        Dealer dealer = null;
//        for(Dealer d : listOfDealers){
//            if(d.getDealer_id().equals(carDID)){
//                dealer = d;
//            }
//        }
//        //if dealer does not exist error
//        if(dealer == null){
//            outcome[0] = true;
//        }
//        //if dealer is closed error
//        else{
//            if(!dealer.getIsActivatedStatus()){
//                outcome[1] = true;
//            }
//        }
//        //if carID is not unique error
//        for(Dealer d : listOfDealers){
//            for(Vehicle v : d.getListOfCarsAtDealer()){
//                if(v.getVehicle_id().equals(carID)){
//                    outcome[2] = true;
//                }
//            }
//        }
//        //if type is not legal error
//        ArrayList<String> allowedVehicles = new ArrayList<>();
//        allowedVehicles.add("suv");
//        allowedVehicles.add("pickup");
//        allowedVehicles.add("sports car");
//        allowedVehicles.add("sedan");
//        if(!allowedVehicles.contains(carType)){
//            outcome[3] = true;
//        }
//
//        //if there is an error return
//        for(int i = 0; i < outcome.length - 1; i++){
//            if(outcome[i] == true){
//                return outcome;
//            }
//        }
//
//        //No errors -> add new car to dealer
//        int vehicle_price = Integer.parseInt(carPrice);
//        long acquisition_date = System.currentTimeMillis();
//        Vehicle car = new Vehicle(carDID, carType, carMake, carModel, carID, vehicle_price, acquisition_date);
//        dealer.addToListOfCarsAtDealer(car);
//
//        //Set success outcome
//        outcome[4] = true;
//
//        return outcome;
//
//    }
//
//    //takes user input to create a car object and put it into the users dealer of choice
//    public void addCar() {
//        outputMessage2 = "";
//        System.out.println("Enter the dealer id of the dealership: ");
//        String dealership_id = input.nextLine();
//
//        for (int i = 0; i < listOfDealers.size(); i++) {
//
//            // If the vehicle acquisition status is false, dealer can't add cars
//            if (listOfDealers.get(i).getIsActivatedStatus() == true) {
//
//                if (dealership_id.equals(listOfDealers.get(i).getDealer_id())) {
//
//                    //User creates car
//                    System.out.println("Enter the vehicle type? ");
//                    String vehicle_type = input.nextLine();
//
//                    System.out.println("Enter the vehicle manufacturer? ");
//                    String vehicle_manufacturer = input.nextLine();
//
//                    System.out.println("Enter the vehicle model? ");
//                    String vehicle_model = input.nextLine();
//
//                    System.out.println("Enter the vehicle id? ");
//                    String vehicle_id = input.nextLine();
//
//                    System.out.println("Enter the vehicle price? ");
//                    int vehicle_price = input.nextInt();
//
//                    System.out.println("Enter the acquisition date?(please enter 13 digit number)");
//                    long acquisition_date = input.nextLong();
//
//                    Vehicle car = new Vehicle(dealership_id, vehicle_type, vehicle_manufacturer,
//                            vehicle_model, vehicle_id, vehicle_price, acquisition_date);
//
//                    //car is stored in user chosen dealer
//                    listOfDealers.get(i).addToListOfCarsAtDealer(car);
//
//                }
//
//            } else {
//                System.out.println("That dealership is not receiving cars at the moment.\n");
//
//                System.out.println("Press enter to continue");
//                input.nextLine();
//            }
//        }
//
//
//    }
//
//    //set dealer to no longer accept new incoming vehicles
//    public void dealerOff(String dealer_ID) {
//        //outputMessage2 = "";
//        //System.out.println("Enter the dealer id of the dealership: ");
//        String dealerId = dealer_ID;
//
//        for (int i = 0; i < listOfDealers.size(); i++) {
//            if (dealerId.equals(listOfDealers.get(i).getDealer_id())) {
//                listOfDealers.get(i).setIsActivatedStatus(false);
//
//                dealershipIDFound = true;
//            }
//
//        }
//
//        if(dealershipIDFound == false){
//            System.out.println("Dealership ID " + dealerId + " does not exist.\n");
//
//            //System.out.println("Press enter to continue");
//            //input.nextLine();
//        }
//
//        //resets dealershipIDFound
//        dealershipIDFound = false;
//    }
//
//    //set dealer to accept new incoming vehicles
//    //CHANGED TO TAKE STRING AS INPUT TO BE USED WITH GUI
//    public void dealerOn(String dealer_ID) {
//        //outputMessage2 = "";
//        //System.out.println("Enter the dealer id of the dealership: ");
//        //String idString = input.nextLine();
//
//        for (int i = 0; i < listOfDealers.size(); i++) {
//            if (dealer_ID.equals(listOfDealers.get(i).getDealer_id())) {
//                listOfDealers.get(i).setIsActivatedStatus(true);
//
//                dealershipIDFound = true;
//            }
//        }
//
//        if(dealershipIDFound == false){
//            System.out.println("Dealership ID " + dealer_ID + " does not exist.\n");
//
//            //System.out.println("Press enter to continue");
//            //input.nextLine();
//        }
//
//        //resets dealershipIDFound
//        dealershipIDFound = false;
//    }
//
//    //takes a user chosen dealer and sends the dealer information to a json file
//    public void exportFromDealer() {
//        outputMessage2 = "";
//        System.out.println("What is the integer ID for the dealership? ");
//        // Show dealers
//        for (Dealer dealer : listOfDealers) {
//            System.out.println(dealer.getDealer_id());
//        }
//        String input1 = input.nextLine();
//
//        //finds dealer the user chooses and calls convertToJson to convert the dealer information into a json file
//        for (int i = 0; i < listOfDealers.size(); i++) {
//
//            if (input1.equalsIgnoreCase(listOfDealers.get(i).getDealer_id())) {
//                c.convertToJson(listOfDealers.get(i));
//            }
//        }
//    }
//
//    //Create new location also known as dealer
//    public void create() {
//        outputMessage2 = "";
//        System.out.println("What is the integer ID for the new dealership? ");
//        String idResponse = input.nextLine();
//
//        Dealer dealer = new Dealer(idResponse, true);
//
//        listOfDealers.add(dealer);
//    }
//
//    //display the dealers and their activation status
//    public void showDealer() {
//        outputMessage2 = "";
//        for (int i = 0; i < listOfDealers.size(); i++) {
//            System.out.println("\nDealership ID:" + listOfDealers.get(i).getDealer_id() +
//                    " Dealership Status: " + listOfDealers.get(i).getIsActivatedStatus());
//        }
//        System.out.println("\n");
//
//        System.out.println("Press enter to continue");
//        input.nextLine();
//
//
//    }
//
//    //display all dealers and all cars at each dealer
//    public List<Dealer> showAll() {
//        outputMessage2 = "";
//        //loops though list of dealers, prints the dealer id and the car id of the cars at the dealer
//        // create array to store dealer and car information
//        //System.out.println("\n");
//        //System.out.println("Any key to continue");
//        //input.nextLine();
//        return listOfDealers;
//
//    }
//
//    //display a user chosen dealer and the cars at that dealer
//    public void showList(){
//        System.out.println("What is the integer ID for the dealership? ");
//        String idResponse = input.nextLine();
//        outputMessage2 = "";
//        for (int i = 0; i < listOfDealers.size(); i++) {
//            if ( idResponse.equals(listOfDealers.get(i).getDealer_id())) {
//                System.out.println("\nDealerId: " + listOfDealers.get(i).getDealer_id());
//                System.out.println("----------------------------\n");
//                listOfDealers.get(i).toString();
//            }
//        }
//        System.out.println("Any key to continue");
//        input.nextLine();
//    }
//
//
//    //Transfer a car from fromDealer to toDealer
//    public boolean[] transferCar(String fromDealerID, String carID, String toDealerID) {
//
//        //outcome will be return to determine what error or success message are displayed
//        boolean success = false;
//        boolean invalid_FromDealerID = false;
//        boolean invalid_ToDealerID = false;
//        boolean invalid_carID = false;
//
//        boolean[] outcome = {success, invalid_FromDealerID, invalid_ToDealerID, invalid_carID};
//
//        //get list of dealer ids
//        List<String> dealerIds = new ArrayList<>();
//        for (Dealer dealer : listOfDealers) {
//            dealerIds.add(dealer.getDealer_id());
//        }
//
//        //check if fromDealer exists in dealerIds
//        if (!dealerIds.contains(fromDealerID)) {
//            outcome[1] = true;
//        }
//        //check if toDealer exists in dealerIds
//        if (!dealerIds.contains(toDealerID)) {
//            outcome[3] = true;
//        }
//
//        //if fromDealer or toDealer do not exist return
//        if (outcome[1] || outcome[3]) {
//            return outcome;
//        }
//
//        //get dealer object from the fromDealerID
//        //get dealer object from the toDealerID
//        Dealer fromDealer = null;
//        Dealer toDealer = null;
//        for (Dealer dealer : listOfDealers) {
//            if (dealer.getDealer_id().equals(fromDealerID)) {
//                fromDealer = dealer;
//            }
//            if (dealer.getDealer_id().equals(toDealerID)) {
//                toDealer = dealer;
//            }
//        }
//
//        //check if car exists at fromDealer
//        boolean carExists = false;
//        Vehicle tempV = null;
//        for (Vehicle v : fromDealer.getListOfCarsAtDealer()) {
//            if (v.getVehicle_id().equals(carID)) {
//                carExists = true;
//                tempV = v;
//            }
//        }
//
//        //if the carID is not found in the fromDealer, return
//        if (!carExists) {
//            outcome[2] = true;
//            return outcome;
//        }
//
//        //success -> transfer car
//        fromDealer.getListOfCarsAtDealer().remove(tempV);
//        toDealer.getListOfCarsAtDealer().add(tempV);
//        tempV.setDealership_id(toDealerID);
//        outcome[0] = true;
//
//        return outcome;
//    }
//
//    //print commands the user can use
//    public String printMessage2() {
//        return outputMessage2;
//    }
//


