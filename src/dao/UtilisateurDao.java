package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Utilisateur;
import utils.UtilsFonction;

public class UtilisateurDao {
    private static final String SQL_SELECT = "SELECT * FROM users";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE idUser = ?";
    private static final String SQL_INSERT = "INSERT INTO users (nom, prenom, telephone, adresse, role, genre, email, mdp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE users SET nom=?, prenom=?, telephone=?, adresse=?, role=?, genre=?, email=?, mdp=? WHERE idUser=?";
    private static final String SQL_DELETE = "DELETE FROM users WHERE idUser=?";
    private static final String SQL_CONNEXION = "SELECT * FROM users WHERE email=? AND mdp=?";

    public static List<Utilisateur> getAll() throws SQLException, ClassNotFoundException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT, null);
        try {
            while (rs.next()) {
                Utilisateur user = new Utilisateur(
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
                utilisateurs.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public static Utilisateur getById(int idUser) throws SQLException, ClassNotFoundException {
        Utilisateur user = null;
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT_BY_ID, new Object[]{idUser});
        try {
            if (rs.next()) {
                user = new Utilisateur(
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static int insert(Utilisateur u) throws SQLException, ClassNotFoundException {
       
        String mdpCrypte = UtilsFonction.encrypt("passer123");
        System.out.println("Mot de passe crypté : " + mdpCrypte);

        Object[] params = {
            u.getNom(),
            u.getPrenom(), 
            u.getTelephone(), 
            u.getAdresse(),
            u.getRole(),
            u.getGenre(), 
            u.getEmail(),
            UtilsFonction.encrypt(u.getMdp())
        };
        return DatabaseService.executeUpdate(SQL_INSERT, params);
    }

    public static int update(Utilisateur u) throws SQLException, ClassNotFoundException {
        Object[] params = {
            u.getNom(),
            u.getPrenom(), 
            u.getTelephone(),
            u.getAdresse(),
            u.getRole(),
            u.getGenre(), 
            u.getEmail(), 
            UtilsFonction.encrypt(u.getMdp()), 
            u.getIdUser()
        };
        return DatabaseService.executeUpdate(SQL_UPDATE, params);
    }

    public static int delete(int idUser) throws SQLException, ClassNotFoundException {
        return DatabaseService.executeUpdate(SQL_DELETE, new Object[]{idUser});
    }

public static Utilisateur connecter(String email, String motDePasseSaisi) throws SQLException, ClassNotFoundException {
    String sql = "SELECT * FROM users WHERE email = ?";
    ResultSet rs = DatabaseService.executeQuery(sql, email);

    try {
        if (rs.next()) {
            String motDePasseCrypte = rs.getString("mdp");
            String motDePasseDecrypte = UtilsFonction.decrypt(motDePasseCrypte);

            if (motDePasseDecrypte.equals(motDePasseSaisi)) {
                Utilisateur u = new Utilisateur();
                u.setIdUser(rs.getInt("idUser"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                // ajoute les autres champs si besoin
                return u;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}


}
