package dao;

import models.Utilisateur;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                rs.getString("mdp")
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
}