package Models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private boolean isActivated = true;
    private String dealer_id;
    private String dealer_name = "";
    List<Vehicle> listOfCarsAtDealer = new ArrayList<Vehicle>();

    public void getAllCarsID() {
        for (Vehicle i : listOfCarsAtDealer) {
            System.out.println("Car ID: " + i.getVehicle_id());
        }
    }

    public boolean getIsActivatedStatus() {
        return isActivated;
    }

    public void setIsActivatedStatus(boolean status) {
        isActivated = status;
    }

    public String getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(String id) {
        dealer_id = id;
    }

    public void setName(String name) { dealer_name = name; }

    public List<Vehicle> getListOfCarsAtDealer() {
        return listOfCarsAtDealer;
    }

    // this method will add a car to the list of cars at this current location unless isActivated is equal to false. This means that the location cannot acquire a new vehicle so the vehicle is not added.
    public void addToListOfCarsAtDealer(Vehicle car) {
        if(isActivated){
            listOfCarsAtDealer.add(car);
        }
        else{
            System.out.println("Vehicle acquisition is currently disabled for the dealership of ID #" + dealer_id);
            System.out.println("Vehicle not added.");
        }
    }

    public String[] toArrString () {
        String[] arr = new String[876876];
        int count = 0;
        for (Vehicle i : listOfCarsAtDealer) {
            arr[count]= ("Car ID: " + i.getVehicle_id() + " | Car Price: " + i.getPrice() + " | Car Acquisition Date: " + Instant.ofEpochMilli(i.getAcquisition_date()) + " | vehicle type: " + i.getVehicle_type() + " | vehicle manufacturer: " + i.getVehicle_manufacturer() + " | vehicle model: " + i.getVehicle_model()).toString();
            count++;
        }
        return arr;
    }

    public String getID(){
        return dealer_id;
    }

    public String getName(){ return dealer_name; }

    public String getActivatedStatus(){
        if(isActivated){
            return "Activated";
        }
        else{
            return "Deactivated";
        }
    }


    public Dealer(String id, boolean status) {
        dealer_id = id;
        isActivated = status;
    }

    public Dealer() {

    }
}
