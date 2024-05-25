package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import models.Perdoruesi;
import repository.UserRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class PerdoruesitController extends HomeController implements Initializable {

    @FXML
    private TextField filterField;

    @FXML
    private Label Fluturimett;

    @FXML
    private Button FshijPerdoruesin;

    @FXML
    private Button goBack;

    @FXML
    private Button Kerko;

    @FXML
    private TableColumn emri;

    @FXML
    private TableColumn mbiemri;

    @FXML
    private Pagination pagination;

    @FXML
    private TableView tabela;

    @FXML
    private Button TeGjitha;

    @FXML
    private TableColumn username;

    @FXML
    private TableColumn gjinia;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;


    @FXML
    void fshijPerdoruesinEPerzgjedhur(ActionEvent event) throws Exception{
        Perdoruesi perdoruesi = (Perdoruesi) tabela.getSelectionModel().getSelectedItem();
        if (perdoruesi != null){
            int id = perdoruesi.getId();
            UserRepository.delete(id);
        }
    }


    @FXML
    void kerkoStudentin(ActionEvent event) throws Exception {
        if (!filterField.getText().equals("")){
            ObservableList<Perdoruesi> perdoruesit = UserRepository.getAll(filterField.getText());

            tabela.setItems(perdoruesit);
        }

    }

    @FXML
    void shfaqTeGjithePerdoruesit(ActionEvent event) throws SQLException {
        ObservableList<Perdoruesi> perdoruesit = UserRepository.getAll("");

        tabela.setItems(perdoruesit);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        albanianFlag.setOnMouseClicked(e->{
            translateAlbanian();
        });
        americanFlag.setOnMouseClicked(e->{
            translateEnglish();
        });
        emri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        mbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        gjinia.setCellValueFactory(new PropertyValueFactory<>("gjinia"));
    }


    @Override
    void translateEnglish(){
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        Kerko.setText(translate.getString("button.Kerko"));
        TeGjitha.setText(translate.getString("button.TeGjitha"));
        FshijPerdoruesin.setText(translate.getString("button.FshijPerdoruesin"));

    }

    @Override
    void translateAlbanian(){
        Locale currentLocale = new Locale("sq");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        TeGjitha.setText(translate.getString("button.TeGjitha"));
        FshijPerdoruesin.setText(translate.getString("button.FshijPerdoruesin"));

    }
}


