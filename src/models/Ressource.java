package models;

public class Ressource {
    private long idRessource;
    private String typeRessource;
    private double quantite;
    private String unite;

    // Constructeur vide
    public Ressource() {}

    // Constructeur sans ID (insertion)
    public Ressource(String typeRessource, double quantite, String unite) {
        this.typeRessource = typeRessource;
        this.quantite = quantite;
        this.unite = unite;
    }

    // Constructeur complet avec ID
    public Ressource(long idRessource, String typeRessource, double quantite, String unite) {
        this.idRessource = idRessource;
        this.typeRessource = typeRessource;
        this.quantite = quantite;
        this.unite = unite;
    }

    // Getters et Setters
    public long getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(long idRessource) {
        this.idRessource = idRessource;
    }

    public String getTypeRessource() {
        return typeRessource;
    }

    public void setTypeRessource(String typeRessource) {
        this.typeRessource = typeRessource;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
