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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class AvailableRoomsController implements UserAwareController{

    @FXML
    private TableView<Room> roomsTable;
    @FXML
    private TableColumn<Room, Integer> roomNumberCol;
    @FXML
    private TableColumn<Room, String> roomTypeCol;
    @FXML
    private TableColumn<Room, Float> priceCol;
    @FXML
    private TableColumn<Room, Boolean> availabilityCol;
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

        roomNumberCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getID()).asObject()
        );

        roomTypeCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName())
        );

        priceCol.setCellValueFactory(cellData ->
                new SimpleFloatProperty(cellData.getValue().getPrice()).asObject()
        );

        availabilityCol.setCellValueFactory(cellData ->
                new SimpleBooleanProperty(!cellData.getValue().isBooked())
        );

        // Set data - filter only available rooms
        ObservableList<Room> availableRooms = FXCollections.observableArrayList();
        for (Room r : Room.getAllRooms()) {
            if (!r.isBooked()) {
                availableRooms.add(r);
            }
        }
        roomsTable.setItems(availableRooms);
    }

        public void handlebackbtn(ActionEvent event) throws IOException {

        navigateToScreen("customer2.fxml", event, "Dashboard");
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
