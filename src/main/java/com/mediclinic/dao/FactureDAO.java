package com.mediclinic.dao;

import com.mediclinic.config.DatabaseConnection;
import com.mediclinic.models.Facture;
import java.sql.*;

public class FactureDAO {
    public boolean saveFacture(Facture f) {
        String query = "INSERT INTO factures (id_patient, montant, mode_paiement) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, f.getIdPatient());
            st.setDouble(2, f.getMontant());
            st.setString(3, f.getModePaiement());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}