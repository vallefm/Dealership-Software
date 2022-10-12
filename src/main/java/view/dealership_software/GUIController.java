package view.dealership_software;
import Controller.Commands;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.imageio.IIOParam;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ActionEvent tempEvent;

    @FXML
    private ListView carList;

    @FXML
    private ObservableList<String> carList1 = FXCollections.observableArrayList();

    Commands cmds = new Commands();








    public void switchToEditScene(ActionEvent event) throws IOException {
        cmds.readJSON();
        Parent root = FXMLLoader.load(getClass().getResource("listGUI.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listGUI.fxml"));
        loader.setController(this);
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainGUI.fxml"));
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToListGUI(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("listGUI.fxml"));
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


    public void loadList(ActionEvent event) throws IOException {
        String[] list = cmds.showAll();
        System.out.println(list);
        carList1.setAll(list);
        carList.setItems(carList1);

        }

    private void refreshList(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("listGUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //exit program function
    public void exitProgram() {
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}