package Controller.CommandsPkg;

import Models.Company;
import Models.Dealer;
import Models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class AddCar {

    public boolean[] addCarGUI(String carMake, String carModel, String carDID, String carID, String carType, String carPrice)  {


//        for (int i = 0; i < listOfDealers.size(); i++) {
//
//            // If the vehicle acquisition status is false, dealer can't add cars
//            if (listOfDealers.get(i).getIsActivatedStatus() == true) {
//                //find dealer to add vehicle too
//                if (carDID.equals(listOfDealers.get(i).getDealer_id())) {
//                    String vehicle_type = carType;
//                    String vehicle_manufacturer = carMake;
//                    String vehicle_model = carModel;
//                    String vehicle_id = carID;
//                    int vehicle_price = Integer.parseInt(carPrice);
//                    long acquisition_date = System.currentTimeMillis();
//                    Vehicle car = new Vehicle(carDID, vehicle_type, vehicle_manufacturer, vehicle_model, vehicle_id, vehicle_price, acquisition_date);
//                    listOfDealers.get(i).addToListOfCarsAtDealer(car);
//                }
//
//            }
//            else {
//                //pop up window saying dealer is not activated
//            }
//        }
//
        List<Dealer> listOfDealers = Company.getCompany();
        //outcome array will be returned to determine which error / success labels become visible
        boolean invalid_dealerID = false;
        boolean invalid_DealerClosed = false;
        boolean invalid_carID = false;
        boolean invalid_Type = false;
        boolean success = false;
        boolean[] outcome = {invalid_dealerID, invalid_DealerClosed, invalid_carID, invalid_Type, success};

        //find if dealer exists
        Dealer dealer = null;
        for(Dealer d : listOfDealers){
            if(d.getDealer_id().equals(carDID)){
                dealer = d;
            }
        }
        //if dealer does not exist error
        if(dealer == null){
            outcome[0] = true;
        }
        //if dealer is closed error
        else{
            if(!dealer.getIsActivatedStatus()){
                outcome[1] = true;
            }
        }
        //if carID is not unique error
        for(Dealer d : listOfDealers){
            for(Vehicle v : d.getListOfCarsAtDealer()){
                if(v.getVehicle_id().equals(carID)){
                    outcome[2] = true;
                }
            }
        }
        //if type is not legal error
        ArrayList<String> allowedVehicles = new ArrayList<>();
        allowedVehicles.add("suv");
        allowedVehicles.add("pickup");
        allowedVehicles.add("sports car");
        allowedVehicles.add("sedan");
        if(!allowedVehicles.contains(carType)){
            outcome[3] = true;
        }

        //if there is an error return
        for(int i = 0; i < outcome.length - 1; i++){
            if(outcome[i] == true){
                return outcome;
            }
        }

        //No errors -> add new car to dealer
        int vehicle_price = Integer.parseInt(carPrice);
        long acquisition_date = System.currentTimeMillis();
        Vehicle car = new Vehicle(carDID, carType, carMake, carModel, carID, vehicle_price, acquisition_date);
        dealer.addToListOfCarsAtDealer(car);

        //Set success outcome
        outcome[4] = true;

        return outcome;

    }
}
