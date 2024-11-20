package main.demo;

import files.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PizzaController {

    private ArrayList<Order> orders;
    private Order order;
    private final int ID = 0;


    public PizzaController() {
        orders = new ArrayList<>();
        order = new Order(0);
    }

    // FXML elements linked to UI components in the FXML file


    @FXML
    private Button currentOrderButton;

    @FXML
    private Button storeOrdersButton;

    @FXML
    private void handlePizza() {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Step 1: Pizza style selection
        Label styleLabel = new Label("Choose your pizza style:");
        ComboBox<String> styleComboBox = new ComboBox<>(FXCollections.observableArrayList("Chicago Style", "New York Style"));

        // Step 2: Pizza type selection
        Label typeLabel = new Label("Choose your pizza type:");
        ComboBox<String> typeComboBox = new ComboBox<>(FXCollections.observableArrayList("Deluxe", "Meatzza", "BBQ Chicken", "Build Your Own"));

        // Step 3: Pizza size selection
        Label sizeLabel = new Label("Choose your pizza size:");
        ComboBox<Size> sizeComboBox = new ComboBox<>(FXCollections.observableArrayList(Size.values()));

        // Submit button
        Button submitButton = new Button("Next");
        submitButton.setOnAction(e -> {
            if (styleComboBox.getValue() == null || typeComboBox.getValue() == null || sizeComboBox.getValue() == null) {
                showError("Please complete all selections.");
                return;
            }

            // Proceed to the next window
            String style = styleComboBox.getValue();
            String type = typeComboBox.getValue();
            Size size = sizeComboBox.getValue();
            Pizza current = null;

            if (style.equals("Chicago Style")){
                ChicagoPizza za = new ChicagoPizza();
                current = switch (type) {
                    case ("Deluxe") -> za.createDeluxe(size);
                    case ("Meatzza") -> za.createMeatzza(size);
                    case ("BBQ Chicken") -> za.createBBQChicken(size);
                    case ("Build Your Own") -> za.createBuildYourOwn(size, null);
                    default -> current;
                };
            }
            else {
                NYPizza za = new NYPizza();
                current = switch (type) {
                    case ("Deluxe") -> za.createDeluxe(size);
                    case ("Meatzza") -> za.createMeatzza(size);
                    case ("BBQ Chicken") -> za.createBBQChicken(size);
                    case ("Build Your Own") -> za.createBuildYourOwn(size, null);
                    default -> current;
                };
            }

            openCustomizationWindow(current, style, type);
            primaryStage.close(); // Close the current selection window
        });

        // Main layout organization
        root.getChildren().addAll(styleLabel, styleComboBox, typeLabel, typeComboBox, sizeLabel, sizeComboBox, submitButton);

        // Show scene
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openCustomizationWindow(Pizza current, String style, String type) {
        Stage customizationStage = new Stage();
        customizationStage.setTitle("Customize Your Pizza");

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        ImageView pizzaImageView = new ImageView();
        pizzaImageView.setFitWidth(200);
        pizzaImageView.setFitHeight(200);
        pizzaImageView.setPreserveRatio(true);

        try {
            // Dynamically set the image path
            String imagePath = "/images/" + type.toLowerCase().replace(" ", "") + ".png";
            System.out.println(imagePath);
            Image pizzaImage = new Image(getClass().getResourceAsStream(imagePath));
            pizzaImageView.setImage(pizzaImage);
        } catch (Exception ignored) {

        }

        // Display basic pizza details
        Label pizzaDetails = new Label("Pizza Details:");
        Label selectedDetails = new Label("Style: " + style + "\nType: " + type + "\nCrust: " + current.getCrust() + "\nSize: " + current.getSize());
        Label ingredientsLabel = new Label("Toppings: ");
        Label priceLabel = new Label("Price: $" + String.format("%.2f", current.price()));
        AtomicReference<Pizza> tempPizza = new AtomicReference<>(current);

        // For Build Your Own (BYO) pizzas, show toppings customization
        if (current instanceof BuildYourOwn) {
            Label toppingsLabel = new Label("Add or remove toppings (Max 7):");
            FlowPane toppingsPane = new FlowPane(10, 10);
            toppingsPane.setPadding(new Insets(10));
            toppingsPane.setPrefWrapLength(400);

            ArrayList<Topping> addedToppings = new ArrayList<>();
            for (Topping topping : Topping.values()) {
                CheckBox checkBox = new CheckBox(topping.toString());
                checkBox.setSelected(addedToppings.contains(topping)); // Pre-check existing toppings
                checkBox.setOnAction(e -> {
                    if (checkBox.isSelected()) {
                        if (addedToppings.size() < 7) {
                            addedToppings.add(topping);
                        } else {
                            checkBox.setSelected(false);
                            showError("Maximum 7 toppings allowed.");
                        }
                    } else {
                        addedToppings.remove(topping);
                    }
                    // Update pizza price based on toppings
                    tempPizza.set(new BuildYourOwn(current.getSize(), addedToppings));
                    priceLabel.setText("Price: $" + String.format("%.2f", tempPizza.get().price()));
                });
                toppingsPane.getChildren().add(checkBox);
            }

            root.getChildren().addAll(toppingsLabel, toppingsPane);
        } else {
            // For non-BYO pizzas, show fixed ingredients
            ingredientsLabel.setText("Toppings: " + current.getToppings());
        }

        // Finalize button
        Button finalizeButton = new Button("Add to Order");
        finalizeButton.setOnAction(e -> {
            showAlert("Order Confirmation", "Your pizza has been added to the order!");
            // Add the pizza to the order here (e.g., orderList.add(current))
            order.addPizza(tempPizza.get());
            customizationStage.close();
        });

        // Add elements to root layout
        root.getChildren().addAll(pizzaImageView, pizzaDetails, selectedDetails, ingredientsLabel, priceLabel, finalizeButton);

        // Show customization stage
        Scene scene = new Scene(root, 500, 400);
        customizationStage.setScene(scene);
        customizationStage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
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
