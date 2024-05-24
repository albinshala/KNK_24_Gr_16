package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Aeroporti;
import models.Airoplani;
import models.Fluturimet;
import service.DBConnection;
import models.Perdoruesi;

import java.sql.*;

public class UserRepository {

    public static void insert(Perdoruesi user) throws SQLException {
        String sql = "INSERT INTO perdoruesit (emri,mbiemri,username,fjalekalimi_salted,salt,gjinia,admin,ditelindja) VALUES (?, ?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getEmri());
        statement.setString(2, user.getMbiemri());
        statement.setString(3, user.getUsername());
        statement.setString(4, user.getFjalekalimi_salted());
        statement.setString(5, user.getSalt());
        statement.setString(6, String.valueOf(user.getGjinia()));
        statement.setBoolean(7, user.isAdmin());
        statement.setDate(8, user.getDitelindja());
        statement.executeUpdate();
    }

    public static Perdoruesi getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM perdoruesit WHERE username=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String emri = resultSet.getString("emri");
                String mbiemri = resultSet.getString("mbiemri");
                String fjalekalimiSalted = resultSet.getString("fjalekalimi_salted");
                String salt = resultSet.getString("salt");
                char gjinia = resultSet.getString("gjinia").charAt(0);
                boolean isAdmin = resultSet.getBoolean("admin");
                Date ditelindja = resultSet.getDate("ditelindja");
                return new Perdoruesi(id, emri, mbiemri, username, fjalekalimiSalted, salt, gjinia, isAdmin, ditelindja);
            }

            return null;
        }
    }

    public static void update(Perdoruesi user) throws SQLException {
        String sql = "UPDATE perdoruesit SET username=?, emri=?, mbiemri = ?, ditelindja = ? WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmri());
            statement.setString(3, user.getMbiemri());
            statement.setDate(4, user.getDitelindja());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        }
    }
    public static void updatePassword(Perdoruesi user) throws SQLException {
        String sql = "UPDATE perdoruesit SET fjalekalimi_salted = ? WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getFjalekalimi_salted());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM perdoruesit WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }


    public static ObservableList<Perdoruesi> getAll(String fromSearch) throws SQLException {
        ObservableList<Perdoruesi> perdoruesit = FXCollections.observableArrayList();
        String sql = "";
        if(fromSearch.equals("")){
            sql = "SELECT * FROM perdoruesit WHERE admin = 0";
        }else{
            sql = "SELECT * FROM perdoruesit WHERE admin = 0 AND emri LIKE '%"+fromSearch+"%' OR mbiemri LIKE '%"+
                    fromSearch+"%' OR username LIKE '%"+fromSearch+"%'";
        }
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String  emri = resultSet.getString("emri");
            String mbiemri = resultSet.getString("mbiemri");
            String username = resultSet.getString("username");
            String fjalekalimi_salted = resultSet.getString("fjalekalimi_salted");
            String salt = resultSet.getString("salt");
            char gjinia = resultSet.getString("gjinia").charAt(0);
            boolean admin = resultSet.getBoolean("admin");
            Date ditelindja = resultSet.getDate("ditelindja");

            Perdoruesi perdoruesi = new Perdoruesi(id, emri, mbiemri, username, fjalekalimi_salted, salt, gjinia, admin, ditelindja);

            perdoruesit.add(perdoruesi);
        }
        return perdoruesit;

    }
}