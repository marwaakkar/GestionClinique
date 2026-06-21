package com.mediclinic.models;

public class Medecin {
    private Long id;
    private String nom;
    private String prenom;
    private String specialite;
    private String email;

    public Medecin() {}
    public Medecin(String nom, String prenom, String specialite, String email) {
        this.nom = nom; this.prenom = prenom; this.specialite = specialite; this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() { return "Dr. " + nom + " (" + specialite + ")"; }
}