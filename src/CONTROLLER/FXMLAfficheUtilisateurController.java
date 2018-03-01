/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Etudiant;
import MODEL.Utilisateur;
import SERVICE.EtudiantService;
import SERVICE.UtilisateurService;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLAfficheUtilisateurController implements Initializable {

    @FXML
    private JFXListView<Etudiant> List;
    EtudiantService es = new EtudiantService();
    List <Etudiant> etudiants = es.getAll();
    UtilisateurService us = new UtilisateurService();
    @FXML
    private AnchorPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<Etudiant> items = FXCollections.observableArrayList(etudiants);

        List.setCellFactory((ListView<Etudiant> arg0) -> {
            ListCell<Etudiant> cell = new ListCell<Etudiant>() {
                @Override
                protected void updateItem(Etudiant e, boolean btl) {
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
                                    Utilisateur e = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                               redirect(String.valueOf(e.getID()));
                                System.out.println(e.getID());
                            }

                        });

                        setText(" Nom : "+e.getNom()+ "\n Prenom : " + e.getPrenom()+ "\n Etudiant en " + e.getClasse()+ "\n Email: " + e.getEmail()+ "\n User name: " + e.getUsername()+"\n Date de creation: "+e.getDate_Creation()) ;               

                        setFont(Font.font("Berlin Sans FB Demi Bold", 13));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        List.setItems(items);
    }    
private  int id ; 
    public void redirect(String id) {
        System.out.println("Interface Affiche Details ");
        System.out.println(id);
        this.id = Integer.parseInt(id);
    }
    @FXML
    private void On_btn_Bloquer(ActionEvent event) {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer " + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    us.remove(id);
       AnchorPane pane = new AnchorPane();
                try {
                    pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheUtilisateur.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLUpdateDeleteCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane.getChildren().setAll(pane);
    }
    }
}

