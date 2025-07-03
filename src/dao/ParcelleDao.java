package dao;

import models.Parcelle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParcelleDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM parcelle";
    private static final String SQL_INSERT = "INSERT INTO parcelle (superficie, nomParcelle, pHSol, typeSol) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE parcelle SET  superficie=?, nomParcelle=?, pHSol=?, typeSol=? WHERE idParcelle=?";
    private static final String SQL_DELETE = "DELETE FROM parcelle WHERE idParcelle=?";

    private static Parcelle convertResultSetToParcelle(ResultSet rs) throws SQLException {
        return new Parcelle(
            rs.getLong("idParcelle"),
            rs.getDouble("superficie"),
            rs.getString("nomParcelle"),
            rs.getDouble("pHSol"),
            rs.getString("typeSol")
        );
    }

    public static Parcelle getParcelleById(long id) throws SQLException, ClassNotFoundException {
        String SQL_SELECT_BY_ID = "SELECT * FROM parcelle WHERE idParcelle=?";
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT_BY_ID, id);

        if(rs.next()) {
            return convertResultSetToParcelle(rs);
        }
        return null;
    }
    public static List<Parcelle> getAllParcelles() throws SQLException, ClassNotFoundException {
        
        List<Parcelle> parcelles = new ArrayList<>();
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT_ALL);

        while (rs.next()) {
            parcelles.add(convertResultSetToParcelle(rs));
        }

        return parcelles;
    }

    public static boolean addParcelle(Parcelle p) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_INSERT,
            p.getSuperficie(),
            p.getNomParcelle(),
            p.getPHSol(),
            p.getTypeSol()
        );

        return row > 0;
    }

    public static boolean updateParcelle(Parcelle p) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_UPDATE,
            p.getSuperficie(),
            p.getNomParcelle(),
            p.getPHSol(),
            p.getTypeSol(),
            p.getIdParcelle()
        );

        return row > 0;
    }

    public static boolean deleteParcelle(int idParcelle) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_DELETE, idParcelle);
        return row > 0;
    }
}

