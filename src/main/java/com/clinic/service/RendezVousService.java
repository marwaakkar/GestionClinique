package com.clinic.service;
import com.clinic.model.RendezVous;

public class RendezVousService {
    public boolean planifierRendezVous(RendezVous rdv) {
        if (rdv.getDateHeure() == null) throw new IllegalArgumentException("Date manquante.");
        return true; 
    }
}