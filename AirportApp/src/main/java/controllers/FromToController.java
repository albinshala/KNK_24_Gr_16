package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Fluturimet;
import models.Rezervimi;
import repository.FluturimetRepository;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

public class FromToController extends HomeController implements Initializable {

    @FXML
    private ChoiceBox arrivalCityChoiceBox;

    @FXML
    private ChoiceBox departingCityChoiceBox;

    @FXML
    private DatePicker departureDatePicker;
    @FXML
    private TableColumn kthimi;

    @FXML
    private TableColumn linja;

    @FXML
    private TableColumn nisja;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private TableColumn rezervimi;

    @FXML
    private TableColumn statusi;

    @FXML
    private TableView tabela;

    @FXML
    private TableColumn vendi_arritjes;
    @FXML
    private ToggleGroup drejtimi;

    @FXML
    private TableColumn vendi_nisjes;
    @FXML
    private Label nga;
    @FXML
    private Label ne;
    @FXML
    private Label dyDrejtimeshe;
    @FXML
    private RadioButton po;
    @FXML
    private RadioButton jo;
    @FXML
    private Label nisjaa;
    @FXML
    private Label kthimi_Label;
    @FXML
    private Button filtro;
    @FXML
    private Button cancell;
    @FXML
    private Button Rezervo;
    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;

    private boolean dyDrejtimeshi = true;

    public static int fId;

    @Override
    void translateEnglish() {
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content_en", currentLocale);
        nga.setText(translate.getString("label.nga"));
        ne.setText(translate.getString("label.ne"));
        dyDrejtimeshe.setText(translate.getString("label.dyDrejtimeshe"));
        po.setText(translate.getString("radiobutton.po"));
        jo.setText(translate.getString("radiobutton.jo"));
        nisjaa.setText(translate.getString("label.nisjaa"));
        kthimi_Label.setText(translate.getString("label.kthimi_Label"));
        filtro.setText(translate.getString("button.filtro"));
        cancell.setText(translate.getString("button.cancell"));
        Rezervo.setText(translate.getString("button.Rezervo"));


    }

    @Override
    void translateAlbanian() {
        Locale currentLocale = Locale.getDefault();

        ResourceBundle translate = ResourceBundle.getBundle("translation.content_sq", currentLocale);
        nga.setText(translate.getString("label.nga"));
        ne.setText(translate.getString("label.ne"));
        dyDrejtimeshe.setText(translate.getString("label.dyDrejtimeshe"));
        po.setText(translate.getString("radiobutton.po"));
        jo.setText(translate.getString("radiobutton.jo"));
        nisjaa.setText(translate.getString("label.nisjaa"));
        kthimi_Label.setText(translate.getString("label.kthimi_Label"));
        filtro.setText(translate.getString("button.filtro"));
        cancell.setText(translate.getString("button.cancell"));
        Rezervo.setText(translate.getString("button.Rezervo"));

    }

    @FXML
    void handleFilterAction(ActionEvent event) throws Exception {
        tabela.getItems().clear();

        String d = "";
        String dc = "";
        String rc = "";
        ObservableList<FluturimetController> list = null;
        if (dyDrejtimeshi){
            if (departureDatePicker.getValue()!= null && returnDatePicker.getValue() != null
                    && departingCityChoiceBox.getValue() != null && arrivalCityChoiceBox.getValue() != null && drejtimi.getSelectedToggle().isSelected()){

                d = departureDatePicker.getValue().toString();
                dc = departingCityChoiceBox.getValue().toString();
                rc = arrivalCityChoiceBox.getValue().toString();

                String r = returnDatePicker.getValue().toString();
                list = FluturimetRepository.getSearched(dyDrejtimeshi,dc, rc, d, r);
                setInTable(list);
            }
        }
        else {
            if (departureDatePicker.getValue() != null && departingCityChoiceBox.getValue() != null
                    && arrivalCityChoiceBox.getValue() != null) {
                d = departureDatePicker.getValue().toString();
                dc = departingCityChoiceBox.getValue().toString();
                rc = arrivalCityChoiceBox.getValue().toString();
                list = FluturimetRepository.getSearched(dyDrejtimeshi, dc, rc, d, "");
                setInTable(list);

            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

        Locale.setDefault(new Locale("sq"));
        translateAlbanian();
        albanianFlag.setOnMouseClicked(e->{
            translateAlbanian();
        });
        americanFlag.setOnMouseClicked(e->{
            translateEnglish();
        });
        try {
            ObservableList<FluturimetController> list1 = FluturimetRepository.getAllDistinctByCity(0);
            ObservableList<FluturimetController> list2 = FluturimetRepository.getAllDistinctByCity(1);
            Collections.sort(list1, Comparator.comparing(FluturimetController::getQyteti1));
            Collections.sort(list2, Comparator.comparing(FluturimetController::getQyteti2));

            for (FluturimetController fluturim: list1 ) {
                departingCityChoiceBox.getItems().add(fluturim.getQyteti1());
            }

            for (FluturimetController fluturim: list2 ) {
                arrivalCityChoiceBox.getItems().add(fluturim.getQyteti2());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void njeDrejtimesh(ActionEvent actionEvent) {
        this.dyDrejtimeshi = false;
        returnDatePicker.setVisible(false);
        kthimi_Label.setVisible(false);
    }

    @FXML
    public void dyDrejtimesh(ActionEvent actionEvent) {
        this.dyDrejtimeshi = true;
        returnDatePicker.setVisible(true);
        kthimi_Label.setVisible(true);
    }

    @FXML
    public void rezervo(ActionEvent actionEvent) {

        // Get the selected row
        FluturimetController selectedObject = (FluturimetController) tabela.getSelectionModel().getSelectedItem();

        // Get the ID of the selected row
        if (selectedObject != null){
            fId = selectedObject.getId();
            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pasagjer.fxml"));
            try {
                Parent root = fxmlLoader.load();
                PasagjerController pasagjerController = fxmlLoader.getController();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setTitle("Pasagjeri");
                stage.setScene(scene);
                stage.show();

            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }

    }

    private void setInTable(ObservableList<FluturimetController> list){
        linja.setCellValueFactory(new PropertyValueFactory<>("linja"));
        nisja.setCellValueFactory(new PropertyValueFactory<>("nisja"));
        kthimi.setCellValueFactory(new PropertyValueFactory<>("kthimi"));
        statusi.setCellValueFactory(new PropertyValueFactory<>("status"));
        vendi_nisjes.setCellValueFactory(new PropertyValueFactory<>("qyteti1"));
        vendi_arritjes.setCellValueFactory(new PropertyValueFactory<>("qyteti2"));

        tabela.setItems(list);
    }


}
