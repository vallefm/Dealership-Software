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
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Models.Vehicle;
import Models.Dealer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Converters {

    //Commands cmds = new Commands();

    public List fromXmlToArr(File xml) throws ParserConfigurationException, IOException, SAXException {

        ArrayList<String> allowedVehicles = new ArrayList<>();
        allowedVehicles.add("suv");
        allowedVehicles.add("pickup");
        allowedVehicles.add("sports car");
        allowedVehicles.add("sedan");

        ArrayList<Vehicle> cars = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new File(String.valueOf(xml)));

        doc.getDocumentElement().normalize();

        NodeList vehicles = doc.getElementsByTagName("Vehicle");


        // Not converted to our situation yet.
        for (int temp = 0; temp < vehicles.getLength(); temp++) {

            Node node = vehicles.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                String dId = element.getParentNode().getAttributes().getNamedItem("id").getTextContent();
                String vId = element.getAttribute("id");
                String type = element.getAttribute("type");

                String manufacturer = element.getElementsByTagName("Make").item(0).getTextContent();
                String price = element.getElementsByTagName("Price").item(0).getTextContent();
                String model = element.getElementsByTagName("Model").item(0).getTextContent();
                Long aDate = System.currentTimeMillis();
                String dName = null;

                String unit = element.getElementsByTagName("Price").item(0).getAttributes().getNamedItem("unit").getTextContent();
                if(node.getParentNode().getNodeType() == Node.ELEMENT_NODE){
                    Element parentElement = (Element) node.getParentNode();
                    dName = parentElement.getElementsByTagName("Name").item(0).getTextContent();
                }


                //temp
                if (allowedVehicles.contains(type)) {
                    Vehicle car = new Vehicle(
                            dId,
                            type,
                            manufacturer,
                            model,
                            vId,
                            Integer.parseInt(price),
                            aDate);

                    //Need a company class to store listOfDealers
//                    if(dName != null){
//                        car.getDealership_id();
//                        if(!cmds.listOfDealers.isEmpty()){
//                            for(Dealer d : cmds.listOfDealers){
//                                if(d.getDealer_id() == car.getDealership_id()){
//                                    if(d.getName() == ""){
//                                        d.setName(dName);
//                                    }
//                                }
//                            }
//                        }
//
//                    }
                    cars.add(car);
                } else {
                    //if a non allowed car is read, do not add it to cars
                    System.out.println("Vehicle Type of " + type + " is not allowed for vehicle ID: "
                            + vId);
                    System.out.println("Vehicle not added.");
                }


            }
        }


        //Once done
        return cars;

    }
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

//                if(c.getAsJsonObject().get("Name") != null){
//                    if(!cmds.listOfDealers.isEmpty()){
//                        for(Dealer d : cmds.listOfDealers){
//                            if(d.getDealer_id() == c.getAsJsonObject().get("dealership_id").getAsString()){
//                                d.setName(c.getAsJsonObject().get("Name").getAsString());
//                            }
//                        }
//                    }
//                }
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
