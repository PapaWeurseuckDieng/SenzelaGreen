package dao;

import models.Facture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FactureDao {
    private static final String SQL_SELECT_ALL = "SELECT f.*, c.nomClient, c.prenomClient FROM facture f JOIN client c ON f.idClientF = c.idClient";
    private static final String SQL_INSERT = "INSERT INTO facture (nomProduit, quantite, montantTotal, modePaiement, idClientF) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE facture SET nomProduit=?, quantite=?, montantTotal=?, modePaiement=?, idClientF=? WHERE idFacture=?";
    private static final String SQL_DELETE = "DELETE FROM facture WHERE idFacture=?";
    private static final String SQL_GET_CLIENTS = "SELECT idClient, CONCAT(nomClient, ' ', prenomClient) AS nomComplet FROM client";

    private static Facture convertResultSetToFacture(ResultSet rs) throws SQLException {
        return new Facture(
            rs.getLong("idFacture"),
            rs.getString("nomProduit"),
            rs.getDouble("quantite"),
            rs.getDouble("montantTotal"),
            rs.getString("modePaiement"),
            rs.getLong("idClientF"),
            rs.getString("nomClient") + " " + rs.getString("prenomClient")
        );
    }

    public static List<Facture> getAllFactures() throws SQLException, ClassNotFoundException {
        List<Facture> factures = new ArrayList<>();
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT_ALL);

        while (rs.next()) {
            factures.add(convertResultSetToFacture(rs));
        }

        return factures;
    }

    public static boolean addFacture(Facture f) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_INSERT,
            f.getNomProduit(),
            f.getQuantite(),
            f.getMontantTotal(),
            f.getModePaiement(),
            f.getIdClientF()
        );

        return row > 0;
    }

    public static boolean updateFacture(Facture f) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_UPDATE,
            f.getNomProduit(),
            f.getQuantite(),
            f.getMontantTotal(),
            f.getModePaiement(),
            f.getIdClientF(),
            f.getIdFacture()
        );

        return row > 0;
    }

    public static boolean deleteFacture(long idFacture) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_DELETE, idFacture);
        return row > 0;
    }

    public static List<String[]> getClientsForComboBox() throws SQLException, ClassNotFoundException {
        List<String[]> clients = new ArrayList<>();
        ResultSet rs = DatabaseService.executeQuery(SQL_GET_CLIENTS);

        while (rs.next()) {
            clients.add(new String[]{
                rs.getString("idClient"),
                rs.getString("nomComplet")
            });
        }

        return clients;
    }

    
}
