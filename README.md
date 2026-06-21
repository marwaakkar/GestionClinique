MediClinic - Système de Gestion de Clinique Médicale

MediClinic est une application de bureau performante développée en JavaFX 17, conçue pour automatiser et simplifier la gestion des flux de travail au sein d'une clinique médicale. Elle permet de gérer tout le cycle de vie d'un patient, de son enregistrement à l'accueil jusqu'à la génération de son ordonnance et de sa facture finale.


Démonstration Vidéo
Découvrez une présentation complète de l'application (Interface, fonctionnalités et génération de documents) en cliquant sur le lien ci-dessous :

>https://drive.google.com/your-link-here

Fonctionnalités Clés

-Sécurité et Authentification
    Accès sécurisé par identifiant et mot de passe.
    Gestion des rôles (Médecin / Réceptionniste) avec des interfaces distinctes et adaptées.

-Gestion de la Réception (Accueil)
    Enregistrement des patients : Création et recherche rapide de dossiers patients par nom ou CIN.
    Planification des Rendez-vous: Gestion du calendrier et affectation d'un médecin spécifique.
    File d'attente : Suivi en temps réel des patients présents dans l'établissement.

-Espace Médecin (Consultation)
    Consultation numérique : Saisie du diagnostic, des constantes vitales et des observations.
    Ordonnance Intelligente : Génération automatique d'une ordonnance professionnelle au format PDF, prête à l'impression, avec liste numérotée des médicaments.

-Facturation et Règlement
    Calcul automatisé des frais de consultation.
    Gestion des modes de paiement (Espèces, Carte, Mutuelle).
    Génération de quittances de paiement en PDF archivées sur le poste de travail.


-Technologies Utilisées
   Langage : Java 17
   UI Framework : JavaFX (FXML)
   Base de données : MySQL 8.0
   Gestionnaire de dépendances : Maven
   Bibliothèque PDF :iText 7 (Kernel, Layout, IO)
   Design : CSS personnalisé pour un rendu moderne et épuré.


-Installation et Lancement

-Prérequis
JDK 17 installé.
WampServer (pour la base de données MySQL).

 Étapes
1.  Base de données : Importer le fichier `database_schema.sql` situé dans le dossier `/sql`.
2.  Configuration : Ouvrir le projet dans **IntelliJ IDEA** ou **Eclipse**.
3.  Maven : Effectuer un `Reload Project` pour télécharger toutes les dépendances.
4.  Lancement : Exécuter la classe `MainApp.java` ou utiliser la commande :
    ```bash
    mvn javafx:run
    ```


-Architecture du Projet
Le projet est structuré selon les patterns MVC(Modèle-Vue-Contrôleur) et DAO (Data Access Object) :
   `models/` : Entités de données (Patient, User, Consultation...).
   `dao/` : Interactions directes avec la base de données SQL.
   `controllers/` : Logique de contrôle des interfaces.
   `views (fxml)/` : Design des écrans via Scene Builder.
   `utils/` : Services transverses (Génération PDF, Navigation).
