package com.clinic.repository;
import com.clinic.config.DBConnection;
import com.clinic.model.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    @Override
    public boolean ajouter(Patient patient) {
        String sql = "INSERT INTO patient (nom, prenom, telephone, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getNom()); stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getTelephone()); stmt.setString(4, patient.getEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public List<Patient> trouverTout() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patient";
        try (Connection conn = DBConnection.getConnection(); Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getLong("id_patient")); p.setNom(rs.getString("nom")); p.setPrenom(rs.getString("prenom"));
                p.setTelephone(rs.getString("telephone")); p.setEmail(rs.getString("email"));
                list.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean modifier(Patient patient) {
        String sql = "UPDATE patient SET nom=?, prenom=?, telephone=?, email=? WHERE id_patient=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getNom()); stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getTelephone()); stmt.setString(4, patient.getEmail());
            stmt.setLong(5, patient.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean supprimer(Long id) {
        String sql = "DELETE FROM patient WHERE id_patient=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id); return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}