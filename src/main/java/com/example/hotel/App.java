package com.example.hotel;

import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(String[] args) {

        new Admin("Admin", "admin", "Admin@test.test", "admin", "0123456789", "Front Desk", "test");
        new Customer("Alice", "Alice1", "Alice21@test.test", "Alice1234", "0123456789");
        Hotel h1 = new Hotel("Hotel1", "Location1", 5, "Description1", "1234567890", "adsa@test.test", "www.hotel1.com");
        Hotel h2 = new Hotel("Hotel2", "Location2", 4, "Description2", "1234567890", "adsa@test.test", "www.hotel2.com");
        new StandardRoom(0, 10, false, h1, "King", true, "jg");
        new LuxuryRoom(1, 20, false, h2, "Hotel1", true, true);
        new LuxuryRoom(1, 25, false,  h1, "Hotel1", true, true);
        new LuxuryRoom(1, 15, false,  h1, "Hotel1", true, true);

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
