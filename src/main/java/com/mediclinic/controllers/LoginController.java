package com.mediclinic.controllers;

import com.mediclinic.dao.UserDAO;
import com.mediclinic.models.User;
import com.mediclinic.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin(ActionEvent event) {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        User loggedUser = userDAO.login(user, pass);

        if (loggedUser != null) {
            System.out.println("login reussi " + loggedUser.getNomComplet());
            if (loggedUser.getRole().equals("RECEPTIONNISTE")) {
                SceneSwitcher.navigate(event, "dashboard_reception.fxml", "MediClinic - Réception");
            } else {
                SceneSwitcher.navigate(event, "dashboard_medecin.fxml", "MediClinic - Médecin");
            }
        } else {
            errorLabel.setText("Utilisateur ou mot de passe incorrect !");
        }
    }
}