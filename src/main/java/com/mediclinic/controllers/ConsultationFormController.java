package com.mediclinic.controllers;

import com.mediclinic.dao.ConsultationDAO;
import com.mediclinic.models.Consultation;
import com.mediclinic.models.Patient;
import com.mediclinic.utils.PDFGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.time.LocalDate;

public class ConsultationFormController {

    @FXML private Label patientLabel;
    @FXML private TextArea diagnosticArea;
    @FXML private TextArea observationsArea;
    @FXML private TextField tensionField;
    @FXML private TextField poidsField;

    private Patient selectedPatient;
    private ConsultationDAO consultationDAO = new ConsultationDAO();

    public void setPatient(Patient patient) {
        this.selectedPatient = patient;
        patientLabel.setText("Consultation : " + patient.getNom() + " " + patient.getPrenom());
    }

    @FXML
    private void handleSave() {
        String diagnostic = diagnosticArea.getText();
        String observations = observationsArea.getText();
        String tension = tensionField.getText();
        double poids = 0;

        try {
            poids = Double.parseDouble(poidsField.getText());
        } catch (NumberFormatException e) {
            poids = 0;
        }
        Consultation con = new Consultation(0, selectedPatient.getId(), "", diagnostic, observations, tension, poids);
        boolean isSaved = consultationDAO.saveConsultation(con);

        if (isSaved) {
            System.out.println("Consultation enregister");

            PDFGenerator.generateOrdonnance(selectedPatient, diagnostic, LocalDate.now().toString());

            Stage stage = (Stage) diagnosticArea.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Erreur d'enregistrement dans la base de donnee");
        }
    }
}