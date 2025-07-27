/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {
    private Stage primaryStage;
    private UserOperations userOperations;
    
    public RegisterView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public VBox getView() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

       // Elemen Register
Label titleLabel = new Label("Register");
titleLabel.setStyle(
    "-fx-font-size: 28px; " +
    "-fx-font-weight: bold; " +
    "-fx-text-fill: #9C27B0;" 
);

TextField usernameField = new TextField();
usernameField.setPromptText("Username");
usernameField.setStyle(
    "-fx-pref-width: 300px; " +
    "-fx-padding: 10px; " +
    "-fx-border-color: #9C27B0; " +
    "-fx-border-radius: 8px; " +
    "-fx-background-radius: 8px;"
);

PasswordField passwordField = new PasswordField();
passwordField.setPromptText("Password");
passwordField.setStyle(
    "-fx-pref-width: 300px; " +
    "-fx-padding: 10px; " +
    "-fx-border-color: #9C27B0; " +
    "-fx-border-radius: 8px; " +
    "-fx-background-radius: 8px;"
);

ComboBox<String> roleComboBox = new ComboBox<>();
ObservableList<String> roles = FXCollections.observableArrayList("User", "Admin");
roleComboBox.setItems(roles);
roleComboBox.setValue("User");
roleComboBox.setStyle(
    "-fx-pref-width: 300px; " +
    "-fx-padding: 10px; " +
    "-fx-border-color: #9C27B0; " +
    "-fx-border-radius: 8px; " +
    "-fx-background-radius: 8px; " +
    "-fx-background-color: #F3E5F5;" 
);

Button registerButton = new Button("Register");
registerButton.setStyle(
    "-fx-background-color: #9C27B0; " +
    "-fx-text-fill: white; " +
    "-fx-font-weight: bold; " +
    "-fx-background-radius: 20px; " +
    "-fx-padding: 10px 30px;"
);
registerButton.setOnMouseEntered(e -> registerButton.setStyle(
    "-fx-background-color: #BA68C8; " + 
    "-fx-text-fill: white; " +
    "-fx-font-weight: bold; " +
    "-fx-background-radius: 20px; " +
    "-fx-padding: 10px 30px;"
));
registerButton.setOnMouseExited(e -> registerButton.setStyle(
    "-fx-background-color: #9C27B0; " +
    "-fx-text-fill: white; " +
    "-fx-font-weight: bold; " +
    "-fx-background-radius: 20px; " +
    "-fx-padding: 10px 30px;"
));

Label loginLink = new Label("Sudah punya akun? Login di sini.");
loginLink.setStyle("-fx-text-fill: #9C27B0; -fx-underline: true; -fx-cursor: hand;");
loginLink.setOnMouseEntered(e -> loginLink.setStyle("-fx-text-fill: #BA68C8; -fx-underline: true; -fx-cursor: hand;"));
loginLink.setOnMouseExited(e -> loginLink.setStyle("-fx-text-fill: #9C27B0; -fx-underline: true; -fx-cursor: hand;"));
loginLink.setOnMouseClicked(event -> {
    // Arahkan ke LoginView
    LoginView loginView = new LoginView(primaryStage);
    Scene loginScene = new Scene(loginView.getView(), 800, 600);
    primaryStage.setScene(loginScene);
});


        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleComboBox.getValue();

            try {
                userOperations = new UserOperations();
            } catch (SQLException ex) {
                Logger.getLogger(RegisterView.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (userOperations.registerUser(username, password, role)) {
                showSuccess("Register berhasil! Silakan login.");
                // Arahkan ke LoginView
                LoginView loginView = new LoginView(primaryStage);
                primaryStage.setScene(new Scene(loginView.getView(), 800, 600));
            } else {
                showError("Register gagal! Username mungkin sudah digunakan.");
            }
        });

        root.getChildren().addAll(titleLabel, usernameField, passwordField, roleComboBox, registerButton, loginLink);
        return root;
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}