package repository;

import models.dto.Pagesa;
import service.DBConnection;

import java.sql.*;

public class PagesaRepository {
    public static void insert(Pagesa pagesa) throws SQLException {
        String sql = "INSERT INTO pagesa (menyra_pageses,emri_kartes ,numri_kartes ,data_skadimit ,kodi_cvv ,bileta_id ) VALUES (?,?,?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, pagesa.getMenyraPageses());
        statement.setString(2, pagesa.getEmriKartes());
        statement.setString(3, pagesa.getNumriKartes());
        statement.setDate(4, pagesa.getDataSkadimit() );
        statement.setString(5, pagesa.getKodiCvv());
        statement.setInt(6, pagesa.getBiletadId());
        statement.executeUpdate();
    }

    public static Pagesa getById(int id) throws SQLException {
        String sql = "SELECT * FROM pagesa WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String menyraPageses = resultSet.getString("menyra_pageses");
                String emriKartes = resultSet.getString("emri_kartes");
                String numriKartes = resultSet.getString("numri_kartes");
                Date dataSkadimit = resultSet.getDate("data_skadimit");
                String kodiCvv = resultSet.getString("kodi_cvv");
                int biletaId = resultSet.getInt("bileta_id");
                return new Pagesa(id, menyraPageses, emriKartes, numriKartes, dataSkadimit, kodiCvv, biletaId);
            } else {
                return null;
            }
        }
    }

    public static void update(Pagesa pagesa) throws SQLException {
        String sql = "UPDATE pagesa SET menyra_pageses=?,emri_kartes = ?, numri_kartes=?,data_skadimit=?, kodi_cvv=? WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pagesa.getMenyraPageses());
            statement.setString(2, pagesa.getEmriKartes());
            statement.setString(3, pagesa.getNumriKartes());
            statement.setDate(4, pagesa.getDataSkadimit());
            statement.setString(5, pagesa.getKodiCvv());
            statement.setInt(6, pagesa.getId());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM pagesa WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}