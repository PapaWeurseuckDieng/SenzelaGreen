package dao;

import models.Produit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM produit";
    private static final String SQL_INSERT = "INSERT INTO produit (nomProduit, description, stock, prixUnitaire, idUserF, idCultureF, codeFactureF) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE produit SET nomProduit=?, description=?, stock=?, prixUnitaire=?, idUserF=?, idCultureF=?, codeFactureF=? WHERE idProduit=?";
    private static final String SQL_DELETE = "DELETE FROM produit WHERE idProduit=?";

    // Convertit une ligne de la table produit en un objet Produit
    private static Produit convertResultSetToProduit(ResultSet rs) throws SQLException {
        return new Produit(
            rs.getLong("idProduit"),
            rs.getString("nomProduit"),
            rs.getString("description"),
            rs.getDouble("stock"),
            rs.getDouble("prixUnitaire"),
            rs.getLong("idUserF"),
            rs.getLong("idCultureF"),
            rs.getString("codeFactureF")
        );
    }

    // Récupère tous les produits
    public static List<Produit> getAllProduits() throws SQLException, ClassNotFoundException {
        List<Produit> produits = new ArrayList<>();
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT_ALL);

        while (rs.next()) {
            produits.add(convertResultSetToProduit(rs));
        }

        return produits;
    }

    // Ajoute un nouveau produit
    public static boolean addProduit(Produit p) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_INSERT,
            p.getNomProduit(),
            p.getDescription(),
            p.getStock(),
            p.getPrixUnitaire(),
            p.getIdUserF(),
            p.getIdCultureF(),
            p.getCodeFactureF()
        );

        return row > 0;
    }

    // Met à jour un produit existant
    public static boolean updateProduit(Produit p) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_UPDATE,
            p.getNomProduit(),
            p.getDescription(),
            p.getStock(),
            p.getPrixUnitaire(),
            p.getIdUserF(),
            p.getIdCultureF(),
            p.getCodeFactureF(),
            p.getIdProduit()
        );

        return row > 0;
    }

    // Supprime un produit par son ID
    public static boolean deleteProduit(long idProduit) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_DELETE, idProduit);
        return row > 0;
    }
}
