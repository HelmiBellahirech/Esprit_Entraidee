/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Etudiant;
import SERVICE.EtudiantService;
import SERVICE.UtilisateurService;
import UTILS.InputValidation;
import UTILS.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLProfilUtilisateurController implements Initializable {

    @FXML
    private JFXButton Retour;
    @FXML
    private Label NomPrenom;
    @FXML
    private Label Classelbl;
    @FXML
    private JFXTextField UsernameInput;
    @FXML
    private JFXTextField EmailInput;
    private JFXPasswordField AnMdp;
    @FXML
    private JFXTextField TelInput;
    @FXML
    private JFXPasswordField Mdp;
    @FXML
    private JFXPasswordField ValidMdp;
    @FXML
    private JFXButton Update;
    @FXML
    private JFXButton Desactivez;
    @FXML
    private JFXButton Annonce;
    @FXML
    private Label Date_Creation;
    @FXML
    private Label Mdplbl;
    @FXML
    private Label ValidMdplbl;
    private Label AnMdplbl;
    @FXML
    private ImageView img;
    private Label UsernameInput1;
    private Label EmailInput1;
    private Label TelInput1;
    @FXML
    private AnchorPane rootpane;
    UtilisateurService us = new UtilisateurService();
    EtudiantService es = new EtudiantService();
    private JFXCheckBox CheckMdp;

    private File file = null;

    private Image imagelog;
    private FileChooser fileChooser = new FileChooser();
    private static String uuid;
    final Stage stage = new Stage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id = esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID();

        Etudiant u = null;
        u = es.findId(id);

        ValidMdp.setVisible(false);
        ValidMdplbl.setVisible(false);

        Date_Creation.setText("Compte Crée le " + esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getDate_Creation());
        NomPrenom.setText(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getNom() + " " + esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getPrenom());
        if (esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser() instanceof Etudiant) {
            Etudiant e = (Etudiant) esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser();
            Classelbl.setText(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getRole() + " en " + e.getClasse());
            UsernameInput.setText(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getUsername());

            File f = new File("C:\\xampp\\htdocs\\pi\\" + u.getPhoto());
            System.out.println(f.toURI().toString());

            Image IMAGE_RUBY = new Image(f.toURI().toString());
            //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

            img.setFitHeight(100);
            img.setFitWidth(100);
            Rectangle clip = new Rectangle(
                    img.getFitWidth(), img.getFitHeight()
            );

            clip.setArcWidth(20);
            clip.setArcHeight(20);
            img.setClip(clip);

            // snapshot the rounded image.
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage image = img.snapshot(parameters, null);

            // remove the rounding clip so that our effect can show through.
            img.setClip(null);

            // apply a shadow effect.
            img.setEffect(new DropShadow(20, Color.BLACK));

            img.setImage(IMAGE_RUBY);
            EmailInput.setText(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getEmail());
            TelInput.setText(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getTelephone());

        }

    }

    @FXML
    private void On_btn_Retour(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilUtilisateur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Modifier(ActionEvent event) {

        Etudiant u;
        u = (Etudiant) esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser();

        String mdp = Mdp.getText();
        String Vmdp = ValidMdp.getText();
        String AncienMdp = esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getPassword();
        String email = EmailInput.getText();
        String Username = UsernameInput.getText();
        String Telephone = TelInput.getText();

        u.setEmail(email);
        u.setTelephone(Telephone);
        u.setUsername(Username);
        u.setPhoto(uuid);
        if (Mdp.getText().equals("")) {
            Mdp.setPromptText("Champ Obligatoire");
        } else {
            if (InputValidation.validPwd(mdp, Vmdp) == 0) {
                Alert alertMDP = new InputValidation().getAlert("Mot de passe", "Les mots de passe ne correspondent pas.");
                alertMDP.showAndWait();
            } else {
                u.setPassword(mdp);

            }

            es.update(u);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification Profil");
            alert.setHeaderText(null);
            alert.setContentText("Modification aboutie! \n" + "Redirection au menu principal");

            alert.showAndWait();
            AnchorPane pane = new AnchorPane();
            try {
                pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilUtilisateur.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(FXMLAjoutCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            rootpane.getChildren().setAll(pane);

        }
    }

    @FXML
    private void On_btn_Desactiver(ActionEvent event) throws IOException {
        UtilisateurService cs = new UtilisateurService();

        Alert alert = new Alert(AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer votre compte " + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            cs.remove(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID());
            esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
            esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml"))));
            esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
            Alert alert1 = new Alert(AlertType.INFORMATION, "Votre compte a été supprimer", ButtonType.OK);
            alert1.setTitle("Supprission du compte");

            alert1.showAndWait();
        }

    }

    private void On_Username_Clicked(MouseEvent event) {
        UsernameInput.setVisible(true);
        Update.setVisible(true);
        Mdp.setVisible(true);
        Mdplbl.setVisible(true);
        UsernameInput1.setVisible(false);

    }

    private void On_Email_Clicked(MouseEvent event) {
        EmailInput.setVisible(true);
        Update.setVisible(true);
        Mdp.setVisible(true);
        Mdplbl.setVisible(true);
        EmailInput1.setVisible(false);
    }

    private void On_Telephone_Clicked(MouseEvent event) {
        TelInput.setVisible(true);
        Update.setVisible(true);
        Mdp.setVisible(true);
        Mdplbl.setVisible(true);
        TelInput1.setVisible(false);
    }

    private void On_check_mdp(ActionEvent event) {
        Mdp.setVisible(true);
        Mdplbl.setVisible(true);
        AnMdp.setVisible(true);
        AnMdplbl.setVisible(true);

    }

    @FXML
    private void On_Mdp(KeyEvent event) {
        ValidMdp.setVisible(true);
        ValidMdplbl.setVisible(true);
    }

    @FXML
    private void On_img(MouseEvent event) throws IOException {

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            uuid = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            imagelog = new Image(file.toURI().toString());
            img.setImage(imagelog);
            //   Copy c = new Copy();
            //C:\\wamp\\www\\images\\utilisateur\\
            Utils u = new Utils();
            String emplacement = "C:\\xampp\\htdocs\\pi\\" + uuid;
            System.out.println(emplacement);
            u.CopyImage(emplacement, file.toPath().toString());
            System.out.println("rrrrr");

        }

    }

    @FXML
    private void Consulter_Annonce(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesAnnonces.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }
}
