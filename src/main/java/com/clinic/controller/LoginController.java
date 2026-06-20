package com.clinic.controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> comboRole;
    @FXML private Label lblError;

    @FXML public void initialize() { comboRole.setItems(FXCollections.observableArrayList("Réceptionniste", "Médecin")); }

    @FXML private void handleLogin() throws Exception {
        if (txtPassword.getText().equals("admin123")) {
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
            stage.setScene(new Scene(root, 1024, 768));
        } else { lblError.setText("Erreur d'authentification."); }
    }
}