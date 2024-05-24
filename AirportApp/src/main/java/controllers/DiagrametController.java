package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.image.ImageView;
import service.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class DiagrametController extends HomeController implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private CategoryAxis barChartXAxis;
    @FXML
    private NumberAxis barChartYAxis;

    @FXML
    private PieChart pieChart;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        albanianFlag.setOnMouseClicked(e->{
            translateAlbanian();
        });
        americanFlag.setOnMouseClicked(e->{
            translateEnglish();
        });

        // Fetch data from the database and populate the charts
        loadData();
    }

    private void loadData() {
        try {
            Connection connection = DBConnection.getConnection();

            // Query to retrieve gender-based data for the bar chart
            String genderQuery = "SELECT gjinia, COUNT(*) AS count FROM perdoruesit GROUP BY gjinia";

            Statement genderStatement = connection.createStatement();
            ResultSet genderResultSet = genderStatement.executeQuery(genderQuery);

            // Populate the bar chart with gender data
            while (genderResultSet.next()) {
                String gender = genderResultSet.getString("gjinia");
                int count = genderResultSet.getInt("count");
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(gender, count));
                barChart.getData().add(series);
            }

            // Query to retrieve gender-based data for the pie chart
            String pieChartQuery = "SELECT status, COUNT(*) AS count FROM fluturimet GROUP BY status";

            Statement ageStatement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet ageResultSet = ageStatement.executeQuery(pieChartQuery);
            int totalCount = 0; // Total count for calculating percentages
            while (ageResultSet.next()) {
                int count = ageResultSet.getInt("count");
                totalCount += count;
            }

            // Reset cursor position
            ageResultSet.beforeFirst();


            // Populate the pie chart with gender data
            while (ageResultSet.next()) {
                String age = ageResultSet.getString("status");
                int count = ageResultSet.getInt("count");
                double percentage = (double) count / totalCount * 100; // Calculate percentage

                PieChart.Data data = new PieChart.Data(age + " (" + String.format("%.2f", percentage) + "%)", count);
                pieChart.getData().add(data);

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    void translateEnglish(){


    }
    @Override
    void translateAlbanian(){

}
}