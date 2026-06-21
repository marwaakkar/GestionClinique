package com.mediclinic.models;

import java.time.LocalDate;

public class Patient {
    private int id;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String mutuelle;
    private LocalDate dateNaissance;

    public Patient(int id, String nom, String prenom, String cin, String telephone, String mutuelle, LocalDate dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.mutuelle = mutuelle;
        this.dateNaissance = dateNaissance;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getCin() { return cin; }
    public String getTelephone() { return telephone; }
    public String getMutuelle() { return mutuelle; }
    public LocalDate getDateNaissance() { return dateNaissance; }
}