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

    public static void addDealerListToCompany(List<Dealer> listOfDealers){

        for(Dealer newDealer : listOfDealers){

            for(int i = 0; i < newDealer.getListOfCarsAtDealer().size(); i++){

                Vehicle newVehicle = newDealer.getListOfCarsAtDealer().get(i);

                for(Dealer companyDealer : Company.getCompany()){

                    for(Vehicle companyVehicle : companyDealer.getListOfCarsAtDealer()){
                        
                        if(newVehicle.getVehicle_id().equals(companyVehicle.getVehicle_id())){

                            newDealer.getListOfCarsAtDealer().remove(newVehicle);
                            i=-1;
                            break;
                        }
                    }
                }
            }
        }

        //get dealerIDs for all dealers in Company
        List<String> companyDealerIDs = new ArrayList<>();
        for(Dealer d : Company.getCompany()){

            companyDealerIDs.add(d.getDealer_id());
        }

        //if a Dealer id from listOfDealers does not exist in companyDealerIDs, add the new dealer to company
        List<Dealer> deleteFromListOfDealers = new ArrayList<>();
        for(Dealer d : listOfDealers){

            if(!companyDealerIDs.contains(d.getDealer_id())){

                Company.getCompany().add(d);
                deleteFromListOfDealers.add(d);
            }
        }

        //delete the dealers that have been added to Company
        for(Dealer d : deleteFromListOfDealers){

            listOfDealers.remove(d);
        }

        //merge remaining new dealers with company dealers
        for(Dealer newDealer : listOfDealers){
            
            for(Dealer companyDealer : Company.getCompany()){

                //check if company dealer has a name, if not and the new dealer does,
                //added the name of the new dealer to the company dealer
                if(newDealer.getDealer_id().equals(companyDealer.getDealer_id())){

                    if(companyDealer.getName().equals("") && !newDealer.getName().equals("")){

                        companyDealer.setName(newDealer.getName());
                    }

                    for(Vehicle v : newDealer.getListOfCarsAtDealer()){

                        companyDealer.getListOfCarsAtDealer().add(v);
                    }
                }
            }
        }
    }
}
