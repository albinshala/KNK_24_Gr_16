package repository;

import models.Pyetje;
import service.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PyetjeRepository {

    public static void insert(Pyetje pyetje){
        try {
            String query = "INSERT INTO pyetjet (pyetja, perdoruesi_id) VALUES(?,?)";
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,pyetje.getPyetje());
            preparedStatement.setInt(2, pyetje.getPerdoruesiId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}