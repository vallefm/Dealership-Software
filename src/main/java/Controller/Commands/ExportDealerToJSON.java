package Controller.Commands;

import Controller.Converters;
import Models.Company;
import Models.Dealer;

import java.util.List;

public class ExportDealerToJSON {

    public boolean exportDealerToJSON(String dealerID) {

        Converters c = new Converters();

        boolean invalid_DealerID = true;

        Dealer dealer = null;

        //find dealer
        for(Dealer d : Company.getCompany()){

            if(d.getDealer_id().equals(dealerID)){

                dealer = d;
                invalid_DealerID = false; //dealer found
            }
        }

        //if dealer found, export to JSON
        if(! invalid_DealerID){
            
            c.convertToJson(dealer);
        }
        return invalid_DealerID;
    }
}
