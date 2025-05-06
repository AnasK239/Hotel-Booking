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

    private Customer customer;

    @Override
    public void setUser(User user) {
        this.customer = (Customer) user;
        updateTable();
    }

    @FXML
    public void updateTable() {
        bookingsTable.setItems(FXCollections.observableArrayList(customer.viewBookingsHistory()));

        bookingIdColumn.setCellValueFactory(cellData ->
                        new SimpleStringProperty(String.valueOf(customer.viewBookingsHistory().indexOf(cellData.getValue()) + 1)));

        userColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCustomer().getName()));

        roomColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getRoom().getDescription()));

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
}
