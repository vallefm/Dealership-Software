package Controller.Commands;

import Controller.CommandManager;
import Models.Company;
import Models.Dealer;
import Models.Vehicle;

public class SetLoanStatus {

    //sets the loan status to the opposite of what is currently is
    public void setLoanStatus(String carID){

        for(Dealer d : Company.getCompany()){

            for(Vehicle v : d.getListOfCarsAtDealer()){

                if(v.getVehicle_id().equals(carID)){

                    v.setIsLoaned(!v.getIsLoaned());
                }
            }
        }
    }
}
