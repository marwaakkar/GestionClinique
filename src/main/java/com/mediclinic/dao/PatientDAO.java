package com.mediclinic.dao;

import com.mediclinic.config.DatabaseConnection;
import com.mediclinic.models.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public boolean addPatient(Patient p) {
        String query = "INSERT INTO patients (nom, prenom, cin, telephone, mutuelle, date_naissance) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            st.setString(1, p.getNom());
            st.setString(2, p.getPrenom());
            st.setString(3, p.getCin());
            st.setString(4, p.getTelephone());
            st.setString(5, p.getMutuelle());
            st.setDate(6, Date.valueOf(p.getDateNaissance())); // تحويل LocalDate إلى SQL Date

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du patient : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Patient> searchPatients(String search) {
        List<Patient> list = new ArrayList<>();
        String query = "SELECT * FROM patients WHERE nom LIKE ? OR cin LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            String searchPattern = "%" + search + "%";
            st.setString(1, searchPattern);
            st.setString(2, searchPattern);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("cin"),
                        rs.getString("telephone"),
                        rs.getString("mutuelle"),
                        rs.getDate("date_naissance").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des patients : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public Patient getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Patient(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("cin"),
                        rs.getString("telephone"),
                        rs.getString("mutuelle"),
                        rs.getDate("date_naissance").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du patient par ID : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePatient(Patient p) {
        String query = "UPDATE patients SET nom=?, prenom=?, cin=?, telephone=?, mutuelle=?, date_naissance=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, p.getNom());
            st.setString(2, p.getPrenom());
            st.setString(3, p.getCin());
            st.setString(4, p.getTelephone());
            st.setString(5, p.getMutuelle());
            st.setDate(6, Date.valueOf(p.getDateNaissance()));
            st.setInt(7, p.getId());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}