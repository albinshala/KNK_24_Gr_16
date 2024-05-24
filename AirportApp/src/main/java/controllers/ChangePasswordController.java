package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Perdoruesi;
import service.UserSevice;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangePasswordController extends HomeController implements Initializable {

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField confirmNewPasswordField;
    private static Perdoruesi perdoruesi;
    @FXML
    private Label changePassword;
    @FXML
    private Label oldPassword;
    @FXML
    private Label newPassword;
    @FXML
    private Label confirmNewPassword;
    @FXML
    private Button saveNewPassword;
    @FXML
    private Button cancel;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;



    @FXML
    void savePass(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR,"");;
        if (!newPasswordField.getText().equals("") && !confirmNewPasswordField.getText().equals("")
                && !oldPasswordField.getText().equals("")
                && newPasswordField.getText().equals(confirmNewPasswordField.getText())){
            Perdoruesi perdoruesi1 = UserSevice.editPassword(getPerdoruesi(), oldPasswordField.getText(), newPasswordField.getText());
            if (perdoruesi1 != null ){
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Password has been changed!");
                alert.show();
            }else{
                alert.setContentText("Incorrect old password or passwords don't match!");
                alert.show();
            }
        }else{
            alert.setContentText("These fields should be filled!");
            alert.show();
        }
    }

    @FXML
    void cancel(ActionEvent event) throws SQLException {
        Stage stage =(Stage) oldPasswordField.getScene().getWindow();
        stage.close();
    }

    public static Perdoruesi getPerdoruesi() {
        return perdoruesi;
    }

    public static void setPerdoruesi(Perdoruesi perdoruesi1) {
        perdoruesi = perdoruesi1;
    }

    @Override
    void translateEnglish() {
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        changePassword.setText(translate.getString("label.changePassword"));
        oldPassword.setText(translate.getString("label.oldPasswordd"));
        newPassword.setText(translate.getString("label.newPasswordd"));
        confirmNewPassword.setText(translate.getString("label.confirmNewPassword"));
        saveNewPassword.setText(translate.getString("button.saveNewPassword"));
        cancel.setText(translate.getString("button.cancel"));

    }


    @Override
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        changePassword.setText(translate.getString("label.changePassword"));
        oldPassword.setText(translate.getString("label.oldPassword"));
        newPassword.setText(translate.getString("label.newPassword"));
        confirmNewPassword.setText(translate.getString("label.confirmNewPassword"));
        saveNewPassword.setText(translate.getString("button.saveNewPassword"));
        cancel.setText(translate.getString("button.cancel"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale.setDefault(new Locale("sq"));
        translateAlbanian();
        albanianFlag.setOnMouseClicked(e->{
            translateAlbanian();
        });
        americanFlag.setOnMouseClicked(e->{
            translateEnglish();
        });

    }
}

