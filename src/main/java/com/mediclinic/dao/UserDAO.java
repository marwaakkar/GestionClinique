package com.mediclinic.dao;

import com.mediclinic.config.DatabaseConnection;
import com.mediclinic.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User login(String user, String pass) {
        String query = "SELECT * FROM utilisateurs WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nom_complet"),
                        rs.getString("username"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllMedecins() {
        List<User> medecins = new ArrayList<>();
        String query = "SELECT * FROM utilisateurs WHERE role = 'MEDECIN'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                medecins.add(new User(
                        rs.getInt("id"),
                        rs.getString("nom_complet"),
                        rs.getString("username"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;
    }
}