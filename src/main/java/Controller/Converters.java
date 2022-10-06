package Controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Models.Vehicle;
import Models.Dealer;

public class Converters {
    public List<Vehicle> fromJsonToInvArr(FileReader json) {


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
        return cars;
    }

    //this method takes an input Dealer such as a dealership and then it converts the list of vehicles for that Dealer into a .json file into your D:\ drive
    public void convertToJson(Dealer dealer) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Json file Dealer
        String path = "C:\\Users\\Public\\" + dealer.getDealer_id() + ".json";
        File file = new File(path);
        FileWriter fw;

        //create file and write the dealer inforamtion to it
        try {
            file.createNewFile();

            fw = new FileWriter(file);

            fw.write(gson.toJson(dealer));

            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to create or write to Json File");
            throw new RuntimeException(e);
        }

        System.out.println("Your file was sent to C:\\Users\\Public");

    }
}
