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

public class MyInfoController implements UserAwareController{
    @FXML
    private Button backbtn;
    @FXML
    private Label nameLabel10 ;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Label roleLabel;
    @FXML
    private Label departmentLabel;
    private Admin admin;
    public void handlebackbtn(ActionEvent event) {
        try {
            navigateToScreen("Admin.fxml", event, "Admin Dashboard");
        } catch (IOException e) {
            System.out.println("Error loading admin screen: " + e.getMessage());
        }
    }
    @Override
    public void setUser(User user) {
        this.admin = (Admin) user;
        updateAdmin();

    }
    public void updateAdmin() {
        nameLabel10.setText(admin.getName());
        phoneLabel.setText(admin.getPhoneNumber());
        username.setText(admin.getUserName());
        email.setText(admin.getEmail());
        roleLabel.setText(admin.getRole());
        departmentLabel.setText(admin.getDepartment());

    }
    private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        adminController controller = loader.getController();
        controller.setUser(admin);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
