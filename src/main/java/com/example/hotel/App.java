package com.example.hotel;

import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(String[] args) {

        User u1 = new Admin("ana1", "ad", "Admin", "ad", "0123456789", "Front Desk", "test");
        User u2 = new Admin("ana2", "admin2", "Admin", "admin", "0123456789", "Back Desk", "test");
        User u3 = new Admin("ana3", "admin3", "Admin", "admin", "0123456789", "Back Desk", "test");
        Customer u4 = new Customer("aa", "aa", "customer", "aa", "0123456789");
        Hotel h1 = new Hotel("Hotel1", "Location1", 5, "Description1", "1234567890", "adsa", "www.hotel1.com");
        Hotel h2 = new Hotel("Hotel2", "Location2", 4, "Description2", "1234567890", "adsa", "www.hotel2.com");
        Room r1 = new StandardRoom(0, 10, false, h1, "King", true, "");
        Room r2 = new LuxuryRoom(1, 20, false, h2, "Hotel1", true, true);
        Room r3 = new LuxuryRoom(1, 25, false,  h1, "Hotel1", true, true);

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Hotel Booking System");
        primaryStage.show();
    }


}
