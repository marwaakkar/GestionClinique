package com.clinic.controller;
import com.clinic.model.Medecin;
import com.clinic.model.Patient;
import com.clinic.model.RendezVous;
import com.clinic.service.PatientService;
import com.clinic.service.RendezVousService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AppointmentController {
    @FXML private ComboBox<Patient> comboPatient;
    @FXML private ComboBox<Medecin> comboMedecin;
    @FXML private DatePicker datePickerRdv;
    @FXML private ComboBox<String> comboHeure;
    @FXML private TextField txtMotif;

    private final PatientService patientService = new PatientService();
    private final RendezVousService rdvService = new RendezVousService();

    @FXML public void initialize() {
        comboPatient.setItems(FXCollections.observableArrayList(patientService.recupererTousLesPatients()));
        comboHeure.setItems(FXCollections.observableArrayList("09:00", "10:00", "14:00"));
    }

    @FXML private void handleSaveAppointment() {
        if (comboPatient.getValue() != null && datePickerRdv.getValue() != null) {
            rdvService.planifierRendezVous(new RendezVous(datePickerRdv.getValue().atStartOfDay(), txtMotif.getText(), comboPatient.getValue(), comboMedecin.getValue()));
        }
    }
}