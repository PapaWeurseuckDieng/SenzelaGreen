package dao;

import models.Culture;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CultureDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM culture";
    private static final String SQL_INSERT = "INSERT INTO culture (nomCulture, description, typeCulture, cycleCulture, dateDebut, dateFin, rendementAttendu) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE culture SET nomCulture=?, description=?, typeCulture=?, cycleCulture=?, dateDebut=?, dateFin=?, rendementAttendu=? WHERE idCulture=?";
    private static final String SQL_DELETE = "DELETE FROM culture WHERE idCulture=?";

    private static Culture convertResultSetToCulture(ResultSet rs) throws SQLException {
        return new Culture(
            String.valueOf(rs.getLong("idCulture")), // Conversion explicite
            rs.getString("nomCulture"),
            rs.getString("description"),
            rs.getString("typeCulture"),
            rs.getString("cycleCulture"),
            rs.getDate("dateDebut"),
            rs.getDate("dateFin"),
            rs.getDouble("rendementAttendu"),
            null // le champ "stade" est manquant dans la requête et dans les paramètres
        );
    }

    public static List<Culture> getAllCultures() throws SQLException, ClassNotFoundException {
        List<Culture> cultures = new ArrayList<>();
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT_ALL);

        while (rs.next()) {
            cultures.add(convertResultSetToCulture(rs));
        }

        return cultures;
    }

    public static boolean addCulture(Culture c) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_INSERT,
            c.getNomCulture(),
            c.getDescription(),
            c.getTypeCulture(),
            c.getCycleCulture(),
            new java.sql.Date(c.getDateDebut().getTime()),
            new java.sql.Date(c.getDateFin().getTime()),
            c.getRendementAttendu()
        );

        return row > 0;
    }

    public static boolean updateCulture(Culture c) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_UPDATE,
            c.getNomCulture(),
            c.getDescription(),
            c.getTypeCulture(),
            c.getCycleCulture(),
            new java.sql.Date(c.getDateDebut().getTime()),
            new java.sql.Date(c.getDateFin().getTime()),
            c.getRendementAttendu(),
            c.getIdCulture()
        );

        return row > 0;
    }

    public static boolean deleteCulture(int idCulture) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_DELETE, idCulture);
        return row > 0;
    }
}
