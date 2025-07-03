package models;

public class Utilisateur {
    private long idUser;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private String role;
    private String genre;
    private String email;
    private String mdp;
    private String idCulture;
    
    // Constructeur
    public Utilisateur(long idUser, String nom, String prenom, String telephone, String adresse, String role, String genre, String email, String mdp) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.role = role;
        this.genre = genre;
        this.email = email;
        this.mdp = mdp;
    }
    
    public Utilisateur( String nom, String prenom, String telephone, String adresse, String role, String genre, String email, String mdp ) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.role = role;
        this.genre = genre;
        this.email = email;
        this.mdp = mdp;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    
    
    
}