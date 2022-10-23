package view.dealership_software;

public class MenuPrint {

    public String getMenuMessage() {
        String message = """
                ReadJSON - Read JSON file from your C: drive(File chooser may appear behind this window)\s  CHECK
                AddCar - Add a car to a dealership\s                                    CHECK
                DealerOff - Turn off the dealership acquisition\s                       CHECK
                DealerOn - Turn on the dealership acquisition\s                   CHECK 
                ShowList - Show list of all current vehicles from a dealership\s
                ExportFromDealer - Export all vehicles from a dealership into a single JSON file\s
                Create - Create a dealership\s
                ShowDealers - Shows a list of all Dealers and Activation status\s 
                ShowAll - Console out all cars in the franchise\s                       CHECK
                Exit - Exit Program
                """;
        return message;
    }
}
