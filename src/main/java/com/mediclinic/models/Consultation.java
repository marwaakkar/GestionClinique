package com.clinic.model;

public class Consultation {
    private Long id;
    private String diagnostic;
    private String observations;
    private Double tarif;
    private RendezVous rendezVous;

    public Consultation() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDiagnostic() { return diagnostic; }
    public void setDiagnostic(String diagnostic) { this.diagnostic = diagnostic; }
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
    public Double getTarif() { return tarif; }
    public void setTarif(Double tarif) { this.tarif = tarif; }
    public RendezVous getRendezVous() { return rendezVous; }
    public void setRendezVous(RendezVous rendezVous) { this.rendezVous = rendezVous; }
}