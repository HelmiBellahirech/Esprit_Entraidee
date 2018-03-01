/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;


import MODEL.Professeur;
import MODEL.Professeur;
import MODEL.Utilisateur;
import SERVICE.Colocation_service;
import SERVICE.ProfService;
import SERVICE.UtilisateurService;
import UTILS.InputValidation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLAfficheProfesseurController implements Initializable {

    @FXML
    private JFXListView<Professeur> List;
    @FXML
    private JFXTextField NomInput;
    @FXML
    private JFXTextField TelInput;
    @FXML
    private JFXTextField PrenomInput;
    @FXML
    private JFXTextField SpecialiteInput;
    @FXML
    private JFXTextField UsernameInput;
    @FXML
    private JFXTextField EmailInput;
    @FXML
    private JFXComboBox<String> SexeCombo;
    ObservableList<String> SexeOption
            = FXCollections.observableArrayList("Homme", "Femme");
    @FXML
    private JFXPasswordField MdpInput;
    @FXML
    private JFXPasswordField ValidMdpInput;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXButton AJout;
    @FXML
    private Pane paneAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SexeCombo.setItems(SexeOption);
        paneAjout.setVisible(false);
        List.setVisible(false);
    }    

    @FXML
    private void On_btn_ConfirmerAjout(ActionEvent event) {
         
      String nom = NomInput.getText();
      String Prenom= PrenomInput.getText();
      String Mdp= MdpInput.getText();
      String VMdp = ValidMdpInput.getText();
      String Telephone = TelInput.getText();
      String Email = EmailInput.getText();
      String Specialite = SpecialiteInput.getText();
      String Username = UsernameInput.getText();
      String Sexe =  SexeCombo.getValue() ; 
      
      Integer verif = InputValidation.validPwd(Mdp, VMdp);
       
        if ((!"".equals(nom)) && (!"".equals(Prenom)) && (!"".equals(Mdp)) && (!"".equals(VMdp)) && (!"".equals(Username))&& (!"".equals(Specialite))&& (!"".equals(Telephone))&& (!"".equals(Specialite))) {

             if(!InputValidation.validEmail(Email))
            {
                
          
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec de l'ajout");
                alert.setHeaderText(null);
                alert.setContentText("Attention ! Email Invalide!");
                alert.showAndWait();
            } else 
                    if (verif == 0) {
                        Alert alertMDP = new InputValidation().getAlert("Mot de passe", "Les mots de passe ne correspondent pas.");
                        alertMDP.showAndWait();
                    } else {
                 
               
                Professeur u = new Professeur(Email, Username, Mdp, nom, Prenom, Telephone,Sexe, Specialite);
                
                ProfService cs = new ProfService();
                  if ( (u.getSexe().equals("Homme"))) {

                                    u.setPhoto("user.png");

                                } else if ( (u.getSexe().equals("Femme"))) {

                                    u.setPhoto("userf.png");
                                }
                cs.add(u);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout Annonce");
                alert.setHeaderText(null);
                alert.setContentText("Ajout avec succes !");

                alert.showAndWait();
                AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheProfesseur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjoutCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Echec de l'ajout");
            alert.setHeaderText(null);
            alert.setContentText("Attention ! Veuillez remplir tous les champs ");

            alert.showAndWait();
        }
    }

    @FXML
    private void On_btn_Ajout(ActionEvent event) {
        List.setVisible(false);
        paneAjout.setVisible(true);
    }

    @FXML
    private void On_btn_aficher(ActionEvent event) {
        paneAjout.setVisible(false);
        List.setVisible(true);
        ProfService ps = new ProfService();
        List<Professeur> professeurs = ps.getAll();
         ObservableList<Professeur> items = FXCollections.observableArrayList(professeurs);

        List.setCellFactory((ListView<Professeur> arg0) -> {
            ListCell<Professeur> cell = new ListCell<Professeur>() {
                @Override
                protected void updateItem(Professeur e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                        File file = new File("C:\\xampp\\htdocs\\pi\\" + e.getPhoto());
                        file.getParentFile().mkdirs();
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

                        ImageView imgview = new ImageView(IMAGE_RUBY);

                        setGraphic(imgview);

                        imgview.setFitHeight(70);
                        imgview.setFitWidth(70);
                        Rectangle clip = new Rectangle(
                                imgview.getFitWidth(), imgview.getFitHeight()
                        );

                        clip.setArcWidth(20);
                        clip.setArcHeight(20);
                        imgview.setClip(clip);

                        // snapshot the rounded image.
                        SnapshotParameters parameters = new SnapshotParameters();
                        parameters.setFill(Color.TRANSPARENT);
                        WritableImage image = imgview.snapshot(parameters, null);

                        // remove the rounding clip so that our effect can show through.
                        imgview.setClip(null);

                        // apply a shadow effect.
                        imgview.setEffect(new DropShadow(20, Color.BLACK));

                        // store the rounded image in the imageView.
                        imgview.setImage(image);
                        TextField id = new TextField(String.valueOf(e.getID()));
                        id.setVisible(true);
                        setId(String.valueOf(e.getID()));
                        List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                    Professeur e = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                               //redirect(String.valueOf(e.getID()));
                                UtilisateurService cs = new UtilisateurService();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer " + " ?", ButtonType.YES,  ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            cs.remove(e.getID());
            
                                        AnchorPane pane = new AnchorPane();
                                    try {
                                        pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheProfesseur.fxml"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    rootpane.getChildren().setAll(pane);
         
        }
                            }

                        });

                        setText("Nom : "+e.getNom()+ "\n Prenom : " + e.getPrenom()+ "\n Téléphone : " + e.getTelephone()+ "\n Specialité : " + e.getMatricule()+ "\n user name : " + e.getUsername()+ "\n Email : " + e.getEmail()+ "\n") ;               

                        setFont(Font.font("Berlin Sans FB Demi Bold", 14));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        List.setItems(items);
    }
    
}
