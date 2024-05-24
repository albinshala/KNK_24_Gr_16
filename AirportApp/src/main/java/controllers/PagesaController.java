package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.dto.*;
import repository.BagazhetRepository;
import repository.BiletaRepository;
import repository.PagesaRepository;
import repository.RezervimiRepository;
import service.PasagjeriService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class PagesaController extends HomeController implements Initializable {

    @FXML
    private TextField cardNameField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private RadioButton creditCardRadio;

    @FXML
    private TextField cvvField;

    @FXML
    private RadioButton debitCardRadio;

    @FXML
    private DatePicker expirationDate;

    @FXML
    private ToggleGroup pagesa;

    @FXML
    private BorderPane root;
    @FXML
    private Label emriKartes;
    @FXML
    private Label nrKartes;
    @FXML
    private Label dataSkadimit;
    @FXML
    private Label kodiCVV;
    @FXML
    private Button Rezervoo;
    @FXML
    private Button anuloo;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;

    private static Pasagjeri pasagjeri;
    private static Rezervimi rezervimi;
    private static Bileta bileta;
    private static Bagazhet bagazhi;

    Alert alert = new Alert(Alert.AlertType.ERROR,"");
    public static int bId;


    @FXML
    void rezervo(ActionEvent event) throws SQLException {
        if (pagesa.getSelectedToggle() != null && expirationDate.getValue() != null && !cvvField.getText().equals("")
                && !cardNameField.getText().equals("") && !cardNumberField.getText().equals("")){
            String mp = menyraPageses();
            pasagjeri = PasagjeriService.regjistroPasagjerin(pasagjeri);
            int biletaId = BiletaRepository.insert(bileta);
            rezervimi.setBileta_id(biletaId);
            rezervimi.setPasagjeri_id(pasagjeri.getId());
            rezervimi.setPasagjeri_id(pasagjeri.getId());
            RezervimiRepository.insert(rezervimi);
            Pagesa pagesaObj = new Pagesa(0,mp, cardNameField.getText(), cardNumberField.getText(),
                    Date.valueOf(expirationDate.getValue()), cvvField.getText(),biletaId);
            PagesaRepository.insert(pagesaObj);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Reservation was made successfully!");
            alert.show();
            close();
        }else{
            alert.setContentText("These fields should be filled!");
            alert.show();
        }
    }



    String menyraPageses(){
        if (pagesa.getSelectedToggle().equals("MasterCard")){
            return "MasterCard";
        }
        return  "Visa";
    }

    @Override
    void translateEnglish(){
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        emriKartes.setText(translate.getString("label.emriKartes"));
        nrKartes.setText(translate.getString("label.nrKartes"));
        dataSkadimit.setText(translate.getString("label.dataSkadimit"));
        kodiCVV.setText(translate.getString("label.kodiCVV"));
        Rezervoo.setText(translate.getString("button.Rezervoo"));
        anuloo.setText(translate.getString("button.anuloo"));
    }
    @Override
    void translateAlbanian(){
        Locale currentLocale = new Locale("sq");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        emriKartes.setText(translate.getString("label.emriKartes"));
        nrKartes.setText(translate.getString("label.nrKartes"));
        dataSkadimit.setText(translate.getString("label.dataSkadimit"));
        kodiCVV.setText(translate.getString("label.kodiCVV"));
        Rezervoo.setText(translate.getString("button.Rezervoo"));
        anuloo.setText(translate.getString("button.anuloo"));
    }

    public static void setData(Object object){
        if (object instanceof  Pasagjeri){
            pasagjeri = (Pasagjeri) object;
        }else if(object instanceof  Rezervimi){
            rezervimi = (Rezervimi) object;
        }else if(object instanceof Bileta){
            bileta = (Bileta) object;
        }else if(object instanceof Bagazhet){
            bagazhi = (Bagazhet) object;
        }
    }

    @FXML
    void anuloRezervimin(ActionEvent actionEvent){
        close();
    }
    void close(){
        Stage stage = (Stage) cardNumberField.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        albanianFlag.setOnMouseClicked(e->{
            translateAlbanian();
        });
        americanFlag.setOnMouseClicked(e->{
            translateEnglish();
        });
    }
}

