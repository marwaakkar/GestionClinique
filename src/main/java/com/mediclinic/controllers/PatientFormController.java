package com.mediclinic.controllers;

import com.mediclinic.dao.PatientDAO;
import com.mediclinic.models.Patient;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class PatientFormController {
    @FXML private TextField nomField, prenomField, cinField, telField;
    @FXML private ComboBox<String> mutuelleBox;
    @FXML private DatePicker dateNaissPicker;

    private PatientDAO patientDAO = new PatientDAO();

    @FXML
    public void initialize() {
        mutuelleBox.setItems(FXCollections.observableArrayList("Aucune", "CNSS", "CNOPS", "RAMED", "Assurance Privée"));
        mutuelleBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleSave() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String cin = cinField.getText();
        String tel = telField.getText();
        String mutuelle = mutuelleBox.getValue();
        LocalDate dateNaiss = dateNaissPicker.getValue();

        if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || dateNaiss == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir les champs obligatoires !");
            alert.show();
            return;
        }

        Patient p = new Patient(0, nom, prenom, cin, tel, mutuelle, dateNaiss);

        if (patientDAO.addPatient(p)) {
            System.out.println("Patient ajouté avec succès !");
            closeWindow();
        } else {
            new Alert(Alert.AlertType.ERROR, "Erreur lors de l'ajout du patient.").show();
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }
}