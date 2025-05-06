package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public void onDoBack(ActionEvent event) throws IOException {
        navigateToScreen("welcome.fxml", event, "Hotel Booking System");
    }

    @FXML
    void onDoRegister(ActionEvent event) throws IOException {
        String name = regNameField.getText().trim();
        String username = regUsernameField.getText().trim();
        String email = regEmailField.getText().trim();
        String password = regPasswordField.getText().trim();
        String phoneText = regPhoneNumberField.getText().trim();

        // Check if any are empty
        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || phoneText.isEmpty()) {
            errorLabel.setText("Fields cannot be empty");
            errorLabel.setVisible(true);
        }
        else {
            try {
                long input = Long.parseLong(regPhoneNumberField.getText());
                if (!User.checkUserRegistered(regUsernameField.getText())){
                    User u = new Customer(regNameField.getText(), regUsernameField.getText(), regEmailField.getText(), regPasswordField.getText(), regPhoneNumberField.getText());
                    navigateToScreen("login.fxml", event, "Dashboard");
                }
                errorLabel.setText("Username already exists");
                errorLabel.setVisible(true);

            } catch (NumberFormatException e) {
                errorLabel.setText("Phone number must be a valid number");
                errorLabel.setVisible(true);
            }
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
