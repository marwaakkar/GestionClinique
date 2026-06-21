package com.mediclinic.utils;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.ListNumberingType;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.mediclinic.models.Patient;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PDFGenerator {

    private static String getDesktopPath() {
        String userHome = System.getProperty("user.home");
        File desktop = new File(userHome + File.separator + "Desktop");
        if (desktop.exists()) return desktop.getAbsolutePath();
        desktop = new File(userHome + File.separator + "OneDrive" + File.separator + "Desktop");
        if (desktop.exists()) return desktop.getAbsolutePath();
        return userHome;
    }

    public static void openPDF(String fileName) {
        try {
            File file = new File(getDesktopPath() + File.separator + fileName);
            if (file.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ouverture du fichier : " + e.getMessage());
        }
    }

    public static void generateOrdonnance(Patient patient, String diagnostic, String date) {
        String nomFichier = "Ordonnance_" + patient.getNom().trim().replace(" ", "_") + "_" + patient.getPrenom().trim().replace(" ", "_") + ".pdf";
        String dest = getDesktopPath() + File.separator + nomFichier;

        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(dest));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Table headerTable = new Table(UnitValue.createPercentArray(new float[]{1, 1}));
            headerTable.setWidth(UnitValue.createPercentValue(100));

            headerTable.addCell(new Cell().add(new Paragraph("MEDICLINIC")
                            .setFontSize(22).setBold().setFontColor(ColorConstants.BLUE))
                    .add(new Paragraph("Centre Médical Pluridisciplinaire").setFontSize(9).setItalic())
                    .setBorder(Border.NO_BORDER));

            headerTable.addCell(new Cell().add(new Paragraph("14 Boulevard des Pyramides\nCasablanca, Maroc\nTel: +212 5 22 10 20 30\nEmail: contact@mediclinic.ma")
                            .setTextAlignment(TextAlignment.RIGHT).setFontSize(9))
                    .setBorder(Border.NO_BORDER));

            document.add(headerTable);

            SolidLine line = new SolidLine(1f);
            line.setColor(ColorConstants.GRAY);
            document.add(new LineSeparator(line).setMarginTop(5).setMarginBottom(10));

            document.add(new Paragraph("□ Dr Olivier D.      □ Dr Bernard A.      □ Dr Priscilla R.      □ Dr Peter T.")
                    .setFontSize(9).setMarginBottom(15));

            document.add(new Paragraph("\nORDONNANCE")
                    .setTextAlignment(TextAlignment.CENTER).setFontSize(24).setBold().setUnderline().setMarginBottom(20));

            Table patientTable = new Table(UnitValue.createPercentArray(new float[]{1, 1}));
            patientTable.setWidth(UnitValue.createPercentValue(100));

            patientTable.addCell(new Cell().add(new Paragraph("Patient : " + patient.getNom().toUpperCase() + " " + patient.getPrenom())
                    .setBold().setFontSize(12)).setBorder(Border.NO_BORDER));

            patientTable.addCell(new Cell().add(new Paragraph("Fait le : " + date)
                    .setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));

            document.add(patientTable);
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("PRESCRIPTION :").setBold().setUnderline().setMarginBottom(10));

            com.itextpdf.layout.element.List list = new com.itextpdf.layout.element.List(ListNumberingType.DECIMAL);
            list.setMarginLeft(30);

            if (diagnostic != null && !diagnostic.isEmpty()) {
                String[] medicaments = diagnostic.split("\n");
                for (String med : medicaments) {
                    if (!med.trim().isEmpty()) {
                        list.add(new ListItem(med.trim()));
                    }
                }
            }
            document.add(list);

            // --- 6. SIGNATURE ---
            document.add(new Paragraph("\n\n\n\nCachet et Signature")
                    .setTextAlignment(TextAlignment.RIGHT).setMarginRight(50).setBold().setUnderline());

            document.close();
            System.out.println("Ordonnance générée : " + nomFichier);
            openPDF(nomFichier);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateFacture(String patientName, double montant, String modePaiement) {
        String nomFichier = "Facture_" + patientName.trim().replace(" ", "_") + ".pdf";
        String dest = getDesktopPath() + File.separator + nomFichier;

        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(dest));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String dateHeure = LocalDateTime.now().format(dtf);

            document.add(new Paragraph("MEDICLINIC - REÇU DE PAIEMENT")
                    .setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER).setMarginBottom(20));

            document.add(new Paragraph("Date : " + dateHeure));
            document.add(new Paragraph("Patient : " + patientName));
            document.add(new Paragraph("\n----------------------------------------------------------"));
            document.add(new Paragraph("MONTANT PAYÉ : " + montant + " DH").setBold().setFontSize(14));
            document.add(new Paragraph("Mode de règlement : " + modePaiement));
            document.add(new Paragraph("Statut : RÉGLÉ").setBold());
            document.add(new Paragraph("----------------------------------------------------------\n"));

            document.add(new Paragraph("Merci de votre confiance."));

            document.close();
            System.out.println("Facture générée : " + nomFichier);
            openPDF(nomFichier);

        } catch (Exception e) {
            System.err.println("Erreur Facture PDF : " + e.getMessage());
            e.printStackTrace();
        }
    }
}