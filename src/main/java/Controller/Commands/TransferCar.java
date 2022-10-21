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
        boolean invalid_ToDealerID = false;
        boolean invalid_carID = false;

        boolean[] outcome = {success, invalid_FromDealerID, invalid_ToDealerID, invalid_carID};

        //get list of dealer ids
        List<String> dealerIds = new ArrayList<>();
        for (Dealer dealer : listOfDealers) {
            dealerIds.add(dealer.getDealer_id());
        }

        //check if fromDealer exists in dealerIds
        if (!dealerIds.contains(fromDealerID)) {
            outcome[1] = true;
        }
        //check if toDealer exists in dealerIds
        if (!dealerIds.contains(toDealerID)) {
            outcome[3] = true;
        }

        //if fromDealer or toDealer do not exist return
        if (outcome[1] || outcome[3]) {
            return outcome;
        }

        //get dealer object from the fromDealerID
        //get dealer object from the toDealerID
        Dealer fromDealer = null;
        Dealer toDealer = null;
        for (Dealer dealer : listOfDealers) {
            if (dealer.getDealer_id().equals(fromDealerID)) {
                fromDealer = dealer;
            }
            if (dealer.getDealer_id().equals(toDealerID)) {
                toDealer = dealer;
            }
        }

        //check if car exists at fromDealer
        boolean carExists = false;
        Vehicle tempV = null;
        for (Vehicle v : fromDealer.getListOfCarsAtDealer()) {
            if (v.getVehicle_id().equals(carID)) {
                carExists = true;
                tempV = v;
            }
        }

        //if the carID is not found in the fromDealer, return
        if (!carExists) {
            outcome[2] = true;
            return outcome;
        }

        //success -> transfer car
        fromDealer.getListOfCarsAtDealer().remove(tempV);
        toDealer.getListOfCarsAtDealer().add(tempV);
        tempV.setDealership_id(toDealerID);
        outcome[0] = true;

        return outcome;
    }
}
