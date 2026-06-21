package com.mediclinic.dao;

import com.mediclinic.config.DatabaseConnection;
import com.mediclinic.models.Consultation;
import com.mediclinic.models.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDAO {

    public List<Patient> getWaitingList() {
        List<Patient> list = new ArrayList<>();
        String query = "SELECT p.* FROM patients p JOIN rendez_vous r ON p.id = r.id_patient " +
                "WHERE r.date_rdv = CURDATE() AND r.statut = 'EN_ATTENTE'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Patient(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("cin"), rs.getString("telephone"), rs.getString("mutuelle"),
                        rs.getDate("date_naissance").toLocalDate()));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean saveConsultation(Consultation c) {
        String insertQuery = "INSERT INTO consultations (id_patient, diagnostic, observations, tension, poids) VALUES (?, ?, ?, ?, ?)";
        String updateRdv = "UPDATE rendez_vous SET statut = 'TERMINE' WHERE id_patient = ? AND date_rdv = CURDATE()";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Transaction

            try (PreparedStatement ps1 = conn.prepareStatement(insertQuery);
                 PreparedStatement ps2 = conn.prepareStatement(updateRdv)) {

                ps1.setInt(1, c.getIdPatient());
                ps1.setString(2, c.getDiagnostic());
                ps1.setString(3, c.getObservations());
                ps1.setString(4, c.getTension());
                ps1.setDouble(5, c.getPoids());
                ps1.executeUpdate();

                ps2.setInt(1, c.getIdPatient());
                ps2.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}