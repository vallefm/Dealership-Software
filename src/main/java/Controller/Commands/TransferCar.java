package Controller.Commands;

import Models.Company;
import Models.Dealer;
import Models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class TransferCar {

    List<Dealer> listOfDealers = Company.getCompany();

    public boolean[] transferCar(String fromDealerID, String carID, String toDealerID) {

        //outcome will be return to determine what error or success message are displayed
        boolean success = false;
        boolean invalid_FromDealerID = false;
        boolean invalid_carID = false;
        boolean invalid_ToDealerID = false;
        boolean invalid_ToDealerClosed = false;

        //get list of dealer ids
        List<String> dealerIds = new ArrayList<>();
        for (Dealer dealer : listOfDealers) {
            dealerIds.add(dealer.getDealer_id());
        }

        //check if fromDealerID exists in dealerIds
        //if it does, get Dealer fromDealer
        //check if carID exists in fromDealer
        Vehicle carToBeTransfered = null;
        Dealer fromDealer = null;
        if (!dealerIds.contains(fromDealerID)) {
            invalid_FromDealerID = true;
        }
        else{
            //fromDealerID exists in dealerIds
            //get Dealer fromDealer
            for(Dealer d : Company.getCompany()){
                if(d.getDealer_id().equals(fromDealerID)){
                    fromDealer = d;
                }
            }
            //check if carID exists in fromDealer
            for(Vehicle v : fromDealer.getListOfCarsAtDealer()){
                if(v.getVehicle_id().equals(carID)){
                    carToBeTransfered = v;
                }
            }

            //if car deos not exist in fromDealer error
            if(carToBeTransfered == null){
                invalid_carID = true;
            }
        }



        //check if toDealerID exists in dealerIds
        //if it does, get Dealer toDealer
        //check if toDealer is closed
        Dealer toDealer = null;
        if (!dealerIds.contains(toDealerID)) {
            invalid_ToDealerID = true;
        }else{
            //toDealerID exists in DealerIds
            //get Dealer toDealer
            //check of toDealer is open
            for(Dealer d : Company.getCompany()){
                if(d.getDealer_id().equals(toDealerID)){
                    toDealer = d;
                }
            }

            if(!toDealer.getIsActivatedStatus()){
                invalid_ToDealerClosed = true;
            }
        }

        //if any errors are found, return
        if (invalid_FromDealerID || invalid_carID || invalid_ToDealerID || invalid_ToDealerClosed) {
            return new boolean[]{
                    success, //always false here
                    invalid_FromDealerID,
                    invalid_carID,
                    invalid_ToDealerID,
                    invalid_ToDealerClosed};
        }


        //success -> transfer car
        fromDealer.getListOfCarsAtDealer().remove(carToBeTransfered);
        toDealer.getListOfCarsAtDealer().add(carToBeTransfered);
        carToBeTransfered.setDealership_id(toDealerID);
        success = true;

        return new boolean[]{
                success, //true
                invalid_FromDealerID, //always false
                invalid_carID, //always false
                invalid_ToDealerID, //always false
                invalid_ToDealerClosed}; //always false
    }
}