package repository;


import models.dto.Airoplani;
import service.DBConnection;

import java.sql.*;

public class AiroplaniRepository {
    public static int insert(Airoplani airoplani) throws SQLException {
        String sql = "INSERT INTO aeroplanet(kompania,kapaciteti, tipi) VALUES (?,?,?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, airoplani.getKompania());
        statement.setInt(2, airoplani.getKapaciteti());
        statement.setString(3, airoplani.getTipi());
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        int airoplaniId;
        if (generatedKeys.next()) {
            airoplaniId = generatedKeys.getInt(1);// përdor airoplaniId në shtimin e një rekordi në tabelën aeroplanet
        } else {
            throw new SQLException("Can not get id of added plane!");
        }

        return airoplaniId;
    }

    public static Airoplani getById(int id) throws SQLException {
        String sql = "SELECT * FROM aeroplanet WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String kompania = resultSet.getString("kompania");
                int kapaciteti = resultSet.getInt("kapaciteti");
                String tipi = resultSet.getString("tipi");
                return new Airoplani(id, kompania, kapaciteti, tipi);
            } else {
                return null;
            }
        }
    }



    public static void update(Airoplani airoplani) throws SQLException {
        String sql = "UPDATE aeroplanet SET kompania=?, kapaciteti=?, tipi=?  WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,airoplani.getKompania());
            statement.setInt(2, airoplani.getKapaciteti());
            statement.setString(3, airoplani.getTipi());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM aeroplanet WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public static boolean intoCapacity(int seat, int fId) throws SQLException {
        String sql = "SELECT a.kapaciteti FROM aeroplanet a INNER JOIN fluturimet f ON a.id = f.aeroplani_id WHERE f.id = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, fId);

        ResultSet resultSet = statement.executeQuery();
        int kapaciteti = 0;
        if (resultSet.next()){
            kapaciteti = resultSet.getInt("a.kapaciteti");
        }
        if (seat > 0 && seat <= kapaciteti){
            return true;
        }
        return false;
    }
}