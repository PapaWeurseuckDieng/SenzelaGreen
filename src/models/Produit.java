package models;

public class Produit {
    private long idProduit;
    private String nomProduit;
    private String description;
    private double stock;
    private double prixUnitaire;
    private long idUserF;       // FK vers users
    private long idCultureF;    // FK vers culture
    private String codeFactureF; // FK vers facture (VARCHAR)

    // Constructeur vide    
    public Produit() {}

    // Constructeur sans ID (pour insertion)
    public Produit(String nomProduit, String description, double stock, double prixUnitaire) {
        this.nomProduit = nomProduit;
        this.description = description;
        this.stock = stock;
        this.prixUnitaire = prixUnitaire;
//        this.idUserF = idUserF;
//        this.idCultureF = idCultureF;
//        this.codeFactureF = codeFactureF;
    }

    // Constructeur complet avec ID
    public Produit(long idProduit, String nomProduit, String description, double stock, double prixUnitaire) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.description = description;
        this.stock = stock;
        this.prixUnitaire = prixUnitaire;
//        this.idUserF = idUserF;
//        this.idCultureF = idCultureF;
//        this.codeFactureF = codeFactureF;
    }
    


    // Getters et Setters

    public long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(long idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public long getIdUserF() {
        return idUserF;
    }

    public void setIdUserF(long idUserF) {
        this.idUserF = idUserF;
    }

    public long getIdCultureF() {
        return idCultureF;
    }

    public void setIdCultureF(long idCultureF) {
        this.idCultureF = idCultureF;
    }

    public String getCodeFactureF() {
        return codeFactureF;
    }

    public void setCodeFactureF(String codeFactureF) {
        this.codeFactureF = codeFactureF;
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
