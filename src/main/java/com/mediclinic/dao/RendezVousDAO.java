package com.mediclinic.dao;

import com.mediclinic.config.DatabaseConnection;
import com.mediclinic.models.RendezVous;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO {

    public boolean createRdvWithMedecin(int idPatient, int idMedecin, LocalDate date, String heure, String statut) {
        String query = "INSERT INTO rendez_vous (id_patient, id_medecin, date_rdv, heure_rdv, statut) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, idPatient);
            st.setInt(2, idMedecin);
            st.setDate(3, Date.valueOf(date));
            st.setString(4, heure);
            st.setString(5, statut);

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création du RDV : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<RendezVous> getTodayRdv() {
        List<RendezVous> list = new ArrayList<>();
        // استعلام يجلب بيانات الموعد + اسم المريض
        String query = "SELECT r.*, p.nom, p.prenom FROM rendez_vous r " +
                "JOIN patients p ON r.id_patient = p.id " +
                "WHERE r.date_rdv = CURDATE() ORDER BY r.heure_rdv ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                String nomCompletPatient = rs.getString("nom") + " " + rs.getString("prenom");

                list.add(new RendezVous(
                        rs.getInt("id"),
                        rs.getInt("id_patient"),
                        nomCompletPatient,
                        rs.getDate("date_rdv").toLocalDate(),
                        rs.getTime("heure_rdv").toLocalTime(),
                        rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<RendezVous> getTodayRdvByMedecin(int idMedecin) {
        List<RendezVous> list = new ArrayList<>();
        String query = "SELECT r.*, p.nom, p.prenom FROM rendez_vous r " +
                "JOIN patients p ON r.id_patient = p.id " +
                "WHERE r.id_medecin = ? AND r.date_rdv = CURDATE() AND r.statut = 'EN_ATTENTE'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, idMedecin);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new RendezVous(
                        rs.getInt("id"),
                        rs.getInt("id_patient"),
                        rs.getString("nom") + " " + rs.getString("prenom"),
                        rs.getDate("date_rdv").toLocalDate(),
                        rs.getTime("heure_rdv").toLocalTime(),
                        rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateStatus(int idRdv, String nouveauStatut) {
        String query = "UPDATE rendez_vous SET statut = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, nouveauStatut);
            st.setInt(2, idRdv);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}