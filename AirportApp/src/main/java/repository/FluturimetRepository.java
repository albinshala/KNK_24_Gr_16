package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import models.dto.*;
import service.DBConnection;

import java.sql.*;

public class FluturimetRepository {


    public static void insert(Fluturimet fluturim) throws SQLException {
        String sql = "INSERT INTO fluturimet (aeroplani_id, aeroporti_nisjes_id, nisja, aeroporti_arritjes_id, kthimi, status, dy_drejtimeshe, kohezgjatja) VALUES (?,?,?,?,?,?,?,?);";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, fluturim.getAeroplani_id());
        statement.setInt(2, fluturim.getAeroporti_nisjes_id());
        statement.setTimestamp(3, fluturim.getNisja());
        statement.setInt(4, fluturim.getAeroporti_arritjes_id());
        statement.setTimestamp(5, fluturim.getKthimi());
        statement.setString(6, fluturim.getStatus());
        statement.setBoolean(7, fluturim.isDy_drejtimeshe());
        statement.setInt(8, fluturim.getKohezgjatja());
        statement.executeUpdate();
    }

    public static ObservableList<Fluturimet> getAll(int pageIndex, int pageSize, String fromSearch) throws Exception {
        int offset = pageIndex * pageSize; // Calculate the offset for the current page
        String sql = "";

        if (fromSearch.isEmpty()) {
            sql = "SELECT * FROM fluturimet f " +
                    "INNER JOIN aeroplanet a ON f.aeroplani_id = a.id " +
                    "INNER JOIN aeroporti nisjet ON nisjet.id = f.aeroporti_nisjes_id " +
                    "INNER JOIN aeroporti arritjet ON arritjet.id = f.aeroporti_arritjes_id " +
                    "LIMIT ? OFFSET ?";
        } else {
            sql = "SELECT * FROM fluturimet f " +
                    "INNER JOIN aeroplanet a ON f.aeroplani_id = a.id " +
                    "INNER JOIN aeroporti nisjet ON nisjet.id = f.aeroporti_nisjes_id " +
                    "INNER JOIN aeroporti arritjet ON arritjet.id = f.aeroporti_arritjes_id " +
                    "WHERE a.kompania LIKE ? OR nisjet.qyteti LIKE ? OR arritjet.qyteti LIKE ? " +
                    "LIMIT ? OFFSET ?";
        }

        ObservableList<Fluturimet> fluturimet = null;

        try ( Connection connection = DBConnection.getConnection();
              PreparedStatement statement = connection.prepareStatement(sql)) {

            if (fromSearch.isEmpty()) {
                statement.setInt(1, pageSize);
                statement.setInt(2, offset);
            } else {
                String searchParam = "%" + fromSearch + "%";
                statement.setString(1, searchParam);
                statement.setString(2, searchParam);
                statement.setString(3, searchParam);
                statement.setInt(4, pageSize);
                statement.setInt(5, offset);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                fluturimet = createObjs(resultSet);
            }
        }

        return fluturimet;
    }

    public static ObservableList<Fluturimet> getAllDistinctByCity(int n) throws SQLException {
        String sql;
        if (n == 0) {
            sql = "SELECT nisjet.qyteti AS qyteti1 " +
                    "FROM fluturimet f " +
                    "INNER JOIN aeroplanet a ON f.aeroplani_id = a.id " +
                    "INNER JOIN aeroporti nisjet ON nisjet.id = f.aeroporti_nisjes_id " +
                    "GROUP BY nisjet.qyteti";
        } else {
            sql = "SELECT arritjet.qyteti AS qyteti2 " +
                    "FROM fluturimet f " +
                    "INNER JOIN aeroplanet a ON f.aeroplani_id = a.id " +
                    "INNER JOIN aeroporti arritjet ON arritjet.id = f.aeroporti_arritjes_id " +
                    "GROUP BY arritjet.qyteti";
        }

        ObservableList<Fluturimet> fluturimet = FXCollections.observableArrayList();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Fluturimet fluturim = new Fluturimet();
                if (n == 0) {
                    fluturim.setQyteti1(resultSet.getString("qyteti1"));
                } else {
                    fluturim.setQyteti2(resultSet.getString("qyteti2"));
                }
                fluturimet.add(fluturim);
            }
        }

        return fluturimet;
    }

    public static ObservableList<Fluturimet> getAllActiveFlights(int pageIndex, int pageSize) throws Exception {
        int offset = pageIndex * pageSize;
        String sql = "SELECT * FROM fluturimet f " +
                "INNER JOIN aeroplanet a ON f.aeroplani_id = a.id " +
                "INNER JOIN aeroporti nisjet ON nisjet.id = f.aeroporti_nisjes_id " +
                "INNER JOIN aeroporti arritjet ON arritjet.id = f.aeroporti_arritjes_id " +
                "WHERE f.status = 'aktive' " +
                " LIMIT ? OFFSET ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, pageSize);
        statement.setInt(2, offset);
        ResultSet resultSet = statement.executeQuery();
        return createObjs(resultSet);
    }


    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM fluturimet WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }


    public static ObservableList<Fluturimet> getSearched(boolean dydrejtimesh, String qytet, String qyteti2, String data1, String data2) throws Exception {
        String sql = "";
        if (dydrejtimesh){
            sql = "SELECT * FROM fluturimet f \n" +
                    "INNER JOIN aeroplanet a ON f.aeroplani_id = a.id \n" +
                    "INNER JOIN aeroporti nisjet ON nisjet.id = f.aeroporti_nisjes_id \n" +
                    "INNER JOIN aeroporti arritjet ON arritjet.id = f.aeroporti_arritjes_id" +
                    " WHERE nisjet.qyteti LIKE '%" + qytet+"%' AND arritjet.qyteti LIKE '%" + qyteti2+ "%' AND DATE(f.nisja) = '"+
                    data1+"' AND DATE(f.kthimi) = '"+data2+"'";
        }else{
            sql = "SELECT * FROM fluturimet f \n" +
                    "INNER JOIN aeroplanet a ON f.aeroplani_id = a.id \n" +
                    "INNER JOIN aeroporti nisjet ON nisjet.id = f.aeroporti_nisjes_id \n" +
                    "INNER JOIN aeroporti arritjet ON arritjet.id = f.aeroporti_arritjes_id" +
                    " WHERE nisjet.qyteti LIKE '%" + qytet+"%' AND arritjet.qyteti LIKE '%" + qyteti2+ "%' AND DATE(f.nisja) = '"+
                    data1+"'";
        }
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ObservableList<Fluturimet> fluturimet = createObjs( resultSet);


        return fluturimet;
    }

    private static ObservableList<Fluturimet> createObjs(ResultSet resultSet) throws Exception {
        ObservableList<Fluturimet> fluturimet = FXCollections.observableArrayList();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int aeroplani_id = resultSet.getInt("aeroplani_id");
            int aeroporti_nisjes_id = resultSet.getInt("aeroporti_nisjes_id");
            Timestamp nisja = resultSet.getTimestamp("nisja");
            int aeroporti_arritjes_id = resultSet.getInt("aeroporti_arritjes_id");
            Timestamp kthimi = resultSet.getTimestamp("kthimi");
            String status = resultSet.getString("status");
            boolean dyDrejtimeshe = resultSet.getBoolean("dy_drejtimeshe");
            int kohezgjatja = resultSet.getInt("kohezgjatja");
            Fluturimet fluturim = new Fluturimet(id, aeroplani_id, aeroporti_nisjes_id, nisja, aeroporti_arritjes_id, kthimi, status, dyDrejtimeshe, kohezgjatja);


            aeroplani_id = resultSet.getInt("id");
            String kompania = resultSet.getString("kompania");
            int kapaciteti = resultSet.getInt("kapaciteti");
            String tipi = resultSet.getString("tipi");
            fluturim.setAiroplani(new Airoplani(aeroplani_id, kompania, kapaciteti, tipi));
            fluturim.setLinja(fluturim.getAiroplani().getKompania());


            int aeroporti_id = resultSet.getInt("nisjet.id");
            String emri = resultSet.getString("nisjet.emri");
            String qyteti = resultSet.getString("nisjet.qyteti");
            fluturim.setAeroporti1(new Aeroporti(aeroporti_id, emri, qyteti));
            fluturim.setQyteti1(fluturim.getAeroporti1().getQyteti());


            int aeroporti_id1 = resultSet.getInt("arritjet.id");
            String emri1 = resultSet.getString("arritjet.emri");
            String qyteti1 = resultSet.getString("arritjet.qyteti");
            fluturim.setAeroporti2(new Aeroporti(aeroporti_id1, emri1, qyteti1));
            fluturim.setQyteti2(fluturim.getAeroporti2().getQyteti());

            fluturimet.add(fluturim);
        }
        return fluturimet;

    }

    public static int getNrAllFlights() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM fluturimet";

        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        int totalRows = 0;
        if (resultSet.next()) {
            totalRows = resultSet.getInt("total");
        }

        resultSet.close();
        statement.close();

        return totalRows;
    }

    public static int getNrAllActives() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM fluturimet f WHERE f.status = 'aktive'";

        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        int totalRows = 0;
        if (resultSet.next()) {
            totalRows = resultSet.getInt("total");
        }

        resultSet.close();
        statement.close();

        return totalRows;
    }

}
