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

public class AddUserController {
      @FXML
    private CheckBox adminCheckBox;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField departmentField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField roleField;

    @FXML
    private TextField usernameField;

    @FXML
    void handleAdminToggle(ActionEvent event) {
        boolean isAdmin = adminCheckBox.isSelected();
        roleField.setDisable(!isAdmin);
        departmentField.setDisable(!isAdmin);
    }

    @FXML
    void handleBack(ActionEvent event) {
        try {
            navigateToScreen("ManageUsers.fxml", event, "Manage Users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCreate(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();

        if(username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            // Handle empty fields (e.g., show an error message)
            return;
        }
        if (User.checkUserRegistered(username)) {
            showAlert("Username is used", "Username is used choose another one");
            return;
        }
        if (adminCheckBox.isSelected()) {
            String role = roleField.getText();
            String department = departmentField.getText();
            new Admin(username, password, name, email, phoneNumber, role, department);
        } else {
            new Customer(username, password, name, email, phoneNumber);
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
