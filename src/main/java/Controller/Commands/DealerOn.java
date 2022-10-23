package Controller.Commands;

import Models.Company;
import Models.Dealer;

import java.util.List;

public class DealerOn {

    public void dealerOn(String dealer_ID) {

        List<Dealer> listOfDealers = Company.getCompany();

        boolean dealershipIDFound = false;

        //outputMessage2 = "";
        //System.out.println("Enter the dealer id of the dealership: ");
        //String idString = input.nextLine();

        for (int i = 0; i < listOfDealers.size(); i++) {
            if (dealer_ID.equals(listOfDealers.get(i).getDealer_id())) {
                listOfDealers.get(i).setIsActivatedStatus(true);

                dealershipIDFound = true;
            }
        }

        if(dealershipIDFound == false){
            System.out.println("Dealership ID " + dealer_ID + " does not exist.\n");

            //System.out.println("Press enter to continue");
            //input.nextLine();
        }

        //resets dealershipIDFound
        dealershipIDFound = false;
    }
}
