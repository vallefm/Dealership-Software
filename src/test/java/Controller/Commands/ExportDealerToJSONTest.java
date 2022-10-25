package Controller.Commands;

import Models.Company;
import Models.Dealer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExportDealerToJSONTest {

    @Test
    void exportDealerToJSON() {

        String dealerId = "111";

        //Creates a dealer with id 111 and true for isActivated
        Company.getCompany().add(new Dealer(dealerId, true));

        ExportDealerToJSON edtj = new ExportDealerToJSON();

        //If the given dealerId is found in the dealer list, it should return false for Invalid_DealerId and perform the exporting
        assertEquals(false, edtj.exportDealerToJSON(dealerId));
    }
}