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
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import models.dto.Aeroporti;
import models.dto.Airoplani;
import models.dto.Fluturimet;
import models.dto.Rezervimi;
import service.FluturimService;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShtoFluturimController extends HomeController implements Initializable {

    @FXML
    private TextField statusi;
    @FXML
    private TextField aeroportiArritjes;

    @FXML
    private TextField aeroportiNisjes;

    @FXML
    private DatePicker dataKthimit;

    @FXML
    private Label dataLabel;
    @FXML
    private DatePicker dataNisjes;
    @FXML
    private ToggleGroup drejtimi;
    @FXML
    private TextField kapaciteti;
    @FXML
    private TextField kompania;
    @FXML
    private TextField oraKthimit;
    @FXML
    private TextField oraNisjes;

    @FXML
    private TextField qytetiArritjes;

    @FXML
    private TextField qytetiNisjes;


    @FXML
    private TextField kohezgjatja;

    @FXML
    private TextField tipi;
    @FXML
    private Label AeroportiNisjes;
    @FXML
    private Label QytetiNisjes;
    @FXML
    private Label AeroportiArritjes;
    @FXML
    private Label QytetiArritjes;
    @FXML
    private Label KompaniaFluturimeve;
    @FXML
    private Label KapacitetiAeroplanit;
    @FXML
    private Label TipiAeroplanit;
    @FXML
    private Label Dydrejtimeshe;
    @FXML
    private Label Kohezgjatja;
    @FXML
    private Label StatusiFluturimit;
    @FXML
    private Label dataDheOraNisjes;
    @FXML
    private Button kthehuMbrapa;
    @FXML
    private Button shto;
    @FXML
    private RadioButton Po;
    @FXML
    private RadioButton No;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;

    Alert alert = new Alert(Alert.AlertType.ERROR,"");

    @FXML
    public void shto(ActionEvent actionEvent) throws Exception {
        if (!statusi.getText().isEmpty() && !aeroportiArritjes.getText().isEmpty() && !aeroportiNisjes.getText().isEmpty()
                &&  dataNisjes.getValue() != null && !kapaciteti.getText().isEmpty() && !kompania.getText().isEmpty()
                && !oraNisjes.getText().isEmpty() && !qytetiArritjes.getText().isEmpty()
                && !qytetiNisjes.getText().isEmpty() && !kohezgjatja.getText().isEmpty() && !tipi.getText().isEmpty()) {


            int k = Integer.parseInt(kapaciteti.getText());

            Airoplani airoplani = new Airoplani(0, kompania.getText(), k ,tipi.getText());
            Aeroporti aeroportiNisjes1 = new Aeroporti(0, aeroportiNisjes.getText(), qytetiNisjes.getText());
            Aeroporti aeroportiArritjes1 = new Aeroporti(0, aeroportiArritjes.getText(), qytetiArritjes.getText());

            FluturimService.shtoObjektetTjera(airoplani, aeroportiNisjes1, aeroportiArritjes1);

            String dataN = dataNisjes.getValue().toString();
            String oraN = oraNisjes.getText();
            String timestampN = dataN +" " + oraN;

            String timestampA = "";
            Timestamp timestamp;
            if (drejtimi.getSelectedToggle().equals("2 drejtime")){
                String dataA = dataKthimit.getValue().toString();
                String oraA = oraKthimit.getText();
                timestampA = dataA +" " + oraA;
                timestamp = Timestamp.valueOf(timestampA);
            }else{
                timestampA = null;
                timestamp = null;
            }

            int duration = Integer.parseInt(kohezgjatja.getText());
            boolean drejtimi = isDyDrejtimeshe();
            Fluturimet fluturim = new Fluturimet(0, FluturimService.aeroplaniId, FluturimService.aeroportiNisjesiId, Timestamp.valueOf(timestampN),
                    FluturimService.aeroportiArritjesiId,timestamp , statusi.getText(), drejtimi,duration);

            FluturimService.shtoFluturim(fluturim);
        } else {
            alert.setContentText("These fields should be filled!");
            alert.show();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateField(kapaciteti);
        validateField(kohezgjatja);
    }

    @FXML
    public void dyDrejtimesh(ActionEvent actionEvent) {
        dataLabel.setVisible(true);
        dataKthimit.setVisible(true);
        oraKthimit.setVisible(true);
    }

    @FXML
    public void njeDrejtimesh(ActionEvent actionEvent) {
        dataLabel.setVisible(false);
        dataKthimit.setVisible(false);
        oraKthimit.setVisible(false);
    }

    void validateField(TextField field){
        field.setOnKeyPressed(e->{
            if (!e.getCode().isDigitKey() && e.getCode() != KeyCode.BACK_SPACE){
                e.consume();
                alert.setContentText("Only digits here!");
                alert.show();
            }
        });
    }

    boolean isDyDrejtimeshe(){
        if (drejtimi.getSelectedToggle().equals("PO")){
            return true;
        }
        return  false;
    }
    @Override
    void translateEnglish() {
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        AeroportiNisjes.setText(translate.getString("label.AeroportiNisjes"));
        QytetiNisjes.setText(translate.getString("label.QytetiNisjes"));
        AeroportiArritjes.setText(translate.getString("label.AeroportiArritjes"));
        QytetiArritjes.setText(translate.getString("label.QytetiArritjes"));
        KompaniaFluturimeve.setText(translate.getString("label.KompaniaFluturimeve"));
        KapacitetiAeroplanit.setText(translate.getString("label.KapacitetiAeroplanit"));
        TipiAeroplanit.setText(translate.getString("label.TipiAeroplanit"));
        Dydrejtimeshe.setText(translate.getString("label.Dydrejtimeshe"));
        Kohezgjatja.setText(translate.getString("label.Kohezgjatja"));
        StatusiFluturimit.setText(translate.getString("label.StatusiFluturimit"));
        dataDheOraNisjes.setText(translate.getString("label.dataDheOraNisjes"));
        kthehuMbrapa.setText(translate.getString("button.kthehuMbrapa"));
        shto.setText(translate.getString("button.shto"));
        Po.setText(translate.getString("radioButton.Po"));
        No.setText(translate.getString("radioButton.No"));

    }


    @Override
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        AeroportiNisjes.setText(translate.getString("label.AeroportiNisjes"));
        QytetiNisjes.setText(translate.getString("label.QytetiNisjes"));
        AeroportiArritjes.setText(translate.getString("label.AeroportiArritjes"));
        QytetiArritjes.setText(translate.getString("label.QytetiArritjes"));
        KompaniaFluturimeve.setText(translate.getString("label.KompaniaFluturimeve"));
        KapacitetiAeroplanit.setText(translate.getString("label.KapacitetiAeroplanit"));
        TipiAeroplanit.setText(translate.getString("label.TipiAeroplanit"));
        Dydrejtimeshe.setText(translate.getString("label.Dydrejtimeshe"));
        Kohezgjatja.setText(translate.getString("label.Kohezgjatja"));
        StatusiFluturimit.setText(translate.getString("label.StatusiFluturimit"));
        dataDheOraNisjes.setText(translate.getString("label.dataDheOraNisjes"));
        kthehuMbrapa.setText(translate.getString("button.kthehuMbrapa"));
        shto.setText(translate.getString("button.shto"));
        Po.setText(translate.getString("radioButton.Po"));
        No.setText(translate.getString("radioButton.No"));
    }

}
