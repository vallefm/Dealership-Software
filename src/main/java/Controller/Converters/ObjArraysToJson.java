package Controller.Converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

import Models.Dealer;

public class ObjArraysToJson {

    //this method takes an input Dealer such as a dealership and then it converts the list of vehicles for that Dealer into a .json file into your D:\ drive
    public static void objArraysToJson(Dealer dealer) {

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
}