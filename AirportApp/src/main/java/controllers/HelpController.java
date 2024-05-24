package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import models.dto.Pyetje;
import models.dto.Rezervimi;
import repository.PyetjeRepository;

public class HelpController extends HomeController{

    @FXML
    private ImageView albanianFlag;

    @FXML
    private ImageView americanFlag;

    @FXML
    private TextArea pyetja;
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"");


    @FXML
    public void dergoMesazhin(ActionEvent actionEvent) {
        if (!pyetja.getText().equals("") && Rezervimi.getPerdoruesi() != null){
            String pyetjeNgaPerdoruesi = pyetja.getText();
            Pyetje pyetje = new Pyetje(0, pyetjeNgaPerdoruesi,Rezervimi.getPerdoruesi().getId());
            PyetjeRepository.insert(pyetje);
            alert = new Alert(Alert.AlertType.CONFIRMATION,"Question was successfully sent!");
            alert.show();
        }else{
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Field should be filled!");
            alert.show();
        }
    }
}
