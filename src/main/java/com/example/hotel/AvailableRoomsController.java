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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class AvailableRoomsController implements UserAwareController{

    @FXML
    private TableView<Room> roomsTable;
    @FXML
    private TableColumn<Room, String> hotelCol;
    @FXML
    private TableColumn<Room, Integer> roomNumberCol;
    @FXML
    private TableColumn<Room, Room> roomTypeCol;
    @FXML
    private TableColumn<Room, Float> priceCol;
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
                new SimpleStringProperty(cellData.getValue().getHotel().getName())
        );

        roomNumberCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getID()).asObject()
        );

        roomTypeCol.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue())
        );
        roomTypeCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                setText(empty || room == null ? "" : room.getClass().getSimpleName());
            }
        });

        priceCol.setCellValueFactory(cellData ->
                new SimpleFloatProperty(cellData.getValue().getPrice()).asObject()
        );

        // Set data only available rooms
        ObservableList<Room> availableRooms = FXCollections.observableArrayList();
        for (Room r : Room.getAllRooms()) {
            if (!r.isBooked()) {
                availableRooms.add(r);
            }
        }
        roomsTable.setItems(availableRooms);
        roomTypeCol.setComparator(Room::compareTo);
        roomsTable.getSortOrder().clear();
        roomsTable.getSortOrder().add(roomTypeCol);
        roomTypeCol.setSortType(TableColumn.SortType.ASCENDING);
        roomsTable.sort();
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
