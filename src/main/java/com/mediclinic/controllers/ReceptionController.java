package com.mediclinic.controllers;

import com.mediclinic.dao.PatientDAO;
import com.mediclinic.dao.RendezVousDAO;
import com.mediclinic.models.Patient;
import com.mediclinic.models.RendezVous;
import com.mediclinic.utils.PDFGenerator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ReceptionController {

    @FXML private TextField searchField;
    @FXML private TableView<Patient> patientTable;
    @FXML private TableColumn<Patient, String> colNom, colPrenom, colCin, colTel, colMutuelle;

    // tableau de file d'attente
    @FXML private TableView<RendezVous> rdvTable;
    @FXML private TableColumn<RendezVous, String> colRdvPatient, colRdvHeure, colRdvStatut;
    @FXML private TableColumn<RendezVous, Void> colOrdonnance, colFacture;

    private PatientDAO patientDAO = new PatientDAO();
    private RendezVousDAO rdvDAO = new RendezVousDAO();

    @FXML
    public void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colMutuelle.setCellValueFactory(new PropertyValueFactory<>("mutuelle"));

        colRdvPatient.setCellValueFactory(new PropertyValueFactory<>("nomPatient"));
        colRdvHeure.setCellValueFactory(new PropertyValueFactory<>("heureRdv"));
        colRdvStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        setupOrdonnanceColumn();
        setupFactureColumn();

        refreshAll();
    }

    private void setupOrdonnanceColumn() {
        colOrdonnance.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Voir PDF");
            {
                btn.setStyle("-fx-background-color: #ed8936; -fx-text-fill: white; -fx-cursor: hand;");
                btn.setOnAction(event -> {
                    RendezVous rdv = getTableView().getItems().get(getIndex());
                    if ("TERMINE".equals(rdv.getStatut())) {
                        String formattedName = rdv.getNomPatient().trim().replace(" ", "_");
                        PDFGenerator.openPDF("Ordonnance_" + formattedName + ".pdf");
                    } else {
                        showAlert("Information", "L'ordonnance n'est pas encore prête.");
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    private void setupFactureColumn() {
        colFacture.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Facture / $");
            {
                btn.setStyle("-fx-background-color: #38a169; -fx-text-fill: white; -fx-cursor: hand;");
                btn.setOnAction(event -> {
                    RendezVous rdv = getTableView().getItems().get(getIndex());
                    handlePaymentForRdv(rdv);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    @FXML
    public void refreshAll() {
        loadPatients();
        loadTodayRdv();
    }

    private void loadPatients() {
        List<Patient> patients = patientDAO.searchPatients(searchField.getText());
        patientTable.setItems(FXCollections.observableArrayList(patients));
    }

    private void loadTodayRdv() {
        List<RendezVous> rdvs = rdvDAO.getTodayRdv();
        rdvTable.setItems(FXCollections.observableArrayList(rdvs));
    }

    @FXML
    private void handleNewPatient() throws IOException {
        openWindow("/fxml/patient_form.fxml", "Nouveau Patient");
    }

    @FXML
    private void handleTakeRDV() throws IOException {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rdv_form.fxml"));
            Parent root = loader.load();

            RdvFormController controller = loader.getController();
            controller.setPatient(selected);

            Stage stage = new Stage();
            stage.setTitle("Prendre RDV");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            refreshAll();
        } else {
            showAlert("Sélection requise", "Veuillez sélectionner un patient dans le tableau du haut.");
        }
    }

    private void handlePaymentForRdv(RendezVous rdv) {
        try {
            Patient p = patientDAO.getPatientById(rdv.getIdPatient());

            if (p != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/billing_view.fxml"));
                Parent root = loader.load();

                BillingController controller = loader.getController();
                controller.setPatient(p);

                Stage stage = new Stage();
                stage.setTitle("Règlement - " + p.getNom());
                stage.setScene(new Scene(root));
                stage.showAndWait();
                refreshAll();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openWindow(String path, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        refreshAll();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}