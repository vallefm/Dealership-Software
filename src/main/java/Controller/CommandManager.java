package Controller;

//The GUIControllers call this class. This class calls the command class that the GUIController needs to use
public class CommandManager {

    public void dealerOff(String dealerID){

        Controller.Commands.DealerOff command = new Controller.Commands.DealerOff();
        command.dealerOff(dealerID);
    }

    public void dealerOn(String dealerID){

        Controller.Commands.DealerOn command = new Controller.Commands.DealerOn();
        command.dealerOn(dealerID);
    }

    public boolean[] addCarGUI(String carMake, String carModel, String carDID, String carID, String carType, String carPrice, String currencyType){

        Controller.Commands.AddCar command = new Controller.Commands.AddCar();
        return command.addCarGUI(carMake, carModel, carDID, carID, carType, carPrice, currencyType);
    }

    public boolean[] transferCar(String fromDealer, String carID, String toDealer){

        Controller.Commands.TransferCar command = new Controller.Commands.TransferCar();
        return command.transferCar(fromDealer, carID, toDealer);
    }

    public boolean[] loanSearch(String carID){

        Controller.Commands.LoanSearch command = new Controller.Commands.LoanSearch();
        return command.loanSearch(carID);
    }

    public void setLoanStatus(String carID){

        Controller.Commands.SetLoanStatus command = new Controller.Commands.SetLoanStatus();
        command.setLoanStatus(carID);
    }


    public boolean createDealer(String dID, String dName){

        Controller.Commands.CreateDealer command = new Controller.Commands.CreateDealer();
        return command.createDealer(dID, dName);
    }

    public boolean exportFromDealerToJSON(String dealerID){

        Controller.Commands.ExportDealerToJSON command = new Controller.Commands.ExportDealerToJSON();
        return command.exportDealerToJSON(dealerID);
    }

    public void saveSerializedData(){
        
        Controller.Commands.SaveSerializedData command = new Controller.Commands.SaveSerializedData();
        command.saveSerializedData();
    }
}
