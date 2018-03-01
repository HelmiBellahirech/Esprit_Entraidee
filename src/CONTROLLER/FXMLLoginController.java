/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Utilisateur;
import SERVICE.Covoiturage_service;
import SERVICE.ReservationcovService;
import SERVICE.UtilisateurService;
import UTILS.InputValidation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Pane pane;
    @FXML
    private Label lbl_titr;
    @FXML
    private JFXTextField Input_email;
    @FXML
    private JFXButton btn_connexion;
    @FXML
    private JFXButton btn_inscrire;
    @FXML
    private JFXPasswordField Input_pw;
    @FXML
    private Hyperlink forgetpw;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img4;

    UtilisateurService us = new UtilisateurService();
    public static final String ACCOUNT_SID = "AC652f43806e3fbc03f53fccd5fdaa9212";
    public static final String AUTH_TOKEN = "2b9eb9158e11e5cbaede12228616354b";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void on_btn_connexion(ActionEvent event) throws IOException {

        int resultatU;
        String email = Input_email.getText().toLowerCase();
        String mdp = Input_pw.getText();
        resultatU = us.login(email, mdp);
        System.out.println(resultatU);
        if (resultatU == 3) {
            esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
            esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilAdministrateur.fxml"))));
            esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
        } else if (resultatU == 0) {
            esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
            esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilUtilisateur.fxml"))));
            esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
                 ReservationcovService rs=new ReservationcovService();
        if(rs.findByReserveb(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID(), true).stream().count() != 0)
        {    
        Image img = new Image("/app.jpg");
        Notifications notificationBuilder = Notifications.create()
                .title("Covoiturage")
                .text("Demande de coivoiturage Approuvé")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
                        try {
                            esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLMesReservationCov.fxml"))));
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();}
        
        } else if (resultatU == 4) {
            esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
            esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLAccueilResponsable.fxml"))));
            esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
        } else if (resultatU == 5) {
            esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
            esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilUtilisateur.fxml"))));
            esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
        } else if (resultatU == 1) {
            Alert alertMDP = new InputValidation().getAlert("Mot de passe", "Mot de passe incorrect");
            alertMDP.showAndWait();
        } else if (resultatU == 2) {
            Alert alertEmail = new InputValidation().getAlert("Utilisateur inexistant", "Cette adresse email ne correspond à aucun utilisateur");
            alertEmail.showAndWait();
        }
        Covoiturage_service cs = new Covoiturage_service();
        cs.miseajour();
       
    }

    @FXML
    private void On_btn_inscrire(ActionEvent event) throws IOException {
        esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
        esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLSignup.fxml"))));
        esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
    }

    @FXML
    private void On_forgetpw(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mot de passe oublié ?");
        dialog.setHeaderText("Veuillez saisir votre numero de telephone");
        dialog.setContentText("Saissizer votre numero:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (InputValidation.isPhoneNumber(result.get()) == 0) {
                Alert alert = new InputValidation().getAlert("ERREUR", "Attention! Numero Invalide!");
                alert.showAndWait();
            } else {
                Utilisateur U = new Utilisateur();
                UtilisateurService us = new UtilisateurService();
                U = us.findbynum(result.get());
                if (us.findbynum(result.get()) == null) {
                    Alert alert = new InputValidation().getAlert("ERREUR", "Attention! Utilisateur n'existe pas!");
                    alert.showAndWait();
                } else {
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                    Message message = Message
                            .creator(new PhoneNumber("+21658365637"), new PhoneNumber("+18443427816"),
                                    "Votre mot de passe = " + U.getPassword())
                            .create();

                    System.out.println(message.getSid());
                    Alert alert = new InputValidation().getAlert("SUCCES", "Votre mot de passe a été envoyé à " + U.getTelephone());
                    alert.showAndWait();
                }
            }
        }
    }
}
