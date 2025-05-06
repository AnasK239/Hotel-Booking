package com.example.hotel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class customerController {
    @FXML
    private Button viewRoomsButton;

    @FXML
    private Button addBookingButton;

    @FXML
    private Button viewHistoryButton;

    @FXML
    private Button managePaymentButton;

    @FXML
    private Button logOutButton;


    @FXML
    private void initialize() {

        System.out.println("Customer Dashboard initialized");

        setupButtonHoverEffects();
    }

    private void setupButtonHoverEffects() {

        String originalStyle = "-fx-background-color:#1F3B73; -fx-background-radius: 5;";

        String hoverStyle = "-fx-background-color:#0A1A3F;  -fx-cursor: hand;";

        setButtonHoverEffect(viewRoomsButton, originalStyle, hoverStyle);
        setButtonHoverEffect(addBookingButton, originalStyle, hoverStyle);
        setButtonHoverEffect(viewHistoryButton, originalStyle, hoverStyle);
        setButtonHoverEffect(managePaymentButton, originalStyle, hoverStyle);
        setButtonHoverEffect(logOutButton, originalStyle, hoverStyle);
    }

    private void setButtonHoverEffect(Button button, String originalStyle, String hoverStyle) {
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(originalStyle));
    }


    @FXML
    private void handleViewRooms(ActionEvent event) {
        System.out.println("View Available Rooms clicked");
        try {
            navigateToScreen("AvailableRooms.fxml", event, "Available Rooms");
        } catch (IOException e) {

        }
    }


    @FXML
    private void handleAddBooking(ActionEvent event) {
        System.out.println("Add Booking clicked");
        try {
            navigateToScreen("NewBooking.fxml", event, "Create Booking");
        } catch (IOException e) {

        }
    }

    @FXML
    private void handleViewHistory(ActionEvent event) {
        System.out.println("View Booking History clicked");
        try {
            navigateToScreen("BookingHistory.fxml", event, "Booking History");
        } catch (IOException e) {

        }
    }

    @FXML
    private void handleManagePayment(ActionEvent event) {
        System.out.println("Manage Payment Methods clicked");
        try {
            navigateToScreen("PaymentMethods.fxml", event, "Payment Methods");
        } catch (IOException e) {

        }
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
        try {
            navigateToScreen("login.fxml", event, "Login");
        } catch (IOException e) {

        }
    }

    private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
