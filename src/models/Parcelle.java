package models;

public class Parcelle {
    private long idParcelle;
    private Double superficie;
    private String nomParcelle;
    private Double pHSol;
    private String typeSol;

    public Parcelle() {
    }

    // Constructeur sans ID

    public Parcelle(Double superficie, String nomParcelle, Double pHSol, String typeSol) {
        this.superficie = superficie;
        this.nomParcelle = nomParcelle;
        this.pHSol = pHSol;
        this.typeSol = typeSol;
//        this.idUserF = idUserF;
    }
    

    // Constructeur avec ID
    public Parcelle(long idParcelle, Double superficie, String nomParcelle, Double pHSol, String typeSol) {
        this.idParcelle = idParcelle;
        this.superficie = superficie;
        this.nomParcelle = nomParcelle;
        this.pHSol = pHSol;
        this.typeSol = typeSol;
//        this.idUserF = idUserF;
    }  

    // Getters et Setters
    public long getIdParcelle() {
        return idParcelle;
    }

    public void setIdParcelle(long idParcelle) {
        this.idParcelle = idParcelle;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public String getNomParcelle() {
        return nomParcelle;
    }

    public void setNomParcelle(String nomParcelle) {
        this.nomParcelle = nomParcelle;
    }

    public Double getPHSol() {
        return pHSol;
    }

    public void setPHSol(Double pHSol) {
        this.pHSol = pHSol;
    }

    public String getTypeSol() {
        return typeSol;
    }

    public void setTypeSol(String typeSol) {
        this.typeSol = typeSol;
    }

}
