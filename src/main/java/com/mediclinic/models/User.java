package com.mediclinic.models;

public class User {
    private int id;
    private String nomComplet;
    private String username;
    private String role;
    public User(int id, String nomComplet, String username, String role) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
    @Override
    public String toString() {
        return nomComplet;
    }

}