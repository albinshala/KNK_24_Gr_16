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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.dto.Perdoruesi;
import models.dto.Rezervimi;
import service.UserSevice;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController extends  HomeController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Button btnLoginClick;

    @FXML
    private Button signIn;
    @FXML
    private Button signUp;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;




    @FXML
    private void goToSignUp(ActionEvent event) throws IOException {
        goTo("Sign Up", "signup.fxml", event);

    }

    @FXML
    private void loginClick(ActionEvent event) throws SQLException {
        String usernamei = this.username.getText();
        String passwordi = this.password.getText();

        Alert alert;
        try {
            Perdoruesi perdoruesi = UserSevice.login(usernamei, passwordi);
            Rezervimi.setPerdoruesi(perdoruesi);
            if (perdoruesi != null && perdoruesi.isAdmin()) {
                goTo("Fluturimet", "fluturimet.fxml", event);
            } else if(perdoruesi != null && !perdoruesi.isAdmin()){
                goTo("Home", "home.fxml", event);
            }else{
                alert = new Alert(Alert.AlertType.ERROR, "Incorrect username/password!");
                alert.show();
                password.setText("");
            }
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        }

    };


    @FXML
    public void login(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                loginClick(new ActionEvent(keyEvent.getSource(), null)); // Pass keyEvent.getSource() instead of keyEvent
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    void translateEnglish() {
        Locale currentLocale = new Locale("en");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        signIn.setText(translate.getString("button.signIn"));
        signUp.setText(translate.getString("button.signUp"));

    }

    @Override
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");

        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
        signIn.setText(translate.getString("button.signIn"));
        signUp.setText(translate.getString("button.signUp"));

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