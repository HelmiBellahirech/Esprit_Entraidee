/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Etudiant;
import MODEL.Utilisateur;
import SERVICE.UtilisateurService;
import UTILS.InputValidation;
import UTILS.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLSignUpController implements Initializable {

    @FXML
    private JFXTextField NomInput;
    @FXML
    private JFXTextField PrenomInput;
    @FXML
    private JFXTextField EmailInput;
    @FXML
    private JFXPasswordField mdpInput;
    @FXML
    private JFXPasswordField verifmdpInput;
    @FXML
    private JFXTextField telInput;
    private JFXTextField sexeInput;
    @FXML
    private JFXTextField usernameInput;
    private JFXTextField ClasseInput;
    @FXML
    private JFXComboBox<String> SexeCombobox;
    ObservableList<String> Sexesoption
            = FXCollections.observableArrayList("Homme", "Femme");
    @FXML
    private JFXComboBox<String> ClasseCombobox;
    ObservableList<String> Classeoption
            = FXCollections.observableArrayList("1ere Année", "2eme Année", "3eme Année", "4eme Année", "5eme Année");
    @FXML
    private JFXRadioButton OuiVoiture;
    @FXML
    private ToggleGroup MyGroup;
    @FXML
    private JFXRadioButton NonVoiture;
    @FXML
    private Label matriculelbl;
    @FXML
    private JFXTextField MatriculeInput;
    @FXML
    private JFXButton ImportPhoto;
    @FXML
    private JFXButton Inscrire;
    UtilisateurService c = new UtilisateurService();
    private File file = null;

    private Image imagelog;
    private FileChooser fileChooser = new FileChooser();
    private static String uuid;
    final Stage stage = new Stage();
    @FXML
    private ImageView image;
    @FXML
    private JFXButton login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClasseCombobox.setItems(Classeoption);
        SexeCombobox.setItems(Sexesoption);

        MatriculeInput.setVisible(false);
        matriculelbl.setVisible(false);
        SexeCombobox.setValue("Homme");
        ClasseCombobox.setValue("1ere Année");
        MatriculeInput.setPromptText("147TUN2015");

    }

    @FXML
    private void On_inscrire_btn(ActionEvent event) throws IOException {

        String Nom = NomInput.getText();
        String Prenom = PrenomInput.getText();
        String Email = EmailInput.getText();
        String Motdepasse = mdpInput.getText();
        String ValidMotDePasse = verifmdpInput.getText();
        String Username = usernameInput.getText();
        String NumTel = telInput.getText();
        String Sexe = SexeCombobox.getValue();

        String Classe = ClasseCombobox.getValue();
        String Matricule = MatriculeInput.getText();

        if (InputValidation.validTextField(Nom)) {
            Alert alertNom = new InputValidation().getAlert("Nom", "Saisissez votre nom");
            alertNom.showAndWait();
        } else {

            if (InputValidation.validTextField(Prenom)) {
                Alert alertPrenom = new InputValidation().getAlert("Prenom", "Saisissez votre Prenom");
                alertPrenom.showAndWait();
            } else {

                if (!InputValidation.validEmail(Email)) {
                    Alert alertEmail = new InputValidation().getAlert("Email", "Saisissez une adresse email valide");
                    alertEmail.showAndWait();
                } else {

                    Integer verif = InputValidation.validPwd(Motdepasse, ValidMotDePasse);
                    if (verif == 0) {
                        Alert alertMDP = new InputValidation().getAlert("Mot de passe", "Les mots de passe ne correspondent pas.");
                        alertMDP.showAndWait();
                    } else {

                        if (!InputValidation.validUsername(Username)) {
                            Alert alertUsername = new InputValidation().getAlert("Nom d'utilisateur", "Saisissez un nom d'utilisateur valide");
                            alertUsername.showAndWait();
                        } else {
                            if (InputValidation.isPhoneNumber(NumTel) == 0) {
                                Alert alertnum = new InputValidation().getAlert("Numero Telephone", "Saisissez un numero de telephone valide");
                                alertnum.showAndWait();
                            } else {
                                Utilisateur u = new Etudiant(Email, Username, Motdepasse, Nom, Prenom, NumTel, Sexe, Classe, Matricule, 0);

                                u.setPhoto(uuid);
                                if ((u.getPhoto() == null) && (u.getSexe().equals("Homme"))) {

                                    u.setPhoto("user.png");

                                } else if ((u.getPhoto() == null) && (u.getSexe().equals("Femme"))) {

                                    u.setPhoto("userf.png");
                                }
                                int existe = c.addUser(u);

                                if (existe == 1) {
                                    Alert alertEmail = new InputValidation().getAlert("Email", "Cet email existe déjà !");
                                    alertEmail.showAndWait();
                                } else if (existe == 2) {
                                    Alert alertUsername = new InputValidation().getAlert("Username", "Ce nom d'utilisateur existe déjà ");
                                    alertUsername.showAndWait();
                                } else if (existe == 0) {
                                    Alert alert = new InputValidation().getAlert("Succes", "Bienvenue a Esprit Entr'aide");
                                    alert.showAndWait();
                                    esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
                                    esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml"))));
                                    esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
                                }

                            }

                        }
                    }
                }
            }
        }

    }

    @FXML
    private void btnImageAction(ActionEvent event) throws IOException {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            uuid = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            imagelog = new Image(file.toURI().toString());
            image.setImage(imagelog);
            //   Copy c = new Copy();
            //C:\\wamp\\www\\images\\utilisateur\\
            Utils u = new Utils();
            String emplacement = "C:\\xampp\\htdocs\\pi\\" + uuid;
            System.out.println(emplacement);
            u.CopyImage(emplacement, file.toPath().toString());
            System.out.println("rrrrr");

            // c.CopyImage(emplacement, file.toPath().toString());
        }
    }

    @FXML
    private void OuiVoiture(MouseEvent event) {

        MatriculeInput.setVisible(true);
        matriculelbl.setVisible(true);
        System.out.println("hi");

    }

    @FXML
    private void NonVoiture(MouseEvent event) {

        MatriculeInput.setVisible(false);
        matriculelbl.setVisible(false);
        System.out.println("hi");

    }

    @FXML
    private void On_btn_Login(ActionEvent event) throws IOException {
        esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
        esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml"))));
        esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
    }
}
