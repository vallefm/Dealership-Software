package Controller.CommandsPkg;

import Models.Company;
import Models.Dealer;

import java.util.List;

public class DealerOff {

    public void dealerOff(String dealer_ID) {

        List<Dealer> listOfDealers = Company.getCompany();

        boolean dealershipIDFound = false;
        //outputMessage2 = "";
        //System.out.println("Enter the dealer id of the dealership: ");
        String dealerId = dealer_ID;

        for (int i = 0; i < listOfDealers.size(); i++) {
            if (dealerId.equals(listOfDealers.get(i).getDealer_id())) {
                listOfDealers.get(i).setIsActivatedStatus(false);

                dealershipIDFound = true;
            }

        }

        if(dealershipIDFound == false){
            System.out.println("Dealership ID " + dealerId + " does not exist.\n");

            //System.out.println("Press enter to continue");
            //input.nextLine();
        }

        //resets dealershipIDFound
        dealershipIDFound = false;
    }
}
