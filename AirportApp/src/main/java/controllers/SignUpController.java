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
import models.Perdoruesi;
import service.UserSevice;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignUpController extends HomeController implements Initializable {
    @FXML
    private TextField emri;
    @FXML
    private TextField mbiemri;
    @FXML
    private TextField username;

    @FXML
    private PasswordField fjalekalimi;
    @FXML
    private PasswordField rishkruajFjalekalimin;
    @FXML
    private DatePicker ditelindja;

    @FXML
    private RadioButton mashkull, femer;
    @FXML
    private char pgjinia;
    @FXML
    private Label emrii;
    @FXML
    private Label mbiemrii;
    @FXML
    private Label emaili;
    @FXML
    private Label gjiniaa;
    @FXML
    private Label ditelindjaa;
    @FXML
    private Label fjalekalimii;
    @FXML
    private Label RishkruajFjalekalimin;
    @FXML
    private Button sign_up;
    @FXML
    private ToggleGroup gjinia;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;



    public void setPgjinia(char pgjinia) {
        this.pgjinia = pgjinia;
    }



    @FXML
    public void btnSignUp(ActionEvent actionEvent) throws Exception{
        String pemri = emri.getText();
        String pmbiemri = mbiemri.getText();
        String pusername = username.getText();
        String pfjalekalimi = fjalekalimi.getText();
        String prishkruajFjalekalimin = rishkruajFjalekalimin.getText();
        Date pditelindja = Date.valueOf(ditelindja.getValue());
        getGjinia();
        boolean isAdmin = false;

        if (pfjalekalimi.equals(prishkruajFjalekalimin)){
            if (UserSevice.validUsername(pusername, 0)){
                Perdoruesi user = UserSevice.signUp(pemri, pmbiemri, pusername,pfjalekalimi,
                        pgjinia,isAdmin,pditelindja);
                goTo("Log In", "login.fxml", actionEvent);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Congratulations! You have been signed up successfully.");
                alert.setTitle("Sign Up Successful");
                alert.setHeaderText(null);
                alert.show();

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid username(taken)!");
                alert.show();
            }

        }else{
            Alert  alert = new Alert(Alert.AlertType.ERROR,"Passwords don't match!");
            alert.show();
        }

    }

    public void getGjinia() {
        if(gjinia.getSelectedToggle() != null) {
            if (mashkull.isSelected() == true) {
                setPgjinia(mashkull.getText().charAt(0));
            } else {
                setPgjinia(femer.getText().charAt(0));
            }
        }
    }

    @Override
    void translateEnglish() {
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        emrii.setText(translate.getString("label.emrii"));
        mbiemrii.setText(translate.getString("label.mbiemrii"));
        emaili.setText(translate.getString("label.emaili"));
        gjiniaa.setText(translate.getString("label.gjinia"));
        ditelindjaa.setText(translate.getString("label.ditelindjaa"));
        fjalekalimii.setText(translate.getString("label.fjalekalimii"));
        RishkruajFjalekalimin.setText(translate.getString("label.RishkruajFjalekalimin"));
        sign_up.setText(translate.getString("button.sign_up"));

    }

    @Override
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        emrii.setText(translate.getString("label.emrii"));
        mbiemrii.setText(translate.getString("label.mbiemrii"));
        emaili.setText(translate.getString("label.emaili"));
        gjiniaa.setText(translate.getString("label.gjinia"));
        ditelindjaa.setText(translate.getString("label.ditelindjaa"));
        fjalekalimii.setText(translate.getString("label.fjalekalimii"));
        RishkruajFjalekalimin.setText(translate.getString("label.RishkruajFjalekalimin"));
        sign_up.setText(translate.getString("button.sign_up"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        albanianFlag.setOnMouseClicked(e->{
            translateAlbanian();
        });
        americanFlag.setOnMouseClicked(e->{
            translateEnglish();
        });
    }
}
