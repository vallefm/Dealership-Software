package Controller.Commands;

import Models.Company;
import Models.Dealer;
import Models.Vehicle;

import java.util.List;

public class AddCar {

    public boolean[] addCarGUI(String carMake, String carModel, String carDID, String carID, String carType, String carPrice)  {

        List<Dealer> listOfDealers = Company.getCompany();

        //outcome array will be returned to determine which error / success labels become visible
        boolean invalid_dealerID = false;
        boolean invalid_DealerClosed = false;
        boolean invalid_carID = false;
        boolean success = false;

        //find if dealer exists
        Dealer dealer = null;
        for(Dealer d : listOfDealers){
            if(d.getDealer_id().equals(carDID)){
                dealer = d;
            }
        }

        //if dealer does not exist error
        if(dealer == null){

            invalid_dealerID = true;
        }
        //if dealer is closed error
        else{

            if(!dealer.getIsActivatedStatus()){
                invalid_DealerClosed = true;
            }
        }
        //if carID is not unique error
        for(Dealer d : listOfDealers){

            for(Vehicle v : d.getListOfCarsAtDealer()){

                if(v.getVehicle_id().equals(carID)){

                    invalid_carID = true;
                }
            }
        }


        //if there is an error return
        if(invalid_carID || invalid_dealerID || invalid_DealerClosed){

            return new boolean[]{
                    invalid_carID,
                    invalid_dealerID,
                    invalid_DealerClosed,
                    success};
        }

        //No errors -> add new car to dealer
        int vehicle_price = Integer.parseInt(carPrice);
        long acquisition_date = System.currentTimeMillis();
        Vehicle car = new Vehicle(carDID, carType, carMake, carModel, carID, vehicle_price, acquisition_date);
        dealer.addToListOfCarsAtDealer(car);

        success = true;

        return new boolean[] {
                invalid_carID,
                invalid_dealerID,
                invalid_DealerClosed,
                success};

    }
}
