package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentController {

    @FXML
    private ImageView cardImageView;

    @FXML
    private RadioButton cardRadioButton;

    @FXML
    private ImageView walletImageView;

    @FXML
    private RadioButton paypalRadioButton;

    @FXML
    private ToggleGroup paymentMethodToggleGroup;

    @FXML
    private Button backbtn;

    @FXML
    public void initialize() {
        paymentMethodToggleGroup.selectedToggleProperty().addListener((observable) -> {


        });
    }


    @FXML
    private void handleBackbtn(ActionEvent event) {
        try{
            navigateToScreen("customer2.fxml" , event , "Dashboard");
        }
        catch(Exception e){

        }

    }

    private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}