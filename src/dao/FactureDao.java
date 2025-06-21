package dao;

import models.Facture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FactureDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM facture";
    private static final String SQL_INSERT = "INSERT INTO facture (codeFacture, quantite, datePaiement, montantTotal, idUserF) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE facture SET quantite=?, datePaiement=?, montantTotal=?, idUserF=? WHERE codeFacture=?";
    private static final String SQL_DELETE = "DELETE FROM facture WHERE codeFacture=?";

    private static Facture convertResultSetToFacture(ResultSet rs) throws SQLException {
        return new Facture(
            rs.getString("codeFacture"),
            rs.getDouble("quantite"),
            rs.getDate("datePaiement"),
            rs.getDouble("montantTotal"),
            rs.getLong("idUserF")
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
            f.getCodeFacture(),
            f.getQuantite(),
            new java.sql.Date(f.getDatePaiement().getTime()),
            f.getMontantTotal(),
            f.getIdUserF()
        );

        return row > 0;
    }

    public static boolean updateFacture(Facture f) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_UPDATE,
            f.getQuantite(),
            new java.sql.Date(f.getDatePaiement().getTime()),
            f.getMontantTotal(),
            f.getIdUserF(),
            f.getCodeFacture()
        );

        return row > 0;
    }

    public static boolean deleteFacture(String codeFacture) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_DELETE, codeFacture);
        return row > 0;
    }
}
