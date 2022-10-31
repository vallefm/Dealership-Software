package Controller.Commands;

import Models.Company;
import Models.Dealer;
import Models.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddCarTest {

    @Test
    void addCarGUI() {

        AddCar ac = new AddCar();
        Company.getCompany().add(new Dealer("111", true)); //Adds the dealer into the company
        Dealer dealer = new Dealer();

        //input for new car to be added
        String carMake = "Toyota";
        String carMod = "4Runner";
        String carDId = "111"; //This has to the match dealer id
        String carId = "123f"; //This has to be unique from other cars
        String carType = "SUV"; //As of right now, dealerships only accept 4 types of cars, SUV, sports car, sedan and pick-up
        String carPrice = "50000";

        //Making the car with the above information and add that to the dealer list
        dealer.addToListOfCarsAtDealer(new Vehicle(carDId, carType, carMake, carMod, carId, 50000, 1111111111));

        //Checking if dealer id in the dealerlist matches the input dealer ID input
        assertEquals(Company.getCompany().get(0).getDealer_id(), carDId);

        //invalid_carID, invalid_dealerId, invalid_DealerClosed, success......You want index 3 to be true
        //assertArrayEquals(new boolean[]{false, false, false, true}, ac.addCarGUI(carMake, carMod, "111", carId, carType, carPrice));

        //Checking to see if the inputs above match the object attributes in the carlist
        assertEquals(carMake, dealer.getListOfCarsAtDealer().get(0).getVehicle_manufacturer());
        assertEquals(carMod, dealer.getListOfCarsAtDealer().get(0).getVehicle_model());
        assertEquals(carDId, dealer.getListOfCarsAtDealer().get(0).getDealership_id());
        assertEquals(carId, dealer.getListOfCarsAtDealer().get(0).getVehicle_id());
        assertEquals(carType, dealer.getListOfCarsAtDealer().get(0).getVehicle_type());
        assertEquals(50000, dealer.getListOfCarsAtDealer().get(0).getPrice());





    }
}