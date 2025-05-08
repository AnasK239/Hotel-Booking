package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInfoController implements UserAwareController{
    @FXML
    private Button backbtn1;
    @FXML
    private Label nameLabel1 ;
    @FXML
    private Label phoneLabel1;
    @FXML
    private Label username1;
    @FXML
    private Label email1;
    private Customer customer;



    @Override
    public void setUser(User user) {
        this.customer = (Customer) user;
        updateuser();

    }
    public void updateuser() {
        nameLabel1.setText(customer.getName());
        phoneLabel1.setText(customer.getPhoneNumber());
        username1.setText(customer.getUserName());
        email1.setText(customer.getEmail());
    }

    public void handlebackbtn1(ActionEvent event) {
        try {
            navigateToScreen("customer2.fxml", event, "Dashboard");
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
}

