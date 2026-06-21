package com.clinic.model;
import java.time.LocalDateTime;

public class RendezVous {
    private Long id;
    private LocalDateTime dateHeure;
    private String statut;
    private String motif;
    private Patient patient;
    private Medecin medecin;

    public RendezVous() {}
    public RendezVous(LocalDateTime dateHeure, String motif, Patient patient, Medecin medecin) {
        this.dateHeure = dateHeure; this.motif = motif; this.patient = patient; this.medecin = medecin; this.statut = "PLANIFIE";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }
}