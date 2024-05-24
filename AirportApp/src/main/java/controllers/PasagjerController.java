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
import javafx.stage.Stage;
import models.dto.Pasagjeri;
import models.dto.Perdoruesi;
import models.dto.Rezervimi;
import service.PasagjeriService;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class PasagjerController extends HomeController implements Initializable{


    @FXML
    TextField emri, mbiemri, adresa, nacionaliteti, numriTelefonit, pasaporta;

    @FXML
    private DatePicker ditelindja;
    @FXML
    private Label TedhenatPasagjerit;
    @FXML
    private Label Emri;
    @FXML
    private Label Mbiemri;
    @FXML
    private Label Ditelindja;
    @FXML
    private Label Adresa;

    @FXML
    private Label Nacionaliteti;
    @FXML
    private Label numri_telefonit;
    @FXML
    private Label nrPasaportes;
    @FXML
    private Button next;

    @FXML
    private Button anulo;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;

    @FXML
    public void goToPagesa(ActionEvent event) throws Exception {
        String padresa = this.adresa.getText();
        String pnacionaliteti = this.nacionaliteti.getText();
        String pNumriTelefonit = this.numriTelefonit.getText();
        String pNumriPasaportes = this.pasaporta.getText();
        if (!padresa.equals("") && !pnacionaliteti.equals("") && !pNumriTelefonit.equals("")){
            Pasagjeri pasagjeri = new Pasagjeri(0,Rezervimi.getPerdoruesi().getId(), padresa, pnacionaliteti, pNumriTelefonit, pNumriPasaportes);
            //RezervimController.pasagjeriId = pasagjeri.getId();
            PagesaController.setData(pasagjeri);
            goTo("Te dhenat e rezervimit", "rezervim.fxml", event);

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "These fields should be filled!");
            alert.show();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Perdoruesi perdoruesi = Rezervimi.getPerdoruesi();
        this.emri.setText(perdoruesi.getEmri());
        this.mbiemri.setText(perdoruesi.getMbiemri());
        this.ditelindja.setValue(perdoruesi.getDitelindja().toLocalDate());

        albanianFlag.setOnMouseClicked(e->{
            translateAlbanian();
        });
        americanFlag.setOnMouseClicked(e->{
            translateEnglish();
        });

    }

    @Override
    void translateEnglish() {
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        TedhenatPasagjerit.setText(translate.getString("label.TedhenatPasagjerit"));
        Emri.setText(translate.getString("label.Emri"));
        Mbiemri.setText(translate.getString("label.Mbiemri"));
        Ditelindja.setText(translate.getString("label.Ditelindja"));
        Adresa.setText(translate.getString("label.Adresa"));
        Nacionaliteti.setText(translate.getString("label.Nacionaliteti"));
        numri_telefonit.setText(translate.getString("label.numri_telefonit"));
        nrPasaportes.setText(translate.getString("label.nrPasaportes"));
        next.setText(translate.getString("button.next"));


    }

    @Override
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        Emri.setText(translate.getString("label.Emri"));
        Mbiemri.setText(translate.getString("label.Mbiemri"));
        Ditelindja.setText(translate.getString("label.Ditelindja"));
        Adresa.setText(translate.getString("label.Adresa"));
        Nacionaliteti.setText(translate.getString("label.Nacionaliteti"));
        numri_telefonit.setText(translate.getString("label.numri_telefonit"));
        nrPasaportes.setText(translate.getString("label.nrPasaportes"));
        next.setText(translate.getString("button.next"));

    }

    @FXML
    public void anulo(ActionEvent actionEvent){
        Stage stage=(Stage) next.getScene().getWindow();
        stage.close();
    }
}


