package com.mycompany.pbo2tgs1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;


import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

     @Override
    public void start(Stage primaryStage) {
        try {
            // Inisialisasi Root Layout
            BorderPane root = new BorderPane();

            // Tampilkan Halaman LoginView terlebih dahulu
            LoginView loginView = new LoginView(primaryStage);
            root.setCenter(loginView.getView());

            // Buat Scene
            Scene scene = new Scene(root, 800, 600);

            // Atur Stage
            primaryStage.setTitle("Todo App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
