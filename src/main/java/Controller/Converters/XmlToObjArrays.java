package Controller.Converters;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Models.Vehicle;
import Models.Dealer;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlToObjArrays {

    public static List<Dealer> xmlToObjArrays(File xml) throws ParserConfigurationException, IOException, SAXException {

        List<Dealer> listOfDealers = new ArrayList<>();
        List<String> allowedVehicles = new ArrayList<>(Arrays.asList("suv", "pickup", "sports car", "sedan"));
        List<Vehicle> cars = new ArrayList<>();

        HashMap<String,String> dealersNames = new HashMap<>();

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

                String dName = null;
                String dId = element.getParentNode().getAttributes().getNamedItem("id").getTextContent();
                String vId = element.getAttribute("id");
                String type = element.getAttribute("type");
                String manufacturer = element.getElementsByTagName("Make").item(0).getTextContent();
                String price = element.getElementsByTagName("Price").item(0).getTextContent();
                String model = element.getElementsByTagName("Model").item(0).getTextContent();
                String unit = "";

                Long aDate = System.currentTimeMillis();
                
                Node attr = element.getElementsByTagName("Price").item(0).getAttributes().getNamedItem("unit");

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
}