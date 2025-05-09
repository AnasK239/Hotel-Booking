package com.example.hotel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageRoomsController implements UserAwareController {
    @FXML
    private TableView<Room> roomsTable;

    @FXML
    private TableColumn<Room, String> roomNumberColumn;
    @FXML
    private TableColumn<Room, String> hotelnameColumn;

    @FXML
    private TableColumn<Room, String> roomTypeColumn;

    @FXML
    private TableColumn<Room, Boolean> availabilityColumn;

    @FXML
    private Button backbtn;

    @FXML
    private Button addRoomBtn;


    @FXML
    private Button delRoomBtn;

    private ObservableList<Room> roomsList;
    private Admin admin;

    @Override
    public void setUser(User user) {
        this.admin = (Admin) user;
    }


    public void initialize() {

        roomNumberColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getID()).asString()
        );
        hotelnameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getHotel().getName())
        );

        roomTypeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName())
        );

        availabilityColumn.setCellValueFactory(cellData ->
                new SimpleBooleanProperty(!cellData.getValue().isBooked())
        );

        ObservableList<Room> availableRooms = FXCollections.observableArrayList();
        for (Room r : Room.getAllRooms()) {
            if (!r.isBooked()) {
                availableRooms.add(r);
            }
        }

        roomsTable.setItems(availableRooms);

        refreshRoomsList();
    }

    private void refreshRoomsList() {
        roomsList = FXCollections.observableArrayList(Room.getAllRooms());
        roomsTable.setItems(roomsList);
    }
    @FXML
    private void handleAddRoom(ActionEvent event) {
        try {
            navigateToScreen("AddRoom.fxml", event, "Add Rooms");
        } catch (IOException e) {
            System.out.println("Error loading add room screen: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelRoomBtnAction(ActionEvent event) {
        Room selected = roomsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {

            Room.getAllRooms().remove(selected);

            selected.getHotel().getRooms().remove(selected);

            roomsList.remove(selected);

            System.out.println("Room Removed");
        } else {
            showAlert("Selection Error", "No room selected for removal.");
        }
    }

    @FXML
    public void handleBackBtn(ActionEvent event) {
        try {
            navigateToScreen("Admin.fxml", event, "Admin Dashboard");
        } catch (IOException e) {
            System.out.println("Error loading admin screen: " + e.getMessage());
        }
    }
    private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Object controller = loader.getController();
        if (controller instanceof UserAwareController) {
            ((UserAwareController) controller).setUser(admin);
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
        private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
