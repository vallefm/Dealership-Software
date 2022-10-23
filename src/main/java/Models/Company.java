package Models;

import java.util.ArrayList;
import java.util.List;

public class Company{

    private static List<Dealer> dealers;

    public static List<Dealer> getCompany(){
        if(dealers == null){
            synchronized (Company.class){
                if(dealers == null){
                    dealers = new ArrayList<>();
                }
            }
        }
        return dealers;
    }
}
