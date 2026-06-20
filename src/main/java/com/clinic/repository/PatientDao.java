package com.clinic.repository;
import com.clinic.model.Patient;
import java.util.List;

public interface PatientDao {
    boolean ajouter(Patient patient);
    List<Patient> trouverTout();
    boolean modifier(Patient patient);
    boolean supprimer(Long id);
}