package view.dealership_software;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GUI extends Application {


    @Override
    public void start(Stage mainWindow) throws IOException {
        
        String serializedDataFilePath = System.getProperty("user.dir") + "\\company-serialized-data.ser";
        File f = new File(serializedDataFilePath);

        if(f.exists() && !f.isDirectory()){

            Controller.Converters.deserializeData(serializedDataFilePath);
        }

        Parent root = FXMLLoader.load(getClass().getResource("mainMenuGUI.fxml"));
        Scene scene =  new Scene(root);
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void main(String[] args) {
        
        launch();
    }
}