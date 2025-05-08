package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class ManageUsersController {
    @FXML
    private Button backbtn;

    @FXML
    private Button removeUser;

    @FXML
    private Button updateUser;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> roleColumn;

    @FXML
    private TableColumn<User, String> usernameColumn;

    
    @FXML
    private Button addUser;
    
    @FXML
    private ObservableList<User> usersList;

    public void initialize() {
        // Set up cell value factories
        emailColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEmail()));

        roleColumn.setCellValueFactory(cellData -> {
            User user = cellData.getValue();
            String role = (user instanceof Admin) ? ((Admin)user).getRole() : "Customer";
            return new SimpleStringProperty(role);
        });

        usernameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUserName()));

        refreshUsersList();
    }

    private void refreshUsersList() {
        usersList = FXCollections.observableArrayList(User.getAllUsers());
        usersTable.setItems(usersList);
    }

    @FXML
    public void handlebackbtn(ActionEvent event) {
        try {
            navigateToScreen("Admin.fxml", event, "Login");
        } catch (IOException e) {
            showAlert("Navigation Error", "Could not navigate to Admin screen: " + e.getMessage());
        }
    }



    @FXML
    void handleUpdateUser(ActionEvent event) {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {

                // You might want to pass the selected user to the update screen

                // controller for userUpdate.fxml that accepts a user
                // UserUpdateController controller = loader.getController();
                // controller.setUser(selected);
                navigateToScreen("userUpdate.fxml", event, "UpdateUser");

            } catch (IOException e) {
                showAlert("Navigation Error", "Could not navigate to Update User screen: " + e.getMessage());
            }
        } else {
            showAlert("Selection Error", "No user selected for update.");
        }
    }

    @FXML
    void handleRemoveUser(ActionEvent event) {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Remove from the data model
            User.removeUser(selected);

            // Remove from the ObservableList to update the UI
            usersList.remove(selected);

            System.out.println("User removed: " + selected.getUserName());
        } else {
            showAlert("Selection Error", "No user selected for removal.");
        }
    }

    
    @FXML
    void handleAddUser(ActionEvent event) {
        try {

            // You might want to pass the selected user to the update screen

            // controller for userUpdate.fxml that accepts a user
            // UserUpdateController controller = loader.getController();
            // controller.setUser(selected);
            navigateToScreen("AddUser.fxml", event, "Add User");

        } catch (IOException e) {
            showAlert("Navigation Error", "Could not navigate to Update User screen: " + e.getMessage());
        }
    }


    // private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
    //     Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
    //     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    //     Scene scene = new Scene(root);
    //     stage.setTitle(title);
    //     stage.setScene(scene);
    //     stage.show();
    // }
    private void navigateToScreen(String fxmlFile, ActionEvent event, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Object controller = loader.getController();
        if (controller instanceof UserAwareController) {
            ((UserAwareController) controller).setUser( usersTable.getSelectionModel().getSelectedItem());
        }

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}