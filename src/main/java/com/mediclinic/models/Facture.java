package com.mediclinic.models;

import java.time.LocalDateTime;

public class Facture {
    private int id;
    private int idPatient;
    private String nomPatient;
    private double montant;
    private String modePaiement;

    public Facture(int id, int idPatient, String nomPatient, double montant, String modePaiement) {
        this.id = id;
        this.idPatient = idPatient;
        this.nomPatient = nomPatient;
        this.montant = montant;
        this.modePaiement = modePaiement;
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

    public double getMontant() {
        return montant;
    }

    public String getModePaiement() {
        return modePaiement;
    }
}