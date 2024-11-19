package main.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class PizzaController {
    // FXML elements linked to UI components in the FXML file
    @FXML
    private Button specialtyPizzasButton;

    @FXML
    private Button byoPizzaButton;

    @FXML
    private Button currentOrderButton;

    @FXML
    private Button storeOrdersButton;

    // This method is called when the "Specialty Pizzas" button is clicked
    @FXML
    private void onSpecialtyPizzasButtonClicked() {
        showAlert("Specialty Pizzas", "You clicked on the Specialty Pizzas button!");
    }

    // This method is called when the "Build Your Own" button is clicked
    @FXML
    private void onBYOButtonClicked() {
        showAlert("Build Your Own", "You clicked on the Build Your Own button!");
    }

    // This method is called when the "Current Order" button is clicked
    @FXML
    private void onCurrentOrderButtonClicked() {
        showAlert("Current Order", "You clicked on the Current Order button!");
    }

    // This method is called when the "Store Orders" button is clicked
    @FXML
    private void onStoreOrdersButtonClicked() {
        showAlert("Store Orders", "You clicked on the Store Orders button!");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
}
