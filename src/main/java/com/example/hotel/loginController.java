package com.example.hotel;


import javafx.event.ActionEvent;
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
import java.util.List;

public class loginController {

    @FXML
    public Button backFromLoginBtn;

    @FXML 
    private PasswordField loginPasswordField;

    @FXML 
    private TextField loginVisiblePasswordField;

    @FXML 
    private CheckBox showPasswordCheckBox;

    @FXML
    private Button doLoginBtn;

    @FXML
    private StackPane loginPane;


    @FXML
    private TextField loginUsernameField;

    @FXML
    private Label errorLabel;


    @FXML
    public void onBackFromLogin(ActionEvent event) throws IOException {
        try
        {
            navigateToScreen("welcome.fxml", event, "Hotel Booking System");
        }
        catch (IOException e)
        {
            System.out.println("Error loading welcome screen: " + e.getMessage());
        }
    }

    public void onDoLogin(ActionEvent event) throws IOException {
        List<User> all = User.getAllUsers();
        String password = showPasswordCheckBox.isSelected()
        ? loginVisiblePasswordField.getText()
        : loginPasswordField.getText();
        for (User u : all){
            if (u.getUserName().equals(loginUsernameField.getText()) && u.getPassword().equals(password)) {
                if (u instanceof Customer) {
                    try {
                        navigateToScreenFwd("customer2.fxml", event, "Dashboard", u);
                    }
                    catch (IOException e) {
                        System.out.println("Error loading customer dashboard: " + e.getMessage());
                    }
                }
                else if (u instanceof Admin) {
                    try {
                        navigateToScreenFwd("Admin.fxml", event, "Dashboard", u);
                    }
                    catch (IOException e) {
                        System.out.println("Error loading Admin Screen"+ e.getMessage());
                    }
                }
            }
        }
        errorLabel.setVisible(true);
    }

    @FXML
    private void togglePasswordVisibility() {
        if (showPasswordCheckBox.isSelected()) {
            loginVisiblePasswordField.setText(loginPasswordField.getText());
            loginVisiblePasswordField.setVisible(true);
            loginVisiblePasswordField.setManaged(true);
            loginPasswordField.setVisible(false);
            loginPasswordField.setManaged(false);
        } else {
            loginPasswordField.setText(loginVisiblePasswordField.getText());
            loginPasswordField.setVisible(true);
            loginPasswordField.setManaged(true);
            loginVisiblePasswordField.setVisible(false);
            loginVisiblePasswordField.setManaged(false);
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

    private void navigateToScreenFwd(String fxmlFile, ActionEvent event, String title, User u) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Object controller = loader.getController();
        if (controller instanceof UserAwareController) {
            ((UserAwareController) controller).setUser(u);
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }


}
