package repository;

import models.dto.Aeroporti;
import service.DBConnection;

import java.sql.*;

public class AeroportiRepository {

    public static int insert(Aeroporti aeroporti) throws SQLException {
        String sql = "INSERT INTO aeroporti(emri,qyteti) VALUES (?,?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, aeroporti.getEmri());
        statement.setString(2, aeroporti.getQyteti());
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        int aeroportiId;
        if (generatedKeys.next()) {
            aeroportiId = generatedKeys.getInt(1);// përdor aeroportiId në shtimin e një rekordi në tabelën aeroporti
        } else {
            throw new SQLException("Can not get id of added airport!");
        }

        return aeroportiId;
    }

    public static Aeroporti getById(int id) throws SQLException {
        String sql = "SELECT * FROM aeroporti WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String emri = resultSet.getString("emri");
                String qyteti = resultSet.getString("qyteti");
                return new Aeroporti(id, emri, qyteti);
            } else {
                return null;
            }
        }
    }

    public static void update(Aeroporti aeroporti) throws SQLException {
        String sql = "UPDATE aeroporti SET emri=?, qyteti=?  WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, aeroporti.getEmri());
            statement.setString(2, aeroporti.getQyteti());
            statement.setInt(3, aeroporti.getId());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM aeroporti WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}