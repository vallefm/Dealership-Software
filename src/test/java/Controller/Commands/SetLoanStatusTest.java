package Controller.Commands;

import Models.Company;
import Models.Dealer;
import Models.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetLoanStatusTest {

    String dealershipId = "111";
    String vehicleType = "SUV";
    String vehicleManufacturer = "Toyota";
    String vehicleModel = "4Runner";
    String vehicleId = "123f";
    int vehiclePrice = 50000;
    long acquisition_date = 1111111;

    Boolean isLoan = false;

    Vehicle newCar = new Vehicle(dealershipId, vehicleType, vehicleManufacturer, vehicleModel, vehicleId, vehiclePrice, acquisition_date);

    @Test
    void setLoanStatus() {
        Company.getCompany().add(new Dealer(dealershipId, true));

        Dealer dealer = new Dealer();
        SetLoanStatus sls = new SetLoanStatus();

        dealer.getListOfCarsAtDealer().add(newCar);

        //Sets the loan status opposite to its original state for the car.
        sls.setLoanStatus(vehicleId);

        assertEquals(false, dealer.getListOfCarsAtDealer().get(0).getIsLoaned());
    }
}