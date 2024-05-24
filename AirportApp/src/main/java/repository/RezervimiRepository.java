package repository;

import models.dto.Rezervimi;
import service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RezervimiRepository {
    public static void insert(Rezervimi rezervim) throws SQLException {
        String sql = "INSERT INTO rezervimet (pasagjeri_id, fluturimi_id, numri_uleses, kategoria, bileta_id) VALUES (?,?,?,?,?);";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, rezervim.getPasagjeri_id());
        statement.setInt(2, rezervim.getFluturimi_id());
        statement.setInt(3, rezervim.getNumri_uleses());
        statement.setString(4, rezervim.getKategoria());
        statement.setInt(5, rezervim.getBileta_id());
        statement.executeUpdate();
    }

    public static Rezervimi getById(int id) throws SQLException {
        String sql = "SELECT * FROM rezervimet WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int pasagjeriId = resultSet.getInt("pasagjeri_id");
                int fluturimiId = resultSet.getInt("fluturimi_id");
                int numriUleses = resultSet.getInt("numri_uleses");
                String kategoria = resultSet.getString("kategoria");
                int biletaId = resultSet.getInt("bileta_id");
                return new Rezervimi(id, pasagjeriId, fluturimiId, numriUleses, kategoria, biletaId);
            }
            return null;
        }
    }


    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM rezervimet WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public static boolean isValidSeat(int seat, int fId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM rezervimet r INNER JOIN fluturimet f " +
                " ON f.id = r.fluturimi_id WHERE r.numri_uleses = ? AND r.fluturimi_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, seat);
        statement.setInt(2, fId);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            return false;
        }
        return true;
    }

}