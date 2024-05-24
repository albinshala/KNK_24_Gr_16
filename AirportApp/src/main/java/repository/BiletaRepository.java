package repository;

import service.DBConnection;
import models.Bileta;

import java.sql.*;

public class BiletaRepository {

    public static int insert(Bileta bileta) throws SQLException {
        String sql = "INSERT INTO bileta (çmimi) VALUES (?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, bileta.getÇmimi());
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        int biletaId;
        if (generatedKeys.next()) {
            biletaId = generatedKeys.getInt(1);// përdor biletaId në shtimin e një rekordi në tabelën rezervimet
        } else {
            throw new SQLException("Can not get id of added ticket!");
        }

        return biletaId;
    }

    public static Bileta getById(int id) throws SQLException {
        String sql = "SELECT * FROM bileta WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int cmimi = resultSet.getInt("çmimi");
                return new Bileta(id, cmimi);
            } else {
                return null;
            }
        }
    }

    public static void update(Bileta bileta) throws SQLException {
        String sql = "UPDATE bileta SET cmimi=? WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bileta.getÇmimi());
            statement.setInt(2, bileta.getId());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM bileta WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
 }
}
}