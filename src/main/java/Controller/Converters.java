package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import Models.Company;
import Models.Vehicle;
import Models.Dealer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Converters {

    public List<Dealer> fromXmlToArr(File xml) throws ParserConfigurationException, IOException, SAXException {
        List<Dealer> listOfDealers = new ArrayList<>();
        ArrayList<String> allowedVehicles = new ArrayList<>();
        allowedVehicles.add("suv");
        allowedVehicles.add("pickup");
        allowedVehicles.add("sports car");
        allowedVehicles.add("sedan");

        HashMap<String,String> dealersNames = new HashMap<>();

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

                Node attr = element.getElementsByTagName("Price").item(0).getAttributes().getNamedItem("unit");
                String unit = "";

                if(attr != null){

                    unit = attr.getTextContent();

                }




                if(node.getParentNode().getNodeType() == Node.ELEMENT_NODE){
                    Element parentElement = (Element) node.getParentNode();
                    if(parentElement.getElementsByTagName("Name").getLength() > 0){
                        dName = parentElement.getElementsByTagName("Name").item(0).getTextContent();
                        dealersNames.put(dId, dName);
                    }

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
//                        if(!cmds.listOfDealers.isEmpty()){
//                            for(Dealer d : cmds.listOfDealers){
//                                if(d.getDealer_id().equals(dId)){
//                                    if(d.getName().equals("")){
//                                        d.setName(dName);
//                                    }
//                                }
//                            }
//                        }
//
//                    }
                    cars.add(car);
                    if(unit.equalsIgnoreCase("pounds")){
                        car.setCurrencyType("£");
                    }

                } else {
                    //if a non allowed car is read, do not add it to cars
                    System.out.println("Vehicle Type of " + type + " is not allowed for vehicle ID: "
                            + vId);
                    System.out.println("Vehicle not added.");
                }


            }
        }

        // if listOfDealers is empty, add the dealer of the first car in listOfCars to
        // listOfDealers
        if (listOfDealers.size() == 0 && cars.size() > 0) {
            Dealer d = new Dealer(cars.get(0).getDealership_id(), true);
            d.setName(dealersNames.get(d.getDealer_id()));
            listOfDealers.add(d);
        }
        // put cars from json file into dealers
        // create new dealer if car's dealership_id does not match any existing dealers
//        for(Vehicle car : cars) {
//            for(Dealer d : listOfDealers) {
//                if(car.getDealership_id() != d.getDealer_id()){
//                    listOfDealers.add(new Dealer(car.getDealership_id(), true));
//                    d.addToListOfCarsAtDealer(car);
//                }
//            }
//        }

        for(Vehicle car : cars) {
            Dealer dealer = listOfDealers.stream().filter(d -> d.getDealer_id().equals(car.getDealership_id())).findFirst().orElse(null);
                if( dealer == null ) {
                    dealer = new Dealer(car.getDealership_id(), true);
                    dealer.setName(dealersNames.get(dealer.getDealer_id()));
                    listOfDealers.add(dealer);
                }
                    dealer.addToListOfCarsAtDealer(car);
            }



        //Once done
        return listOfDealers;

    }
    public List<Dealer> fromJsonToInvArr(FileReader json) {
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

    //this method takes an input Dealer such as a dealership and then it converts the list of vehicles for that Dealer into a .json file into your D:\ drive
    public void convertToJson(Dealer dealer) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Json file Dealer
        String path = System.getProperty("user.dir")+"\\" + dealer.getDealer_id() + ".json";
        File file = new File(path);
        FileWriter fw;

        //create file and write the dealer information to it
        try {
            file.createNewFile();

            fw = new FileWriter(file);

            fw.write(gson.toJson(dealer));

            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void deserializeData(String serializedDataPath){
        List<Dealer> listOfDealers = null;
        try {

            // This block of code  of creates an object of arrayList containing the vehicle objects to be loaded into our program further down
            FileInputStream serializedDataFile = new FileInputStream(serializedDataPath);
            ObjectInputStream inputObjects = new ObjectInputStream(serializedDataFile);
            listOfDealers = (List<Dealer>)inputObjects.readObject();
            serializedDataFile.close();
            inputObjects.close();            
            Company.getCompany().addAll(listOfDealers);

         } catch (IOException i) {
            i.printStackTrace();
            System.out.println("fail ioexception deserialize");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("fail classnotfoundexception deserialize");
        }
    }
}
