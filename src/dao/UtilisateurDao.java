package dao;

import models.Utilisateur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDao {
    
    public static Utilisateur connexionUser(String email) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE email = ?";
        ResultSet rs = DatabaseService.executeQuery(query, email);
        
        if (rs.next()) {
            return new Utilisateur(
                rs.getInt("idUser"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("telephone"),
                rs.getString("adresse"),
                rs.getString("role"),
                rs.getString("genre"),
                rs.getString("email"),
                rs.getString("mdp"),
                rs.getString("idCulture")
            );
        }
        return null;
    }
    
    public static boolean verifierConnexion(String email, String mdpCrypte) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM users WHERE email = ? AND mdp = ?";
        ResultSet rs = DatabaseService.executeQuery(query, email, mdpCrypte);
        
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }
    
        public static boolean ajouterAgriculteur(Utilisateur agriculteur) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO users (nom, prenom, telephone, adresse, role, genre, email, mdp) " +
                      "VALUES (?, ?, ?, ?, 'agriculteur', ?, ?, ?)";
        
        int rowsAffected = DatabaseService.executeUpdate(
            query,
            agriculteur.getNom(),
            agriculteur.getPrenom(),
            agriculteur.getTelephone(),
            agriculteur.getAdresse(),
            agriculteur.getGenre(),
            agriculteur.getEmail(),
            agriculteur.getMdp()
        );
        
        return rowsAffected > 0;
    }
        
        public static List<Utilisateur> getAgriculteurs() throws SQLException, ClassNotFoundException {
        List<Utilisateur> agriculteurs = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = 'agriculteur'";
        ResultSet rs = DatabaseService.executeQuery(query);
        
        while (rs.next()) {
            agriculteurs.add(new Utilisateur(
                rs.getInt("idUser"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("telephone"),
                rs.getString("adresse"),
                rs.getString("role"),
                rs.getString("genre"),
                rs.getString("email"),
                rs.getString("mdp"),
                rs.getString("idCulture")
            ));
        }
        
        return agriculteurs;
    }

    public static boolean supprimerAgriculteur(int id) throws SQLException, ClassNotFoundException {
    try {
        // Désactiver temporairement les contraintes de clé étrangère si nécessaire
        DatabaseService.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
        
        // Supprimer d'abord les références dans les tables liées
        String deleteParcelles = "DELETE FROM parcelle WHERE idUserF = ?";
        DatabaseService.executeUpdate(deleteParcelles, id);
        
        // Puis supprimer l'agriculteur
        String deleteAgriculteur = "DELETE FROM users WHERE idUser = ? AND role = 'agriculteur'";
        int rowsAffected = DatabaseService.executeUpdate(deleteAgriculteur, id);
        
        return rowsAffected > 0;
    } finally {
        // Réactiver les contraintes
        DatabaseService.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
    }
}
    

}