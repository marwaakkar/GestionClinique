package com.clinic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/patient_search.fxml")
            );

            Scene scene = new Scene(loader.load());

            scene.getStylesheets().add(
                    getClass().getResource("/styles/main.css")
                            .toExternalForm()
            );

            primaryStage.setTitle("MediClinic - Gestion de Clinique");
            primaryStage.setScene(scene);

            primaryStage.setWidth(1000);
            primaryStage.setHeight(700);

            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);

            primaryStage.centerOnScreen();
            primaryStage.show();

        } catch (Exception e) {

            System.err.println("Erreur lors du démarrage de l'application");
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}