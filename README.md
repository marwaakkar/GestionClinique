# MédiClinic - Système de Gestion de Clinique Médicale

Application de bureau en **JavaFX** avec architecture en couches (N-Tier) et persistance **JDBC (DAO)**.

## 💾 Configuration SQL
```sql
CREATE DATABASE IF NOT EXISTS miniclinic_db;
USE miniclinic_db;

CREATE TABLE patient (
    id_patient INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    email VARCHAR(100)
);

CREATE TABLE medecin (
    id_medecin INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    specialite VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

CREATE TABLE rendez_vous (
    id_rdv INT AUTO_INCREMENT PRIMARY KEY,
    date_heure DATETIME NOT NULL,
    statut VARCHAR(20) DEFAULT 'PLANIFIE',
    motif VARCHAR(255),
    id_patient INT,
    id_medecin INT,
    FOREIGN KEY (id_patient) REFERENCES patient(id_patient) ON DELETE CASCADE,
    FOREIGN KEY (id_medecin) REFERENCES medecin(id_medecin) ON DELETE CASCADE
);

INSERT INTO medecin (nom, prenom, specialite, email) VALUES 
('Alami', 'Ahmed', 'Généraliste', 'alami@clinic.com'),
('Benani', 'Sanaa', 'Pédiatre', 'benani@clinic.com');