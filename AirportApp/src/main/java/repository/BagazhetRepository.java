package repository;

import models.Bagazhet;
import service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BagazhetRepository {

    public static void insert(Bagazhet bagazh) throws SQLException {
        System.out.println("");
        String sql = "INSERT INTO bagazhet(pasagjeri_id,numri_bagazhit, pesha_bagazhit) VALUES (?,?,?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, bagazh.getPasagjeri_Id());
        statement.setInt(2, bagazh.getNumri_bagazhit());
        statement.setDouble(3, bagazh.getPesha_bagazhit());
        statement.executeUpdate();
    }

    public static Bagazhet getById(int id) throws SQLException {
        String sql = "SELECT * FROM bagazhet WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int pasagjeri_id = resultSet.getInt("pasagjeri_id");
                int numri_bagazhit = resultSet.getInt("numri_bagazhit");
                double pesha_bagazhit = resultSet.getInt("pesha_bagazhit");
                return new Bagazhet(id, pasagjeri_id, numri_bagazhit,pesha_bagazhit);
            } else {
                return null;
            }
        }
    }

    public static void update(Bagazhet bagazh) throws SQLException {
        String sql = "UPDATE bagazhet SET numri_bagazhit=?, pesha_bagazhit=?  WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bagazh.getNumri_bagazhit());
            statement.setDouble(2, bagazh.getPesha_bagazhit());
            statement.setInt(3, bagazh.getId());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM bagazhet WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}