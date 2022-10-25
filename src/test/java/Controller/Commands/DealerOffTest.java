package Controller.Commands;

import Models.Company;
import Models.Dealer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerOffTest {

    @Test
    void dealerOff() {

        String dealerId = "111";

        //Creates a dealer with id 111 and true for isActivated
        Company.getCompany().add(new Dealer(dealerId, true));

        DealerOff df = new DealerOff();

        //Turns off dealer with specific id
        df.dealerOff(dealerId);

        //If dealer is off, it should return string Deactivated when retrieving the getAtctivatedStatus
        assertEquals("Deactivated", Company.getCompany().get(0).getActivatedStatus());
    }
}