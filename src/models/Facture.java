package models;

import java.util.Date;

public class Facture {
    private String codeFacture;
    private double quantite;
    private Date datePaiement;
    private double montantTotal;
    private long idUserF;

    // Constructeur vide
    public Facture() {}

    // Constructeur sans codeFacture (si généré ailleurs)
    public Facture(double quantite, Date datePaiement, double montantTotal, long idUserF) {
        this.quantite = quantite;
        this.datePaiement = datePaiement;
        this.montantTotal = montantTotal;
        this.idUserF = idUserF;
    }

    // Constructeur complet
    public Facture(String codeFacture, double quantite, Date datePaiement, double montantTotal, long idUserF) {
        this.codeFacture = codeFacture;
        this.quantite = quantite;
        this.datePaiement = datePaiement;
        this.montantTotal = montantTotal;
        this.idUserF = idUserF;
    }

    // Getters & Setters

    public String getCodeFacture() {
        return codeFacture;
    }

    public void setCodeFacture(String codeFacture) {
        this.codeFacture = codeFacture;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public long getIdUserF() {
        return idUserF;
    }

    public void setIdUserF(long idUserF) {
        this.idUserF = idUserF;
    }
}
