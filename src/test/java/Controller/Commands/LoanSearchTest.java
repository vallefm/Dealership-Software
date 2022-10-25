package Controller.Commands;

import Models.Company;
import Models.Dealer;
import Models.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanSearchTest {

    @Test
    void loanSearch() {

        String dealershipId = "111";
        String vehicleType = "SUV";
        String vehicleManufacturer = "Toyota";
        String vehicleModel = "4Runner";
        String vehicleId = "123f";
        int vehiclePrice = 50000;
        long acquisition_date = 1111111;
        Boolean isLoan = false;

        Vehicle newCar = new Vehicle(dealershipId, vehicleType, vehicleManufacturer, vehicleModel, vehicleId, vehiclePrice, acquisition_date);

        Company.getCompany().add(new Dealer(dealershipId, true));

        Dealer dealer = new Dealer();
        LoanSearch ls= new LoanSearch();

        //Add car with the above attributes to list of cars at dealership
        dealer.getListOfCarsAtDealer().add(newCar);

        /*
        The method loanSearch returns a boolean array with 2 indexes. The first index is the invalid_CarId boolean,
        and the second index is the carLoanedStatus boolean.
         */
        assertArrayEquals(new boolean[]{true, false}, ls.loanSearch(vehicleId) );
    }
}