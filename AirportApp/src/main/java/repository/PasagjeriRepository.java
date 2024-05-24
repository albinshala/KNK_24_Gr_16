package repository;

import models.Pasagjeri;
import models.Perdoruesi;
import models.Rezervimi;
import service.DBConnection;

import java.sql.*;

public class PasagjeriRepository {
    public static void insert(Pasagjeri pasagjeri) throws SQLException {
        String sql = "INSERT INTO pasagjeret (perdoruesi_id,adresa,nacionaliteti,numri_telefonit, numri_pasaportes) VALUES (?, ?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Rezervimi.getPerdoruesi().getId());
        statement.setString(2, pasagjeri.getAdresa());
        statement.setString(3, pasagjeri.getNacionaliteti());
        statement.setString(4, pasagjeri.getNumri_telefonit());
        statement.setString(5, pasagjeri.getNumri_pasaportes());
        statement.executeUpdate();
    }

    public static Pasagjeri getByPId(int pid) throws SQLException {
        String sql = "SELECT * FROM pasagjeret WHERE perdoruesi_id=? ORDER BY id DESC LIMIT 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int perdoruesiId = resultSet.getInt("perdoruesi_id");
                String adresa = resultSet.getString("adresa");
                String nacionaliteti = resultSet.getString("nacionaliteti");
                String numriTelefonit = resultSet.getString("numri_telefonit");
                String numriPasaportes = resultSet.getString("numri_pasaportes");

                return new Pasagjeri(id, perdoruesiId, adresa, nacionaliteti, numriTelefonit,numriPasaportes);
            }

            return null;
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM pasagjeret WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
