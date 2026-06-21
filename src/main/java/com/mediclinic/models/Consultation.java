package com.mediclinic.models;

import java.time.LocalDateTime;

public class Consultation {
    private int id;
    private int idPatient; // C'est ce champ qui est utilisé
    private String nomPatient;
    private String diagnostic;
    private String observations;
    private String tension;
    private double poids;

    public Consultation(int id, int idPatient, String nomPatient, String diagnostic, String observations, String tension, double poids) {
        this.id = id;
        this.idPatient = idPatient;
        this.nomPatient = nomPatient;
        this.diagnostic = diagnostic;
        this.observations = observations;
        this.tension = tension;
        this.poids = poids;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public String getObservations() {
        return observations;
    }

    public String getTension() {
        return tension;
    }

    public double getPoids() {
        return poids;
    }
}