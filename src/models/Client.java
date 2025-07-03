
package models;

public class Client {
    private long idClient;
    private String nomClient;
    private String prenomClient;
    private String telephone;
    private String adresse;

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Client() {
    }

    public Client(long idClient, String nomClient, String prenomClient, String telephone, String adresse) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public Client(String nomClient, String prenomClient, String telephone, String adresse) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.telephone = telephone;
        this.adresse = adresse;
    }
    
    
}
