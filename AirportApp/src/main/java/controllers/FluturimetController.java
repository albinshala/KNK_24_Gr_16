package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import models.Fluturimet;
import repository.FluturimetRepository;

public class FluturimetController extends HomeController implements Initializable {
    @FXML
    private TableColumn  nisja, kthimi, vendi_nisjes, vendi_arritjes, statusi,linja;
    @FXML
    private TableView tabela;
    @FXML
    private CheckBox checkBoxFilterIsActive;
    @FXML
    private TextField filterField;
    @FXML
    private Label fluturimett;
    @FXML
    private Button kerko;
    @FXML
    private Button teGjitha;
    @FXML
    private Button fshijFluturimin;
    @FXML
    private Button shtoFluturim;
    @FXML
    private Button menaxhoPerdoruesit;
    @FXML
    private Button shikoDiagramet;
    @FXML
    private Button goBack;
    @FXML
    private Pagination pagination;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;
    private int rowsPerPage = 6;
    ObservableList<Fluturimet> fluturimet = null;
    private Alert alert = new Alert(Alert.AlertType.ERROR,"");

    public void btnFilter(ActionEvent actionEvent) {
    }


    @FXML
    public void shfaqTeGjithaFluturimet(ActionEvent actionEvent) throws Exception {
        if (checkBoxFilterIsActive.isSelected()) {
            checkBoxFilterIsActive.setSelected(false);
        }
        int totalItems = FluturimetRepository.getNrAllFlights(); // Get the total number of items from the repository
        System.out.println(totalItems);
        int totalPages = (int) Math.ceil((double) totalItems / rowsPerPage); // Calculate the total number of pages

        pagination.setPageCount(totalPages); // Set the total number of pages in the pagination control

        pagination.setPageFactory(pageIndex -> {
            try {
                fluturimet = FluturimetRepository.getAll(pageIndex, rowsPerPage, ""); // Get data for the current page
                tabela.setItems(fluturimet);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tabela;
        });
    }

    @FXML
    public void active(ActionEvent actionEvent) throws Exception {
        int totalItems = FluturimetRepository.getNrAllActives(); // Get the total number of items from the repository
        int totalPages = (int) Math.ceil((double) totalItems / rowsPerPage); // Calculate the total number of pages

        pagination.setPageCount(totalPages); // Set the total number of pages in the pagination control
        if (checkBoxFilterIsActive.isSelected()) {
            pagination.setPageFactory(pageIndex -> {
                try {
                    fluturimet = FluturimetRepository.getAllActiveFlights(pageIndex , rowsPerPage); // Get data for the current page
                    tabela.setItems(fluturimet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tabela;
            });
        }
    }

    @FXML
    public void kerkoFluturimin(ActionEvent actionEvent) throws Exception {
        if (!filterField.getText().equals("")) {
            String fromSearch = filterField.getText();
            int totalItems = FluturimetRepository.getNrAllFlights(); // Get the total number of items from the repository
            int totalPages = (int) Math.ceil((double) totalItems / rowsPerPage); // Calculate the total number of pages

            pagination.setPageCount(totalPages); // Set the total number of pages in the pagination control
            pagination.setPageFactory(pageIndex -> {
                try {
                    fluturimet = FluturimetRepository.getAll(pageIndex, rowsPerPage, fromSearch); // Get data for the current page
                    tabela.setItems(fluturimet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tabela;
            });
        }
    }

    @Override
    void translateEnglish() {
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        fluturimett.setText(translate.getString("label.fluturimett"));
        kerko.setText(translate.getString("button.kerko"));
        teGjitha.setText(translate.getString("button.teGjitha"));
        fshijFluturimin.setText(translate.getString("button.fshijFluturimin"));
        shtoFluturim.setText(translate.getString("button.shtoFluturim"));
        goBack.setText(translate.getString("button.goBack"));


    }

    @Override
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        fluturimett.setText(translate.getString("label.fluturimett"));
        kerko.setText(translate.getString("button.kerko"));
        teGjitha.setText(translate.getString("button.teGjitha"));
        fshijFluturimin.setText(translate.getString("button.fshijFluturimin"));
        shtoFluturim.setText(translate.getString("button.shtoFluturim"));
        goBack.setText(translate.getString("button.goBack"));


    }


    @FXML
    public void shtoFluturim(ActionEvent actionEvent) throws Exception {
        goTo("Shto fluturim", "shtoFluturim.fxml", actionEvent);


    }

    @FXML
    public void fshijFluturiminEPerzgjedhur(ActionEvent actionEvent) throws SQLException {
        Fluturimet fluturimet = (Fluturimet) tabela.getSelectionModel().getSelectedItem();
        if (fluturimet != null){
            FluturimetRepository.delete(fluturimet.getId());
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Flight has been deleted successfully!");
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        albanianFlag.setOnMouseClicked(e->{
            translateAlbanian();
        });
        americanFlag.setOnMouseClicked(e->{
            translateEnglish();
        });
        linja.setCellValueFactory(new PropertyValueFactory<>("linja"));
        nisja.setCellValueFactory(new PropertyValueFactory<>("nisja"));
        kthimi.setCellValueFactory(new PropertyValueFactory<>("kthimi"));
        statusi.setCellValueFactory(new PropertyValueFactory<>("status"));
        vendi_nisjes.setCellValueFactory(new PropertyValueFactory<>("qyteti1"));
        vendi_arritjes.setCellValueFactory(new PropertyValueFactory<>("qyteti2"));
    }

}