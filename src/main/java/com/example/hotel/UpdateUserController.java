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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateUserController implements UserAwareController {
    

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
    private TextField VisiblePasswordField;

    
    @FXML
    private CheckBox showPasswordCheckBox;
    private Admin admin;
    private User user;

    @FXML
    void handleBack(ActionEvent event) {
        try {
            navigateToScreen("ManageUsers.fxml", event, "Manage Users");
        } catch (IOException e) {
            System.out.println("Error loading manage users screen: " + e.getMessage());
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
        String password = showPasswordCheckBox.isSelected()
        ? VisiblePasswordField.getText()
        : passwordField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        if(username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return;
        }
        // Check if the username is numbers only
        if (username.matches("\\d+") || email.matches("\\d+") || name.matches("\\d+")) {
            showAlert("Invalid field", "fields cannot be numbers only.");
            return;
        }
        // Check if the username first char is number
        if (Character.isDigit(username.charAt(0)) || Character.isDigit(email.charAt(0)) || Character.isDigit(name.charAt(0))) {
            showAlert("Invalid field", "field cannot start with a number.");
            return;
        }
        // check if password is less than 8 characters
        if (password.length() < 8) {
            showAlert("Invalid Password", "Password must be at least 8 characters long.");
            return;
        }
        //check if valid email
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            showAlert("Invalid Email", "Email is not valid.");
            return;
        }
        
        if (user != null) {
            user.updateProfile(name, username, email, password, phoneNumber);
        }

        try {
            navigateToScreen("ManageUsers.fxml", event, "Manage Users");
        } catch (IOException e) {
            System.out.println("Error loading manage users screen: " + e.getMessage());
        }
    }

    @FXML
    void togglePasswordVisibility(ActionEvent event) {
        if (showPasswordCheckBox.isSelected()) {
            VisiblePasswordField.setText(passwordField.getText());
            VisiblePasswordField.setVisible(true);
            VisiblePasswordField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
        } else {
            passwordField.setText(VisiblePasswordField.getText());
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            VisiblePasswordField.setVisible(false);
            VisiblePasswordField.setManaged(false);
        }
    }

    private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        ManageUsersController controller= loader.getController();
        controller.setUser(admin);
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

    public void setAdmin(Admin admin) {
        this.admin = admin;
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
