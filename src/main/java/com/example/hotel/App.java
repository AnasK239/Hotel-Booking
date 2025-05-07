package com.example.hotel;

import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(String[] args) {

        User u1 = new Admin("ana1", "ad", "Admin", "ad", "0123456789", "bitching", "bitch");
        User u2 = new Admin("ana2", "admin2", "Admin", "admin", "0123456789", "bitching", "bitch");
        User u3 = new Admin("ana3", "admin3", "Admin", "admin", "0123456789", "bitching", "bitch");
        Customer u4 = new Customer("aa", "aa", "Admin", "aa", "0123456789");
        Hotel h1 = new Hotel("Hotel1", "Location1", 5, "Description1", "1234567890", "adsa", "www.hotel1.com");
        Hotel h2 = new Hotel("Hotel2", "Location2", 4, "Description2", "1234567890", "adsa", "www.hotel2.com");
        Room r1 = new StandardRoom(0, 0, false, new Date(2023-1900, 10, 1), h1, "King", true, "");
        Room r2 = new LuxuryRoom(1, 0, false, new Date(2023-1900, 10, 1), h1, "Hotel1", true, true);
        Date checkInDate = new Date(2023-1900, 10, 1);
        Date checkInDate2 = new Date(2024-1900, 10, 1);
        Date checkOutDate = new Date(2023-1900, 10, 5);
        Date checkOutDate2 = new Date(2025-1900, 10, 5);
        Booking b1 = new Booking(u4, h1, r1, checkInDate, checkOutDate);
        Booking b2 = new Booking(u4, h1, r1, checkInDate2, checkOutDate2);
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("welcome.fxml")
        );
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Hotel Booking System");
        primaryStage.show();
    }


}
