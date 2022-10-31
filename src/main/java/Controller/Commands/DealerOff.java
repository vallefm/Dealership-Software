package Controller.Commands;

import Models.Company;
import Models.Dealer;

import java.util.List;

public class DealerOff {

    public void dealerOff(String dealer_ID) {

        List<Dealer> listOfDealers = Company.getCompany();

        boolean dealershipIDFound = false;
        
        String dealerId = dealer_ID;

        for (int i = 0; i < listOfDealers.size(); i++) {
            if (dealerId.equals(listOfDealers.get(i).getDealer_id())) {
                listOfDealers.get(i).setIsActivatedStatus(false);

                dealershipIDFound = true;
            }

        }

        if(dealershipIDFound == false){
            System.out.println("Dealership ID " + dealerId + " does not exist.\n");
        }

        //resets dealershipIDFound
        dealershipIDFound = false;
    }
}
