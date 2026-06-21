package com.mediclinic.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class RendezVous {
    private int id;
    private int idPatient;
    private String nomPatient;
    private LocalDate dateRdv;
    private LocalTime heureRdv;
    private String statut;

    // Constructor
    public RendezVous(int id, int idPatient, String nomPatient, LocalDate dateRdv, LocalTime heureRdv, String statut) {
        this.id = id;
        this.idPatient = idPatient;
        this.nomPatient = nomPatient;
        this.dateRdv = dateRdv;
        this.heureRdv = heureRdv;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public LocalDate getDateRdv() {
        return dateRdv;
    }

    public LocalTime getHeureRdv() {
        return heureRdv;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}