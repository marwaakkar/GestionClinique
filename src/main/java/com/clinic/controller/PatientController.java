package com.clinic.controller;
import com.clinic.model.Patient;
import com.clinic.service.PatientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PatientController {
    @FXML private TextField txtNom, txtPrenom, txtTelephone, txtEmail;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, Long> colId;
    @FXML private TableColumn<Patient, String> colNom, colPrenom, colTelephone, colEmail;

    private final PatientService service = new PatientService();
    private final ObservableList<Patient> list = FXCollections.observableArrayList();

    @FXML public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        chargerPatients();
    }

    private void chargerPatients() { list.clear(); list.addAll(service.recupererTousLesPatients()); tablePatients.setItems(list); }

    @FXML private void handleAjouterPatient() {
        service.enregistrerPatient(new Patient(txtNom.getText(), txtPrenom.getText(), txtTelephone.getText(), txtEmail.getText()));
        chargerPatients(); handleClearForm();
    }

    @FXML private void handleModifierPatient() {
        Patient s = tablePatients.getSelectionModel().getSelectedItem();
        if (s != null) {
            s.setNom(txtNom.getText()); s.setPrenom(txtPrenom.getText());
            service.mettreAJourPatient(s); chargerPatients();
        }
    }

    @FXML private void handleSupprimerPatient() {
        Patient s = tablePatients.getSelectionModel().getSelectedItem();
        if (s != null) { service.supprimerPatient(s.getId()); chargerPatients(); }
    }

    @FXML private void handleClearForm() { txtNom.clear(); txtPrenom.clear(); txtTelephone.clear(); txtEmail.clear(); }
    @FXML private void handleOuvrirDossier() {}
}