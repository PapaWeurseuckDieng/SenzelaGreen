package models;

import java.util.Date;

public class Facture {
    private long idFacture;
    private String nomProduit;
    private double quantite;
    private double montantTotal;
    private String modePaiement;
    private long idClientF;
    private String nomClient;

    // Constructeur vide
    public Facture() {}

    public Facture(long idFacture, String nomProduit, double quantite, double montantTotal, String modePaiement, long idClientF) {
        this.idFacture = idFacture;
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.montantTotal = montantTotal;
        this.modePaiement = modePaiement;
        this.idClientF = idClientF;
    }
    
    public Facture(String nomProduit, double quantite, double montantTotal, String modePaiement, long idClientF) {
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.montantTotal = montantTotal;
        this.modePaiement = modePaiement;
        this.idClientF = idClientF;
    }
    
    

    public long getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(long idFacture) {
        this.idFacture = idFacture;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public long getIdClientF() {
        return idClientF;
    }

    public void setIdClientF(long idClientF) {
        this.idClientF = idClientF;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    

    
    
}
