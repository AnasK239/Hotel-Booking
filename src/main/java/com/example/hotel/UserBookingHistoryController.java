package com.example.hotel;

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
import java.util.Date;
import java.util.List;

public class UserBookingHistoryController implements UserAwareController{
    @FXML
    private Button backbtn;

    @FXML
    private TableView<Booking> bookingsTable;

    @FXML
    private TableColumn<Booking, String> bookingIdColumn;

    @FXML
    private TableColumn<Booking, String> dateColumn;

    @FXML
    private TableColumn<Booking, String> roomColumn;

    @FXML
    private TableColumn<Booking, String> userColumn;

    @FXML
    private TableColumn<Booking, String> hotelColumn;

    @FXML
    private Button removeBookingBtn;

    private Customer customer;

    @Override
    public void setUser(User user) {
        this.customer = (Customer) user;
        updateTable();
    }

    @FXML
    public void updateTable() {
        List<Booking> history = customer.viewBookingsHistory();
        ObservableList<Booking> observableHistory = FXCollections.observableArrayList(history);
        bookingsTable.setItems(observableHistory);

        bookingIdColumn.setCellValueFactory(cellData ->
            new SimpleStringProperty(String.valueOf(history.indexOf(cellData.getValue()) + 1)));

        userColumn.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getCustomer().getName()));

        roomColumn.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getRoom().getClass().getSimpleName()));

        hotelColumn.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getHotel().getName()));

        dateColumn.setCellValueFactory(cellData -> {
            Booking b = cellData.getValue();
            String range = formatDate(b.getCheckInDate()) + " to " + formatDate(b.getCheckOutDate());
            return new SimpleStringProperty(range);
        });
    }   

    @FXML
    public void handlebackbtn(ActionEvent event) {
        try {
            navigateToScreen("customer2.fxml", event, "Login");
        } catch (IOException e) {
            System.out.println("Error loading customer screen: " + e.getMessage());
        }
    }

    @FXML
    void handleRemoveBooking(ActionEvent event) {
        Booking selectedBooking = bookingsTable.getSelectionModel().getSelectedItem();
        if (selectedBooking == null) {
            showAlert("Error", "No booking selected");
            return;
        }
        Date checkInDate = selectedBooking.getCheckInDate();
        Date checkOutDate = selectedBooking.getCheckOutDate();
        if (checkInDate.before(new Date()) || checkOutDate.before(new Date())) {
            showAlert("Error", "Cannot remove past or current bookings");
            return;
        }
        if (selectedBooking != null) {
            customer.viewBookingsHistory().remove(selectedBooking);
            Booking.getAllBookings().remove(selectedBooking);
            int days = (int) (checkOutDate.getTime() - checkInDate.getTime()); 
            days /= (1000 * 60 * 60 * 24); // Convert milliseconds to days
            days += 1; 
            Room room = selectedBooking.getRoom();
            int pointsPerDay;
            if (room instanceof LuxuryRoom) {
                pointsPerDay = 5;
            } else if (room instanceof UniqueRoom) {
                pointsPerDay = 3;
            } else {
                pointsPerDay = 2;
            }
            int totalPoints = days * pointsPerDay;
            customer.removeLoyaltyPoints(totalPoints);
            room.setBooked(false);
            updateTable();
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

    private String formatDate(Date date) {
        return new java.text.SimpleDateFormat("dd-MM-yyyy").format(date);
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
