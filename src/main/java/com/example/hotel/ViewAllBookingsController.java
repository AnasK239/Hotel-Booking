package com.example.hotel;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class ViewAllBookingsController implements UserAwareController{
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
    private Admin admin;

    @Override
    public void setUser(User user) {
        this.admin = (Admin) user;
    }

    public void initialize() {
    // Optional Booking ID if needed
    bookingIdColumn.setCellValueFactory(cellData -> 
        new SimpleStringProperty(String.valueOf(Booking.getAllBookings().indexOf(cellData.getValue()) + 1)));

    userColumn.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getCustomer().getName()));

    roomColumn.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getRoom().getDescription()));

    dateColumn.setCellValueFactory(cellData -> {
        Booking b = cellData.getValue();
        String range = formatDate(b.getCheckInDate()) + " to " + formatDate(b.getCheckOutDate());
        return new SimpleStringProperty(range);
    });

    bookingsTable.setItems(FXCollections.observableArrayList(Booking.getAllBookings()));
}
    @FXML
    public void handlebackbtn(ActionEvent event) {
        try {
            navigateToScreen("Admin.fxml", event, "Admin Dashboard");
        } catch (IOException e) {

        }
    }
    private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        adminController controller = loader.getController();
        controller.setUser(admin);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private String formatDate(Date date) {
        return new java.text.SimpleDateFormat("dd-MM-yyyy").format(date);
    }
}
