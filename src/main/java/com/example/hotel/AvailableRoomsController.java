package com.example.hotel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

public class AvailableRoomsController implements UserAwareController{

    @FXML
    private TableView<Room> roomsTable;
    @FXML
    private TableColumn<Room, String> hotelCol;
    @FXML
    private TableColumn<Room, Integer> roomNumberCol;
    @FXML
    private TableColumn<Room, String> roomTypeCol;
    @FXML
    private TableColumn<Room, Float> priceCol;
    @FXML
    private TableColumn<Room, Integer> capacityCol;
    @FXML
    private TableColumn<Room, String> bedTypeCol;
    @FXML
    private TableColumn<Room, String> tvCol;
    @FXML
    private TableColumn<Room, String> balconyCol;
    @FXML
    private TableColumn<Room, String> jacuzziCol;
    @FXML
    private TableColumn<Room, String> descriptionCol;
    @FXML
    private TableColumn<Room, String> featureCol;
    @FXML
    private TableColumn<Room, String> themeCol;
    @FXML
    private Button backbtn;

    private Customer customer;

    @Override
    public void setUser(User user) {
        this.customer = (Customer) user;
        updateTable();
    }

    @FXML
    public void updateTable() {
        hotelCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getHotel().getName()));

        roomNumberCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getID()).asObject());

        roomTypeCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));

        priceCol.setCellValueFactory(cellData ->
                new SimpleFloatProperty(cellData.getValue().getPrice()).asObject()
        );

        capacityCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getCapacity()).asObject()
        );

        bedTypeCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue() instanceof StandardRoom ?
                        ((StandardRoom) cellData.getValue()).getBedType() : "King"));

        tvCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue() instanceof StandardRoom ?
                        (((StandardRoom) cellData.getValue()).hasTV() ? "Yes" : "No") : "Yes"));

        balconyCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue() instanceof LuxuryRoom ?
                        (((LuxuryRoom) cellData.getValue()).hasBalcony() ? "Yes" : "No") : "-"));

        jacuzziCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue() instanceof LuxuryRoom ?
                        (((LuxuryRoom) cellData.getValue()).hasJacuzzi() ? "Yes" : "No") : "-"));

        descriptionCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescription())
        );

        featureCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue() instanceof UniqueRoom ?
                        ((UniqueRoom) cellData.getValue()).getUniqueFeature() : "-"));

        themeCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue() instanceof UniqueRoom ?
                        ((UniqueRoom) cellData.getValue()).getTheme() : "-"));
        // Set only available rooms
        ObservableList<Room> availableRooms = FXCollections.observableArrayList();
        for (Room r : Room.getAllRooms()) {
            if (!r.isBooked()) {
                availableRooms.add(r);
            }
        }
        Collections.sort(availableRooms);
        roomsTable.setItems(availableRooms);
    }

        public void handlebackbtn(ActionEvent event) throws IOException {
        try{
                navigateToScreen("customer2.fxml", event, "Dashboard");
        }
        catch(Exception e)
        {
            System.out.println("Error loading customer screen: " + e.getMessage());
        }
    }

    private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        customerController controller = loader.getController();
        controller.setUser(customer);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
