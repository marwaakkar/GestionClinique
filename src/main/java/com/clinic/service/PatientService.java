package com.clinic.service;
import com.clinic.model.Patient;
import com.clinic.repository.PatientDao;
import com.clinic.repository.PatientDaoImpl;
import java.util.List;

public class PatientService {
    private final PatientDao patientDao = new PatientDaoImpl();

    public boolean enregistrerPatient(Patient p) {
        if (p.getNom().isEmpty() || p.getPrenom().isEmpty()) throw new IllegalArgumentException("Champs vides !");
        return patientDao.ajouter(p);
    }
    public List<Patient> recupererTousLesPatients() { return patientDao.trouverTout(); }
    public boolean mettreAJourPatient(Patient p) { return patientDao.modifier(p); }
    public boolean supprimerPatient(Long id) { return patientDao.supprimer(id); }
}