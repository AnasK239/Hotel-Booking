package com.example.hotel;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AddBookingController implements UserAwareController{

    @FXML
    private ComboBox<Hotel> hotelComboBox;
    @FXML
    private ComboBox<Room> roomComboBox;
    @FXML
    private DatePicker checkInDatePicker;
    @FXML
    private DatePicker checkOutDatePicker;
    @FXML
    private Label statusLabel;
    @FXML
    private Button backbtn;
    @FXML
    private Button bookBtn;

    private Customer customer;

    @Override
    public void setUser(User user) {
        this.customer = (Customer) user;
        update();
    }

    @FXML
    public void update() {
        hotelComboBox.setItems(FXCollections.observableArrayList(Hotel.getAllHotels()));

        hotelComboBox.setOnAction(e -> {
            Hotel selectedHotel = hotelComboBox.getSelectionModel().getSelectedItem();
            if (selectedHotel != null) {
                List<Room> availableRooms = selectedHotel.getRooms().stream().filter(r -> !r.isBooked()).collect(Collectors.toList());
                roomComboBox.setItems(FXCollections.observableArrayList(availableRooms));
            }
        });
    }

    @FXML
    private void handleBookNow(ActionEvent event) throws IOException {
        Hotel hotel = hotelComboBox.getSelectionModel().getSelectedItem();
        Room room = roomComboBox.getSelectionModel().getSelectedItem();
        LocalDate checkIn = checkInDatePicker.getValue();
        LocalDate checkOut = checkOutDatePicker.getValue();

        if (hotel == null || room == null || checkIn == null || checkOut == null) {
            statusLabel.setText("Please fill in all fields.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())) {
            statusLabel.setText("Dates cannot be in the past.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (checkIn.isAfter(checkOut)) {
            statusLabel.setText("Check-in date cannot be after check-out date.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Date checkInDate = java.sql.Date.valueOf(checkIn);
        Date checkOutDate = java.sql.Date.valueOf(checkOut);

        customer.bookRoom(hotel, room, checkInDate, checkOutDate);
        room.setBooked(true);
        navigateToScreen("customer2.fxml", event, "Dashboard");
    }

    @FXML
    private void handleBackbtn(ActionEvent event) {
        try{
            navigateToScreen("customer2.fxml" , event , "Dashboard");
        }
        catch(Exception e){
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
