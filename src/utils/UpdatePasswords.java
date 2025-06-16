/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import dao.DatabaseService;
import java.sql.*;

/**
 *
 * @author HP
 */
public class UpdatePasswords {
        public static void main(String[] args) {
        try {
            // Mise à jour pour l'admin
            updatePassword("rougui080601@gmail.com", "...motDePasseCrypte...");
            
            // Mise à jour pour l'agriculteur
            updatePassword("papa@gmail.com", "123");
            
            System.out.println("Mots de passe mis à jour avec succès");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void updatePassword(String email, String plainPassword) 
            throws SQLException, ClassNotFoundException {
        String encrypted = UtilsFonction.encrypt(plainPassword);
        String query = "UPDATE users SET mdp = ? WHERE email = ?";
        DatabaseService.executeUpdate(query, encrypted, email);
    }
    
}
