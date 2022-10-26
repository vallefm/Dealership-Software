package Controller.Commands;

import Models.Company;
import Models.Dealer;
import Models.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferCarTest {

    @Test
    void transferCar() {

        String dealerIDOne = "111";
        String dealerIDTwo = "222";
        Dealer dealer = new Dealer();



        //Created the car that will be transfer between the two dealers. The car will be initially placed in dealerOne.
        String carId = "123f";
        Vehicle car = new Vehicle(dealerIDOne, "SUV", "Toyota", "4Runner", carId, 50000, 1111111);

        //Created two dealerships that I will be transferring the car between.
        Company.getCompany().add(new Dealer(dealerIDOne, true));
        Company.getCompany().add(new Dealer(dealerIDTwo, true));

        //Adding the car to the first index of dealership list which should be the dealerIDOne
        Company.getCompany().get(0).addToListOfCarsAtDealer(car);

        TransferCar tc = new TransferCar();

        /*transferCar methods returns a boolean array of 5 elements. The elements are arrange in this order,
        success, invalid_FromDealerID, invalid_carID, invalid_ToDealerID, invalid_ToDealerClosed
        You want the success variable to be true and the rest of the variables to be false
         */
        assertArrayEquals(new boolean[]{true, false, false, false, false}, tc.transferCar(dealerIDOne, carId, dealerIDTwo) );
    }
}