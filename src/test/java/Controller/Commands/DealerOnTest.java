package Controller.Commands;

import Models.Company;
import Models.Dealer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerOnTest {

    @Test
    void dealerOn() {

        String dealerId = "111";

        //Creates a dealer with id 111 and false for isActivated
        Company.getCompany().add(new Dealer(dealerId, false));

        DealerOn dealer = new DealerOn();

        //Turns on the dealer with specific ID
        dealer.dealerOn(dealerId);

        //If true, returns the string Activated of the dealer that we are retrieving.
        assertEquals("Activated", Company.getCompany().get(0).getActivatedStatus());

    }

}