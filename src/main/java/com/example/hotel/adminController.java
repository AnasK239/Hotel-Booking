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

public class adminController implements UserAwareController {
    @FXML
    private Button manageRoomsButton;

    @FXML
    private Button manageUsersButton;

    @FXML
    private Button viewBookingsButton;

    @FXML
    private Button myInfoButton;

    @FXML
    private Button logOutButton;
    private Admin admin;

    @Override
    public void setUser(User user) {
        this.admin = (Admin) user;
    }


    @FXML
    private void initialize() {

        System.out.println("Admin Dashboard initialized");

        setupButtonHoverEffects();
    }

    private void setupButtonHoverEffects() {

        String originalStyle = "-fx-background-color:#1F3B73; -fx-background-radius: 5;";

        String hoverStyle = "-fx-background-color:#0A1A3F;  -fx-cursor: hand;";

        setButtonHoverEffect(manageRoomsButton, originalStyle, hoverStyle);
        setButtonHoverEffect(manageUsersButton, originalStyle, hoverStyle);
        setButtonHoverEffect(viewBookingsButton, originalStyle, hoverStyle);
        setButtonHoverEffect(myInfoButton, originalStyle, hoverStyle);
        setButtonHoverEffect(logOutButton, originalStyle, hoverStyle);
    }

    private void setButtonHoverEffect(Button button, String originalStyle, String hoverStyle) {
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(originalStyle));
    }


    @FXML
    private void handlemanageRooms(ActionEvent event) {
        System.out.println("Manage Rooms clicked");
        try {
            navigateToScreen("ManageRooms.fxml", event, "Manage Rooms");
        } catch (IOException e) {
            System.out.println("Error loading Manage Rooms screen: " + e.getMessage());
        }
    }


    @FXML
    private void handlemanageUsers(ActionEvent event) {
        System.out.println("Manage Users clicked");
        try {
            navigateToScreen("ManageUsers.fxml", event, "Manage Users");
        } catch (IOException e) {
            System.out.println("Error loading Manage Users screen: " + e.getMessage());
        }
    }

    @FXML
    private void handleViewBookings(ActionEvent event) {
        System.out.println("View All Bookings clicked");
        try {
            navigateToScreen("ViewAllBookings.fxml", event, "All Bookings");
        } catch (IOException e) {
            System.out.println("Error loading View All Bookings screen: " + e.getMessage());
        }
    }

    @FXML
    private void handlemyInfo(ActionEvent event) {
        System.out.println("My Info clicked");
        try {
            navigateToScreen("MyInfo.fxml", event, "My Info");
        } catch (IOException e) {
            System.out.println("Error loading My Info screen: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
        try {
            navigateToScreen("login.fxml", event, "Login");
        } catch (IOException e) {
            System.out.println("Error loading login screen: " + e.getMessage());
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

}
