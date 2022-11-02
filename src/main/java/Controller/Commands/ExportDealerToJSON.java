package Controller.Commands;

import Controller.Converters.ObjArraysToJson;
import Models.Company;
import Models.Dealer;

public class ExportDealerToJSON {

    public boolean exportDealerToJSON(String dealerID) {

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
            
            ObjArraysToJson.objArraysToJson(dealer);
        }
        
        return invalid_DealerID;
    }
}
