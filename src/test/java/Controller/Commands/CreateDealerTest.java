package Controller.Commands;

import Models.Company;
import Models.Dealer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateDealerTest {

    @Test
    void createDealer() {

        CreateDealer cd = new CreateDealer();

        String carId = "111";
        String dealerName = "Bill's Shop";

        //Dealer id 111, name Bill's shop is added to the dealer list.
        Company.getCompany().add(new Dealer(carId, "Bill's Shop"));

        /*The invalid_DealerId will be set to false if the newly created dealer has a unique ID.
        You want the expected value to be false.
        */
        assertEquals(false, cd.createDealer("123","CarShop Experts"));
    }
}