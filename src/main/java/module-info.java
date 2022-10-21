module view.dealership_software {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;


    opens view.dealership_software to javafx.fxml;
    exports view.dealership_software;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Controller.GUIControllers;
    opens Controller.GUIControllers to javafx.fxml;
    exports Controller.Commands;
    opens Controller.Commands to javafx.fxml;
}