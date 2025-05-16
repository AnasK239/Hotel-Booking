package com.example.hotel;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class AddRoomController implements UserAwareController {

    @FXML
    private ComboBox<Hotel> hotelComboBox;
    @FXML
    private TextField roomCapacity;
    @FXML
    private TextField roomPrice;
    @FXML
    private TextField roomDescription;
    @FXML
    private ComboBox<String> roomTypeComboBox;
    @FXML
    private Button createButton;
    @FXML
    private Button backButton;
    @FXML
    private Label statusLabel;

    // Standard Room specific fields
    @FXML
    private VBox standardRoomFields;
    @FXML
    private TextField bedTypeField;
    @FXML
    private CheckBox hasTVCheckbox;

    // Unique Room specific fields
    @FXML
    private VBox uniqueRoomFields;
    @FXML
    private TextField uniqueFeatureField;
    @FXML
    private TextField themeField;

    // Luxury Room specific fields
    @FXML
    private VBox luxuryRoomFields;
    @FXML
    private CheckBox hasJacuzziCheckbox;
    @FXML
    private CheckBox hasBalconyCheckbox;
    private Admin admin;

    @Override
    public void setUser(User user) {
        this.admin = (Admin) user;
    }

    public void initialize() {

        hotelComboBox.setItems(FXCollections.observableArrayList(Hotel.getAllHotels()));


        roomTypeComboBox.setItems(FXCollections.observableArrayList(
                Arrays.asList("Standard Room", "Unique Room", "Luxury Room")
        ));


        roomTypeComboBox.setOnAction(e -> {
            String selectedType = roomTypeComboBox.getValue();
            if (selectedType != null) {
                toggleRoomTypeFields(selectedType);
            }
        });

    }

    private void toggleRoomTypeFields(String roomType) {

        standardRoomFields.setVisible(false);
        uniqueRoomFields.setVisible(false);
        luxuryRoomFields.setVisible(false);

        // Show only the fields for selected room type
        switch (roomType) {
            case "Standard Room":
                standardRoomFields.setVisible(true);
                break;
            case "Unique Room":
                uniqueRoomFields.setVisible(true);
                break;
            case "Luxury Room":
                luxuryRoomFields.setVisible(true);
                break;
        }
    }

    @FXML
    public void handleCreateRoom(ActionEvent actionEvent) {
        try {

            Hotel selectedHotel = hotelComboBox.getSelectionModel().getSelectedItem();

            if (selectedHotel == null) {
                showStatus("Please select a hotel", true);
                return;
            }

            String roomType = roomTypeComboBox.getValue();
            if (roomType == null || roomType.isEmpty()) {
                showStatus("Please select a room type", true);
                return;
            }

            String description = roomDescription.getText().trim();
            if (description.isEmpty()) {
                showStatus("Please enter a room description", true);
                return;
            }

            int capacity;
            float price;

            try {
                capacity = Integer.parseInt(roomCapacity.getText().trim());
                if (capacity <= 0) {
                    showStatus("Capacity must be a positive number", true);
                    return;
                }
            } catch (NumberFormatException e) {
                showStatus("Please enter a valid room capacity", true);
                return;
            }

            try {
                price = Float.parseFloat(roomPrice.getText().trim());
                if (price < 0) {
                    showStatus("Price cannot be negative", true);
                    return;
                }
            } catch (NumberFormatException e) {
                showStatus("Please enter a valid price", true);
                return;
            }


            Room newRoom = null;

            switch (roomType) {
                case "Standard Room":
                    if (bedTypeField == null || bedTypeField.getText().trim().isEmpty()) {
                        showStatus("Please enter bed type", true);
                        return;
                    }
                    String bedType = bedTypeField.getText().trim();
                    boolean hasTV = hasTVCheckbox.isSelected();

                    newRoom = new StandardRoom(capacity, price, false, selectedHotel, bedType,
                            hasTV,
                            description
                    );
                    break;

                case "Unique Room":
                    if (uniqueFeatureField == null || uniqueFeatureField.getText().trim().isEmpty()) {
                        showStatus("Please enter unique feature", true);
                        return;
                    }
                    if (themeField == null || themeField.getText().trim().isEmpty()) {
                        showStatus("Please enter theme", true);
                        return;
                    }
                    String uniqueFeature = uniqueFeatureField.getText().trim();
                    String theme = themeField.getText().trim();

                    newRoom = new UniqueRoom(capacity, price, false, selectedHotel, description,
                            uniqueFeature,
                            theme
                    );
                    break;

                case "Luxury Room":
                    boolean hasJacuzzi = hasJacuzziCheckbox.isSelected();
                    boolean hasBalcony = hasBalconyCheckbox.isSelected();

                    newRoom = new LuxuryRoom(capacity, price, false, selectedHotel, description,
                            hasJacuzzi,
                            hasBalcony
                    );
                    break;
            }

            if (newRoom != null) {

                showStatus("Room successfully added to " + selectedHotel.getName(), false);
                clearForm();

            } else {
                showStatus("Failed to create room", true);
            }

        } catch (Exception e) {
            showStatus("Error creating room: " + e, true);
            e.printStackTrace();
        }
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setStyle(isError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }

    private void clearForm() {
        roomCapacity.clear();
        roomPrice.clear();
        roomDescription.clear();
        roomTypeComboBox.getSelectionModel().clearSelection();

        // Clear specific fields
        if (bedTypeField != null) bedTypeField.clear();
        if (hasTVCheckbox != null) hasTVCheckbox.setSelected(false);
        if (uniqueFeatureField != null) uniqueFeatureField.clear();
        if (themeField != null) themeField.clear();
        if (hasJacuzziCheckbox != null) hasJacuzziCheckbox.setSelected(false);
        if (hasBalconyCheckbox != null) hasBalconyCheckbox.setSelected(false);

        // Hide all specific fields
        if (standardRoomFields != null) standardRoomFields.setVisible(false);
        if (uniqueRoomFields != null) uniqueRoomFields.setVisible(false);
        if (luxuryRoomFields != null) luxuryRoomFields.setVisible(false);
    }

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            navigateToScreen("ManageRooms.fxml", event, "Manage Rooms");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        ManageRoomsController controller = loader.getController();
        controller.setUser(admin);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}