package com.example.hotel;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateUserController implements UserAwareController {
    
    private User user;

    @FXML
    private Button backButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button updateButton;

    @FXML
    private TextField usernameField;

    @FXML
    void handleBack(ActionEvent event) {
        try {
            navigateToScreen("ManageUsers.fxml", event, "Manage Users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialize method to set the fields with the current user data
    public void update() {
        if (user != null) {
            usernameField.setText(user.getUserName());
            passwordField.setText(user.getPassword());
            nameField.setText(user.getName());
            emailField.setText(user.getEmail());
            phoneNumberField.setText(user.getPhoneNumber());
        }
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        if(username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return;
        }
        if (user != null) {
            user.updateUserName(username);
            user.updatePassword(password);
            user.updateName(name);
            user.updateEmail(email);
            user.updatePhoneNumber(phoneNumber);
        }

        try {
            navigateToScreen("ManageUsers.fxml", event, "Manage Users");
        } catch (IOException e) {
            e.printStackTrace();
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

    @Override
    public void setUser(User user) {
        this.user = user;
        update();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
