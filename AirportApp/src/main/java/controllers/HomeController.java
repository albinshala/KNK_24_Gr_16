package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import models.Rezervimi;


public class HomeController {

    @FXML
    private Hyperlink login;

    @FXML
    private ImageView albanianFlag;
    @FXML
    private ImageView americanFlag;



    void translateEnglish(){

    };
    void translateAlbanian(){

    };

    void goTo(String title, String window, ActionEvent event) throws IOException {
        Parent parenti = FXMLLoader.load(getClass().getResource(window));
        Scene scene = new Scene(parenti);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void help(ActionEvent event) throws Exception {
        goTo("Help", "help.fxml", event);
    }

    @FXML
    private void goToFluturimet(ActionEvent event) throws IOException {
        goTo("Fluturimet", "fromto.fxml", event);
    }



    @FXML
    public void goToLogin(ActionEvent event) throws Exception {
        Rezervimi.setPerdoruesi(null);
        goTo("Log In", "login.fxml", event);

    }


    @FXML
    public void goToProfile(ActionEvent event) throws Exception{
        goTo("User Profile", "userProfile.fxml", event);
    }

    @FXML
    public void goToUsers(ActionEvent event) throws Exception{
        goTo("Perdoruesit", "perdoruesit.fxml", event);
    }

    @FXML
    public void goToStats(ActionEvent event) throws Exception{
        goTo("Statistikat", "diagramet.fxml", event);
    }

    @FXML
    public void kthehu(ActionEvent event) throws Exception{
        goTo("Fluturimet", "fluturimet.fxml", event);
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        goTo("Home", "home.fxml", event);
    }
}