package main.demo;

import files.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
/**
 * Controller class for handling pizza orders and interactions in the pizza ordering system.
 * This class manages pizza selection, customization, order handling, and order export functionalities.
 * @author Amani Islam
 * @author Aryan Rejo
 */
public class PizzaController {

    private ArrayList<Order> orders;
    private Order order;
    private final int ID = 0;


    public PizzaController() {
        orders = new ArrayList<>();
        order = new Order(0);
    }

    // FXML elements linked to UI components in the FXML file

    /**
     * Handles the event of selecting a pizza. Opens a window for selecting pizza style, type, and size.
     */
    @FXML
    private void handlePizza() {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Step 1: Pizza style selection
        Label styleLabel = new Label("Choose your pizza style:");
        ComboBox<PizzaStyle> styleComboBox = new ComboBox<>(FXCollections.observableArrayList(PizzaStyle.values()));

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
            PizzaStyle style = styleComboBox.getValue();
            String type = typeComboBox.getValue();
            Size size = sizeComboBox.getValue();
            Pizza current = null;

            if (style.equals(PizzaStyle.CHICAGO)){
                ChicagoPizza za = new ChicagoPizza();
                current = switch (type) {
                    case ("Deluxe") -> za.createDeluxe(style, size);
                    case ("Meatzza") -> za.createMeatzza(style, size);
                    case ("BBQ Chicken") -> za.createBBQChicken(style, size);
                    case ("Build Your Own") -> za.createBuildYourOwn(style, size, null);
                    default -> current;
                };
            }
            else {
                NYPizza za = new NYPizza();
                current = switch (type) {
                    case ("Deluxe") -> za.createDeluxe(style, size);
                    case ("Meatzza") -> za.createMeatzza(style, size);
                    case ("BBQ Chicken") -> za.createBBQChicken(style, size);
                    case ("Build Your Own") -> za.createBuildYourOwn(style, size, null);
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

    /**
     * Opens the customization window where users can add toppings and view pizza details.
     *
     * @param current The selected pizza to customize.
     * @param style   The selected pizza style.
     * @param type    The selected pizza type.
     */
    private void openCustomizationWindow(Pizza current, PizzaStyle style, String type) {
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
            String imagePath = "/main/demo/images/" + type.toLowerCase().replace(" ", "") + ".png";
            Image pizzaImage = new Image(getClass().getResourceAsStream(imagePath));
            pizzaImageView.setImage(pizzaImage);
        } catch (Exception ignored) {

        }

        // Display basic pizza details
        Label pizzaDetails = new Label("Pizza Details:");
        Label selectedDetails = new Label("Style: " + style.toString() + "\nType: " + type + "\nCrust: " + current.getCrust() + "\nSize: " + current.getSize());
        Label ingredientsLabel = new Label("Toppings: ");
        Label priceLabel = new Label("Price: $" + String.format("%.2f", current.price()));
        AtomicReference<Pizza> tempPizza = new AtomicReference<>(current);

        // For Build Your Own (BYO) pizzas, show toppings customization
        if (current instanceof BuildYourOwn) {
            Label toppingsLabel = new Label("Add or remove toppings (Max 7):");

            // Create a ListView for available toppings (left side)
            ListView<Topping> toppingsListView = new ListView<>();
            toppingsListView.getItems().addAll(Topping.values()); // Add all available toppings
            toppingsListView.setPrefSize(200, 150); // Set a preferred size for the ListView
            toppingsListView.setMaxHeight(150); // Ensure the ListView is visible

            // Create a ListView for selected toppings (right side)
            ListView<Topping> addedToppingsListView = new ListView<>();
            addedToppingsListView.setPrefSize(200, 150); // Set a preferred size for the ListView
            addedToppingsListView.setMaxHeight(150); // Ensure the ListView is visible

            ArrayList<Topping> addedToppings = new ArrayList<>();
            Button addButton = new Button("Add");
            Button removeButton = new Button("Remove");

            // Add button action: Add selected topping to the right ListView
            addButton.setOnAction(e -> {
                Topping selectedTopping = toppingsListView.getSelectionModel().getSelectedItem();
                if (selectedTopping != null && !addedToppings.contains(selectedTopping)) {
                    if (addedToppings.size() < 7) {
                        addedToppings.add(selectedTopping);
                        addedToppingsListView.getItems().add(selectedTopping); // Add to right ListView
                        toppingsListView.getSelectionModel().clearSelection(); // Clear selection on left
                    } else {
                        showError("Maximum 7 toppings allowed.");
                    }
                }
            });

            // Remove button action: Remove selected topping from the right ListView
            removeButton.setOnAction(e -> {
                Topping selectedTopping = addedToppingsListView.getSelectionModel().getSelectedItem();
                if (selectedTopping != null) {
                    addedToppings.remove(selectedTopping);
                    addedToppingsListView.getItems().remove(selectedTopping); // Remove from right ListView
                }
            });

            // Update pizza price based on added toppings
            addedToppingsListView.getItems().addListener((ListChangeListener<? super Topping>) c -> {
                tempPizza.set(new BuildYourOwn(style, current.getSize(), addedToppings));
                priceLabel.setText("Price: $" + String.format("%.2f", tempPizza.get().price()));
            });

            // Add elements to root layout (toppings ListView, buttons, and selected ListView)
            HBox toppingsBox = new HBox(10);
            toppingsBox.getChildren().addAll(toppingsListView, addButton, removeButton, addedToppingsListView); // Place all controls side by side
            root.getChildren().addAll(toppingsLabel, toppingsBox);

        } else {
            // For non-BYO pizzas, show fixed ingredients
            ingredientsLabel.setText("Toppings: " + current.getToppings());
        }

        // Finalize button
        Button finalizeButton = new Button("Add to Order");
        finalizeButton.setOnAction(e -> {
            showAlert("Order Confirmation", "Your pizza has been added to the order!");
            order.addPizza(tempPizza.get());
            customizationStage.close();
        });

        // Add elements to root layout
        root.getChildren().addAll(pizzaImageView, pizzaDetails, selectedDetails, ingredientsLabel, priceLabel, finalizeButton);

        // Show customization stage
        Scene scene = new Scene(root, 600, 400);
        customizationStage.setScene(scene);
        customizationStage.show();
    }

    /**
     * Displays an error alert with the given message.
     *
     * @param message The message to display in the error alert.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Handles the event when the "Current Order" button is clicked.
     * Displays the details of the current order, including pizzas, their toppings,
     * and the order total. Allows the user to remove individual pizzas or clear the entire order.
     */    @FXML
    private void handleCurrentOrder() {
        Stage orderStage = new Stage();
        orderStage.setTitle("Current Orders");

        VBox orderLayout = new VBox(10);
        orderLayout.setPadding(new Insets(15));

        Label headerLabel = new Label("Current Order Details:");
        orderLayout.getChildren().add(headerLabel);

        // If there are no pizzas in the order, display a message
        if (order.getPizzas().isEmpty()) {
            Label noPizzasLabel = new Label("No pizzas have been added to the order yet.");
            orderLayout.getChildren().add(noPizzasLabel);
        } else {
            // Otherwise, iterate through the pizzas in the order and display their details
            for (Pizza pizza : order.getPizzas()) {
                // Prepare the toppings list as a string
                String toppingsList = pizza.getToppings().isEmpty() ? "No toppings" : String.join(", ", pizza.getToppings().toString());

                // Create a label for each pizza's details
                String pizzaDetails = String.format("Style: %s\nType: %s\nSize: %s\nCrust: %s\nToppings: %s\nPrice: $%.2f",
                        pizza.getStyle(),
                        pizza.getClass().getSimpleName(),
                        pizza.getSize(),
                        pizza.getCrust(),
                        toppingsList,
                        pizza.price());

                Label pizzaLabel = new Label(pizzaDetails);
                orderLayout.getChildren().add(pizzaLabel);

                // Add a "Remove" button next to each pizza
                Button removeButton = new Button("Remove");
                removeButton.setOnAction(e -> {
                    // Remove the pizza from the order and update the UI
                    order.removePizza(pizza);
                    // Refresh the order details view
                    handleCurrentOrder(); // This reopens the order window
                    orderStage.close(); // Close the current order window
                });
                orderLayout.getChildren().add(removeButton);
            }

            // Add subtotal, tax, and total price
            Label subtotalLabel = new Label("Subtotal: $" + String.format("%.2f", order.getSubtotal()));
            Label taxLabel = new Label("Tax: $" + String.format("%.2f", order.getTax()));
            Label totalLabel = new Label("Total: $" + String.format("%.2f", order.getTotal()));


            // Add a "Clear Order" button to remove all pizzas
            Button clearOrderButton = new Button("Clear Order");
            clearOrderButton.setOnAction(e -> {
                order = new Order(order.getNumber());
                handleCurrentOrder(); // Refresh the order details view
                orderStage.close(); // Close the current order window
            });

            Button placeOrderButton = new Button("Place Order");
            placeOrderButton.setOnAction(e -> {
                // Simulate placing the order (this can be extended to save to database or send to another system)
                showAlert("Order Placed", "Your order has been successfully placed!");
                orders.add(order);
                order = new Order(order.getNumber()+1);
                orderStage.close(); // Close the order window
            });


            orderLayout.getChildren().addAll(subtotalLabel, taxLabel, totalLabel, clearOrderButton, placeOrderButton);
        }


        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> orderStage.close());
        orderLayout.getChildren().addAll(closeButton);

        Scene orderScene = new Scene(orderLayout, 400, 300);
        orderStage.setScene(orderScene);
        orderStage.show();
        //showAlert("Current Order", "You clicked on the Current Order button!");
    }

    /**
     * Handles the event when the "Manage Orders" button is clicked.
     * Displays a list of all orders placed, including each pizza's details (type, size, crust, toppings).
     * Allows the user to cancel an order and refreshes the view to reflect changes.
     */
    @FXML
    private void handleOrders() {
        if (orders.isEmpty()) { // Check if there are no orders
            showAlert("No Orders Found", "There are no orders to display.");
            return;
        }

        Stage ordersStage = new Stage();
        ordersStage.setTitle("Manage Orders");

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        Label header = new Label("Manage Orders");
        header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        root.getChildren().add(header);

        ScrollPane scrollPane = new ScrollPane();
        VBox ordersList = new VBox(10);
        scrollPane.setContent(ordersList);

        for (Order order : orders) {
            VBox orderBox = new VBox(5);
            orderBox.setPadding(new Insets(10));
            orderBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f4f4f4;");

            Label orderLabel = new Label("Order Number: " + order.getNumber());
            orderLabel.setStyle("-fx-font-weight: bold;");
            orderBox.getChildren().add(orderLabel);

            for (Pizza pizza : order.getPizzas()) {
                String toppingsList = pizza.getToppings().isEmpty()
                        ? "No toppings"
                        : String.join(", ", pizza.getToppings().toString());

                Label pizzaDetails = new Label(String.format(
                        "Type: %s, Size: %s, Crust: %s, Toppings: %s, Price: $%.2f",
                        pizza.getClass().getSimpleName(),
                        pizza.getSize(),
                        pizza.getCrust(),
                        toppingsList,
                        pizza.price()
                ));
                orderBox.getChildren().add(pizzaDetails);
            }

            Label totalLabel = new Label("Total Price: $" + String.format("%.2f", order.getTotal()));
            orderBox.getChildren().add(totalLabel);

            HBox buttonsBox = new HBox(10);
            buttonsBox.setAlignment(Pos.CENTER);

            // Cancel Order Button
            Button cancelButton = new Button("Cancel Order");
            cancelButton.setOnAction(e -> {
                orders.remove(order); // Remove the order from the list
                handleOrders(); // Refresh the view
                ordersStage.close(); // Close the current stage
            });

            buttonsBox.getChildren().addAll(cancelButton);
            orderBox.getChildren().add(buttonsBox);
            ordersList.getChildren().add(orderBox);
        }

        // Export Button
        Button exportButton = new Button("Export Orders");
        exportButton.setOnAction(e -> {
            exportOrders();
        });

        root.getChildren().addAll(scrollPane, exportButton);

        Scene scene = new Scene(root, 500, 600);
        ordersStage.setScene(scene);
        ordersStage.show();
    }

    /**
     * Exports the details of all orders to a text file.
     * If no orders are available, an alert is shown to the user.
     * Writes order information, including pizzas, toppings, price, subtotal, tax, and total, to the selected file.
     */
    private void exportOrders() {
        if (orders.isEmpty()) { // Check if there are no orders
            showAlert("No Orders Found", "There are no orders to export.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Iterate through all orders in the ArrayList
                for (Order order : orders) { // Replace 'allOrders' with your ArrayList of orders
                    writer.write("Order Number: " + order.getNumber());
                    writer.newLine();
                    writer.write("Pizzas in Order:");
                    writer.newLine();

                    // Iterate through each pizza in the current order and write its details
                    for (Pizza pizza : order.getPizzas()) {
                        writer.write("Type: " + pizza.getClass().getSimpleName());
                        writer.newLine();
                        writer.write("Size: " + pizza.getSize());
                        writer.newLine();
                        writer.write("Crust: " + pizza.getCrust());
                        writer.newLine();

                        // Format toppings nicely
                        String toppingsList = pizza.getToppings().isEmpty()
                                ? "No toppings"
                                : String.join(", ", pizza.getToppings().toString());
                        writer.write("Toppings: " + toppingsList);
                        writer.newLine();

                        writer.write("Price: $" + String.format("%.2f", pizza.price()));
                        writer.newLine();
                        writer.newLine();
                    }

                    writer.write("Subtotal: $" + String.format("%.2f", order.getSubtotal()));
                    writer.newLine();
                    writer.write("Tax: $" + String.format("%.2f", order.getTax()));
                    writer.newLine();
                    writer.write("Total Price: $" + String.format("%.2f", order.getTotal()));
                    writer.newLine();
                    writer.write("=======================================");
                    writer.newLine();
                }

                showAlert("Orders Exported", "All orders have been successfully exported to " + file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Displays a confirmation alert with the given title and message.
     *
     * @param title   The title of the alert.
     * @param message The message to display in the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
