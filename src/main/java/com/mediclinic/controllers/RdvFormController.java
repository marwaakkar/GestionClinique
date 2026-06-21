package com.mediclinic.controllers;

import com.mediclinic.dao.RendezVousDAO;
import com.mediclinic.dao.UserDAO;
import com.mediclinic.models.Patient;
import com.mediclinic.models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class RdvFormController {

    @FXML private Label patientLabel;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> heureBox;
    @FXML private ComboBox<User> medecinBox;

    private Patient selectedPatient;
    private RendezVousDAO rdvDAO = new RendezVousDAO();
    private UserDAO userDAO = new UserDAO();

    public void setPatient(Patient patient) {
        this.selectedPatient = patient;
        patientLabel.setText("Patient : " + patient.getNom() + " " + patient.getPrenom());
    }

    @FXML
    public void initialize() {
        heureBox.setItems(FXCollections.observableArrayList("09:00", "09:30", "10:00", "10:30", "11:00", "15:00", "16:00"));
        medecinBox.setItems(FXCollections.observableArrayList(userDAO.getAllMedecins()));
        medecinBox.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                return (user == null) ? "" : "Dr. " + user.getNomComplet();
            }
            @Override
            public User fromString(String string) { return null; }
        });
    }

    @FXML
    private void handleSave() {
        User medecinSelected = medecinBox.getValue();

        if (datePicker.getValue() == null || heureBox.getValue() == null || medecinSelected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs !");
            alert.show();
            return;
        }

        boolean success = rdvDAO.createRdvWithMedecin(
                selectedPatient.getId(),
                medecinSelected.getId(),
                datePicker.getValue(),
                heureBox.getValue(),
                "EN_ATTENTE"
        );

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rendez-vous enregistré avec succès !");
            alert.showAndWait();
            ((Stage) datePicker.getScene().getWindow()).close();
        } else {
            new Alert(Alert.AlertType.ERROR, "Erreur lors de l'enregistrement.").show();
        }
    }

    @FXML private void handleCancel() {
        ((Stage) datePicker.getScene().getWindow()).close();
    }
}