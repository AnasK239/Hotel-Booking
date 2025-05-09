package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class RegisterController {

    @FXML
    private Button backFromRegisterBtn;

    @FXML
    private Button doRegisterBtn;

    @FXML
    private TextField regEmailField;

    @FXML
    private TextField regNameField;

    @FXML
    private PasswordField regPasswordField;

    @FXML
    private TextField regUsernameField;

    @FXML
    private TextField regPhoneNumberField;

    @FXML
    private StackPane registerPane;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField VisiblePasswordField;

    
    @FXML
    private CheckBox showPasswordCheckBox;


    @FXML
    public void onDoBack(ActionEvent event) throws IOException {
        navigateToScreen("welcome.fxml", event, "Hotel Booking System");
    }

    @FXML
    void onDoRegister(ActionEvent event) throws IOException {
        String name = regNameField.getText().trim();
        String username = regUsernameField.getText().trim();
        String email = regEmailField.getText().trim();
        String password =  showPasswordCheckBox.isSelected()
        ? VisiblePasswordField.getText()
        : regPasswordField.getText();
        String phoneText = regPhoneNumberField.getText().trim();

        // Check if any are empty
        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || phoneText.isEmpty()) {
            errorLabel.setText("Fields cannot be empty");
            errorLabel.setVisible(true);
            return;
        }
        if (User.checkUserRegistered(username)) {
            errorLabel.setText("Username is used choose another one");
            errorLabel.setVisible(true);
            return;
        }
        // Check if the username is numbers only
        if (username.matches("\\d+") || email.matches("\\d+") || name.matches("\\d+")) {
            errorLabel.setText("fields cannot be numbers only.");
            errorLabel.setVisible(true);
            return;
        }
        // Check if the username first char is number
        if (Character.isDigit(username.charAt(0)) || Character.isDigit(email.charAt(0)) || Character.isDigit(name.charAt(0))) {
            errorLabel.setText("fields cannot start with a number.");
            errorLabel.setVisible(true);
            return;
        }
        // check if password is less than 8 characters
        if (password.length() < 8) {
            errorLabel.setText("Password must be at least 8 characters long.");
            errorLabel.setVisible(true);
            return;
        }
        //check if valid email
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            errorLabel.setText("Email is not valid.");
            errorLabel.setVisible(true);
            return;
        }
        
        try {
            long input = Long.parseLong(phoneText);
            if (!User.checkUserRegistered(regUsernameField.getText())){
                User u = new Customer(name, username, email, password, phoneText);
                navigateToScreen("login.fxml", event, "Login");
            }
            errorLabel.setText("Username already exists");
            errorLabel.setVisible(true);
        } catch (NumberFormatException e) {
            errorLabel.setText("Phone number must be a valid number");
            errorLabel.setVisible(true);
        }

    }

    @FXML
    void togglePasswordVisibility(ActionEvent event) {
        if (showPasswordCheckBox.isSelected()) {
            VisiblePasswordField.setText(regPasswordField.getText());
            VisiblePasswordField.setVisible(true);
            VisiblePasswordField.setManaged(true);
            regPasswordField.setVisible(false);
            regPasswordField.setManaged(false);
        } else {
            regPasswordField.setText(VisiblePasswordField.getText());
            regPasswordField.setVisible(true);
            regPasswordField.setManaged(true);
            VisiblePasswordField.setVisible(false);
            VisiblePasswordField.setManaged(false);
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
