package dao;

import models.Ressource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RessourceDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM ressource";
    private static final String SQL_INSERT = "INSERT INTO ressource (typeRessource, quantite, unite) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE ressource SET typeRessource=?, quantite=?, unite=? WHERE idRessource=?";
    private static final String SQL_DELETE = "DELETE FROM ressource WHERE idRessource=?";

    // Convertir une ligne de la base en objet Ressource
    private static Ressource convertResultSetToRessource(ResultSet rs) throws SQLException {
        return new Ressource(
            rs.getLong("idRessource"),
            rs.getString("typeRessource"),
            rs.getDouble("quantite"),
            rs.getString("unite")
        );
    }

    // Récupérer toutes les ressources
    public static List<Ressource> getAllRessources() throws SQLException, ClassNotFoundException {
        List<Ressource> ressources = new ArrayList<>();
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT_ALL);

        while (rs.next()) {
            ressources.add(convertResultSetToRessource(rs));
        }

        return ressources;
    }

    // Ajouter une nouvelle ressource
    public static boolean addRessource(Ressource r) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_INSERT,
            r.getTypeRessource(),
            r.getQuantite(),
            r.getUnite()
        );

        return row > 0;
    }

    // Modifier une ressource
    public static boolean updateRessource(Ressource r) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_UPDATE,
            r.getTypeRessource(),
            r.getQuantite(),
            r.getUnite(),
            r.getIdRessource()
        );

        return row > 0;
    }

    // Supprimer une ressource
    public static boolean deleteRessource(long idRessource) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_DELETE, idRessource);
        return row > 0;
    }
}
