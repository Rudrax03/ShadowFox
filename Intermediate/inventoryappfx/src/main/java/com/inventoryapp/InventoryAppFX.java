package com.inventoryapp;

import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class InventoryAppFX extends Application {
    private final ObservableList<InventoryItem> inventory = FXCollections.observableArrayList();
    private final ComboBox<InventoryItem> itemComboBox = new ComboBox<>();

    private final TextField nameField = new TextField();
    private final TextField quantityField = new TextField();
    private final TextField priceField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inventory Management (JavaFX)");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // Row 0
        grid.add(new Label("Item Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        // Row 1
        grid.add(new Label("Quantity:"), 0, 1);
        grid.add(quantityField, 1, 1);

        // Row 2
        grid.add(new Label("Price (₹):"), 0, 2);
        grid.add(priceField, 1, 2);

        // Row 3 - ComboBox
        grid.add(new Label("Select Item:"), 0, 3);
        itemComboBox.setItems(inventory);
        itemComboBox.setOnAction(e -> loadSelectedItem());
        grid.add(itemComboBox, 1, 3);

        // Row 4 - Buttons
        HBox buttonBox = new HBox(10);
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton);
        grid.add(buttonBox, 1, 4);

        // Event handlers
        addButton.setOnAction(e -> addItem());
        updateButton.setOnAction(e -> updateItem());
        deleteButton.setOnAction(e -> deleteItem());

        Scene scene = new Scene(grid, 400, 280);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addItem() {
        String name = nameField.getText().trim();
        String quantityText = quantityField.getText().trim();
        String priceText = priceField.getText().trim();

        if (name.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            double price = Double.parseDouble(priceText);

            InventoryItem item = new InventoryItem(name, quantity, price);
            inventory.add(item);
            itemComboBox.getSelectionModel().select(item);
            clearFields();
            showAlert("Item added successfully.");
        } catch (NumberFormatException e) {
            showAlert("Quantity and price must be valid numbers.");
        }
    }

    private void updateItem() {
        InventoryItem selected = itemComboBox.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an item to update.");
            return;
        }

        try {
            String name = nameField.getText().trim();
            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            selected.setName(name);
            selected.setQuantity(quantity);
            selected.setPrice(price);

            // Refresh ComboBox by resetting items
            itemComboBox.setItems(null);
            itemComboBox.setItems(inventory);
            itemComboBox.getSelectionModel().select(selected);

            clearFields();
            showAlert("Item updated successfully.");
        } catch (NumberFormatException e) {
            showAlert("Invalid quantity or price.");
        }
    }

    private void deleteItem() {
        InventoryItem selected = itemComboBox.getSelectionModel().getSelectedItem();
        if (selected != null) {
            inventory.remove(selected);
            itemComboBox.getSelectionModel().clearSelection();
            clearFields();
            showAlert("Item deleted.");
        } else {
            showAlert("No item selected.");
        }
    }

    private void loadSelectedItem() {
        InventoryItem selected = itemComboBox.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameField.setText(selected.getName());
            quantityField.setText(String.valueOf(selected.getQuantity()));
            priceField.setText(String.valueOf(selected.getPrice()));
        }
    }

    private void clearFields() {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
