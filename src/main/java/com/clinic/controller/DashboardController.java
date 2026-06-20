package com.clinic.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class DashboardController {
    @FXML private TableView<?> tableNextAppointments;

    private void loadView(String fxmlPath) {
        try {
            BorderPane pane = (BorderPane) tableNextAppointments.getScene().getRoot();
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            pane.setCenter(view);
        } catch (Exception e) { e.printStackTrace(); }
    }
    @FXML private void showHome() { loadView("/fxml/dashboard.fxml"); }
    @FXML private void showPatients() { loadView("/fxml/patient.fxml"); }
    @FXML private void showAppointments() { loadView("/fxml/appointment_view.fxml"); }
    @FXML private void handleLogout() throws Exception {
        tableNextAppointments.getScene().getWindow().hide();
    }
}