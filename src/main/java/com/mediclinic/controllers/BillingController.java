package com.mediclinic.controllers;

import com.mediclinic.dao.FactureDAO;
import com.mediclinic.models.Facture;
import com.mediclinic.models.Patient;
import com.mediclinic.utils.PDFGenerator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class BillingController {
    @FXML private Label patientLabel;
    @FXML private TextField amountField;
    @FXML private ComboBox<String> modeBox;

    private Patient selectedPatient;
    private FactureDAO factureDAO = new FactureDAO();

    public void setPatient(Patient patient) {
        this.selectedPatient = patient;
        patientLabel.setText(patient.getNom() + " " + patient.getPrenom());
        modeBox.setItems(FXCollections.observableArrayList("ESPECES", "CARTE", "MUTUELLE"));
        modeBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void handlePayment() {
        String amountStr = amountField.getText();
        if (amountStr.isEmpty()) return;

        double montant = Double.parseDouble(amountStr);
        String mode = modeBox.getValue();

        Facture f = new Facture(0, selectedPatient.getId(), selectedPatient.getNom(), montant, mode);

        if (factureDAO.saveFacture(f)) {
            PDFGenerator.generateFacture(selectedPatient.getNom() + " " + selectedPatient.getPrenom(), montant, mode);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Paiement réussi ! Facture générée sur le Bureau.");
            alert.showAndWait();

            ((Stage) amountField.getScene().getWindow()).close();
        }
    }
}