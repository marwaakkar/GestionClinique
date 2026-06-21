package com.mediclinic.controllers;

import com.mediclinic.dao.ConsultationDAO;
import com.mediclinic.models.Patient;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MedecinController {
    @FXML private TableView<Patient> waitingTable;
    @FXML private TableColumn<Patient, String> colNom;
    @FXML private TableColumn<Patient, String> colPrenom;
    @FXML private TableColumn<Patient, String> colCin;

    private ConsultationDAO consultationDAO = new ConsultationDAO();

    @FXML
    public void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));

        loadWaitingList();
    }

    private void loadWaitingList() {
        waitingTable.setItems(FXCollections.observableArrayList(consultationDAO.getWaitingList()));
    }

    @FXML
    private void handleStartConsultation() {
        Patient selected = waitingTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/consultation_form.fxml"));
                Parent root = loader.load();

                ConsultationFormController controller = loader.getController();
                controller.setPatient(selected);

                Stage stage = new Stage();
                stage.setTitle("Consultation en cours");
                stage.setScene(new Scene(root));
                stage.show();
                stage.setOnHidden(e -> loadWaitingList());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un patient !");
            alert.show();
        }
    }
}