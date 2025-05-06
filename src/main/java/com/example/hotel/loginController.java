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
import java.util.List;

public class loginController {

    @FXML
    public Button backFromLoginBtn;

    @FXML
    private Button doLoginBtn;

    @FXML
    private StackPane loginPane;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private TextField loginUsernameField;

    @FXML
    private Label errorLabel;


    @FXML
    public void onBackFromLogin(ActionEvent event) throws IOException {

        navigateToScreen("welcome.fxml", event, "Hotel Booking System");
    }

    public void onDoLogin(ActionEvent event) throws IOException {
        List<User> all = User.getAllUsers();
        for (User u : all){
            if (u.getUserName().equals(loginUsernameField.getText()) && u.getPassword().equals(loginPasswordField.getText())) {
                if (u instanceof Customer) {
                    navigateToScreen("customer2.fxml", event, "Dashboard");
                }
                else if (u instanceof Admin) {
                    navigateToScreen("Admin.fxml", event, "Dashboard");
                }
            }
        }
        errorLabel.setVisible(true);


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
