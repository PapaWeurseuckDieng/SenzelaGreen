package models;

import java.util.Date;

public class Culture {
    private long idCulture;
    private String nomCulture;
    private String description;
    private String typeCulture;
    private String cycleCulture;
    private Date dateDebut;
    private Date dateFin;
    private Double rendementAttendu;

    // Constructeurs
    public Culture() {}

    public Culture(String nomCulture, String description, String typeCulture, String cycleCulture, Date dateDebut, Date dateFin, Double rendementAttendu) {
        this.nomCulture = nomCulture;
        this.description = description;
        this.typeCulture = typeCulture;
        this.cycleCulture = cycleCulture;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.rendementAttendu = rendementAttendu;
    }

    public Culture(long idCulture, String nomCulture, String description, String typeCulture, String cycleCulture, Date dateDebut, Date dateFin, Double rendementAttendu) {
        this.idCulture = idCulture;
        this.nomCulture = nomCulture;
        this.description = description;
        this.typeCulture = typeCulture;
        this.cycleCulture = cycleCulture;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.rendementAttendu = rendementAttendu;
       
    }

    // Getters et Setters
    public long getIdCulture() {
        return idCulture;
    }

    public void setIdCulture(long idCulture) {
        this.idCulture = idCulture;
    }

    public String getNomCulture() {
        return nomCulture;
    }

    public void setNomCulture(String nomCulture) {
        this.nomCulture = nomCulture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeCulture() {
        return typeCulture;
    }

    public void setTypeCulture(String typeCulture) {
        this.typeCulture = typeCulture;
    }

    public String getCycleCulture() {
        return cycleCulture;
    }

    public void setCycleCulture(String cycleCulture) {
        this.cycleCulture = cycleCulture;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Double getRendementAttendu() {
        return rendementAttendu;
    }

    public void setRendementAttendu(Double rendementAttendu) {
        this.rendementAttendu = rendementAttendu;
    }

 
    
    
}
