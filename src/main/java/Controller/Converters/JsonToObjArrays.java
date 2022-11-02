package Controller.Converters;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Models.Dealer;
import Models.Vehicle;

public class JsonToObjArrays {

    public static List<Dealer> jsonToObjArrays(FileReader json) {

        List<Dealer> listOfDealers = new ArrayList<>();

        JsonObject obj = JsonParser.parseReader(json).getAsJsonObject();

        JsonArray jCars = obj.get("car_inventory").getAsJsonArray();

        ArrayList<Vehicle> cars = new ArrayList<>();

        ArrayList<String> allowedVehicles = new ArrayList<>();
        allowedVehicles.add("suv");
        allowedVehicles.add("pickup");
        allowedVehicles.add("sports car");
        allowedVehicles.add("sedan");

        //this enhanced for loop will iterate through each json element in a file and create a inventory object for each vehicle within the file
        for (JsonElement c : jCars) {

            String vehicleTypeString = c.getAsJsonObject().get("vehicle_type").getAsString();

            if (allowedVehicles.contains(vehicleTypeString)) {

                Vehicle car = new Vehicle(
                        c.getAsJsonObject().get("dealership_id").getAsString(),
                        c.getAsJsonObject().get("vehicle_type").getAsString(),
                        c.getAsJsonObject().get("vehicle_manufacturer").getAsString(), // <- Breaks on third iteration
                        c.getAsJsonObject().get("vehicle_model").getAsString(),
                        c.getAsJsonObject().get("vehicle_id").getAsString(),
                        c.getAsJsonObject().get("price").getAsInt(),
                        c.getAsJsonObject().get("acquisition_date").getAsLong());
                cars.add(car);
            } else {

                //if a non allowed car is read, do not add it to cars
                System.out.println("Vehicle Type of " + vehicleTypeString + " is not allowed for vehicle ID: "
                        + c.getAsJsonObject().get("vehicle_id").getAsString());
                System.out.println("Vehicle not added.");
            }
        }

        if (listOfDealers.size() == 0 && cars.size() > 0) {

                Dealer d = new Dealer(cars.get(0).getDealership_id(), true);
                //d.setName(dealersNames.get(d.getDealer_id()));
                listOfDealers.add(d);
        }

        //This method duplicates cars
        for(Vehicle car : cars) {

            Dealer dealer = listOfDealers.stream().filter(d -> d.getDealer_id().equals(car.getDealership_id())).findFirst().orElse(null);
            if( dealer == null ) {

                dealer = new Dealer(car.getDealership_id(), true);
                //dealer.setName(dealersNames.get(dealer.getDealer_id()));
                listOfDealers.add(dealer);
            }

            dealer.addToListOfCarsAtDealer(car);
        }

        return listOfDealers;
    }
}