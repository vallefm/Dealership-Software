package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Company{

    private static List<Dealer> dealers = new ArrayList<>();

    public static Dealer dealerExists(String dId){
        Optional<Dealer> dealer = dealers.stream().filter(d -> d.getDealer_id().equals(dId)).findFirst();
        if(dealer.isEmpty()){
            return null;
        }
        return dealer.get();
    }
    public static List<Dealer> getCompany(){
        return dealers;
    }
}
