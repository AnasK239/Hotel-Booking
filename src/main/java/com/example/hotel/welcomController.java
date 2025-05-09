package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class welcomController {

    @FXML
    private Button welcomeLoginBtn;

    @FXML
    private StackPane welcomePane;

    @FXML
    private Button welcomeRegisterBtn;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("register.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("Error loading register screen: " + e.getMessage());
        }
    }

    @FXML

    public void switchToScene2(ActionEvent event) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("Error loading login screen: " + e.getMessage());
        }
    }

}
