package com.inventoryapp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InventoryGUI {
    private JFrame frame;
    private JTextField nameField, quantityField, priceField;
    private JButton addButton, updateButton, deleteButton;
    private JComboBox<String> itemComboBox;

    private ArrayList<InventoryItem> inventory = new ArrayList<>();

    public InventoryGUI() {
        frame = new JFrame("Inventory Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2, 10, 10));
        frame.setLocationRelativeTo(null); // Center the window

        // Labels and fields
        frame.add(new JLabel("Item Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        frame.add(quantityField);

        frame.add(new JLabel("Price (₹):"));
        priceField = new JTextField();
        frame.add(priceField);

        frame.add(new JLabel("Select Item:"));
        itemComboBox = new JComboBox<>();
        itemComboBox.addActionListener(e -> loadSelectedItem());
        frame.add(itemComboBox);

        // Buttons
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        addButton.addActionListener(e -> addItem());
        updateButton.addActionListener(e -> updateItem());
        deleteButton.addActionListener(e -> deleteItem());

        frame.add(addButton);
        frame.add(updateButton);
        frame.add(deleteButton);

        frame.setVisible(true);
    }

    private void addItem() {
        String name = nameField.getText().trim();
        String quantityStr = quantityField.getText().trim();
        String priceStr = priceField.getText().trim();

        if (name.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            showMessage("All fields are required.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);

            InventoryItem item = new InventoryItem(name, quantity, price);
            inventory.add(item);
            itemComboBox.addItem(name);
            clearFields();
            showMessage("Item added successfully.");
        } catch (NumberFormatException e) {
            showMessage("Invalid quantity or price.");
        }
    }

    private void updateItem() {
        int index = itemComboBox.getSelectedIndex();
        if (index == -1) {
            showMessage("No item selected.");
            return;
        }

        String name = nameField.getText().trim();
        String quantityStr = quantityField.getText().trim();
        String priceStr = priceField.getText().trim();

        if (name.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            showMessage("All fields are required.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);

            InventoryItem item = inventory.get(index);
            item.setName(name);
            item.setQuantity(quantity);
            item.setPrice(price);

            // Update display name in combo box
            itemComboBox.insertItemAt(name, index);
            itemComboBox.removeItemAt(index + 1);
            itemComboBox.setSelectedIndex(index);

            showMessage("Item updated successfully.");
        } catch (NumberFormatException e) {
            showMessage("Invalid quantity or price.");
        }
    }

    private void deleteItem() {
        int index = itemComboBox.getSelectedIndex();
        if (index == -1) {
            showMessage("No item selected.");
            return;
        }

        inventory.remove(index);
        itemComboBox.removeItemAt(index);
        clearFields();
        showMessage("Item deleted.");
    }

    private void loadSelectedItem() {
        int index = itemComboBox.getSelectedIndex();
        if (index != -1 && index < inventory.size()) {
            InventoryItem item = inventory.get(index);
            nameField.setText(item.getName());
            quantityField.setText(String.valueOf(item.getQuantity()));
            priceField.setText(String.valueOf(item.getPrice()));
        }
    }

    private void clearFields() {
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryGUI::new);
    }
}
