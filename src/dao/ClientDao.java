package dao;

import models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM client";
    private static final String SQL_INSERT = "INSERT INTO client (nomClient, prenomClient, adresse, telephone) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE client SET  nomClient=?, prenomClient=?, adresse=?, telephone=? WHERE idClient=?";
    private static final String SQL_DELETE = "DELETE FROM client WHERE idClient=?";

    private static Client convertResultSetToClient(ResultSet rs) throws SQLException {
        return new Client(
            rs.getLong("idClient"),
            rs.getString("nomClient"),
            rs.getString("prenomClient"),
            rs.getString("adresse"),
            rs.getString("telephone")
        );
    }

    public static Client getClientById(long id) throws SQLException, ClassNotFoundException {
        String SQL_SELECT_BY_ID = "SELECT * FROM client WHERE idClient=?";
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT_BY_ID, id);

        if(rs.next()) {
            return convertResultSetToClient(rs);
        }
        return null;
    }
    public static List<Client> getAllClients() throws SQLException, ClassNotFoundException {
        
        List<Client> clients = new ArrayList<>();
        ResultSet rs = DatabaseService.executeQuery(SQL_SELECT_ALL);

        while (rs.next()) {
            clients.add(convertResultSetToClient(rs));
        }

        return clients;
    }

    public static boolean addClient(Client c) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_INSERT,
            c.getNomClient(),
            c.getPrenomClient(),
            c.getAdresse(),
            c.getTelephone()
        );

        return row > 0;
    }

    public static boolean updateClient(Client c) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_UPDATE,
            c.getNomClient(),
            c.getPrenomClient(),
            c.getAdresse(),
            c.getTelephone(),
            c.getIdClient()
        );

        return row > 0;
    }

    public static boolean deleteClient(long idClient) throws SQLException, ClassNotFoundException {
        int row = DatabaseService.executeUpdate(SQL_DELETE, idClient);
        return row > 0;
    }
}

