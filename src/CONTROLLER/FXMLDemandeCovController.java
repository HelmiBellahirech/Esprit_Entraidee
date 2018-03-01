/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Covoiturage;
import MODEL.Reservationcov;
import MODEL.Utilisateur;
import SERVICE.Covoiturage_service;
import SERVICE.ReservationcovService;
import SERVICE.UtilisateurService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
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
public class FXMLDemandeCovController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ReservationcovService rs = new ReservationcovService();
    @FXML
    private JFXListView<Reservationcov> List;
    @FXML
    private Label num;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXButton Accepter;
    @FXML
    private JFXButton Decliner;
    @FXML
    private JFXButton Retour;
    @FXML
    private JFXButton app;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Covoiturage_service cs = new Covoiturage_service();
        List<Reservationcov> reservations = new ArrayList<>();
        UtilisateurService us = new UtilisateurService();

        reservations = rs.findByChauffeurb(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID(), false);
        num.setText(String.valueOf(reservations.stream().count()));
        ObservableList<Reservationcov> items = FXCollections.observableArrayList(reservations);

        List.setCellFactory((ListView<Reservationcov> arg0) -> {
            ListCell<Reservationcov> cell = new ListCell<Reservationcov>() {
                @Override
                protected void updateItem(Reservationcov e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        Covoiturage c = cs.findId(e.getID_ANNONCE());
                        Utilisateur U = new Utilisateur();
                        U = us.getById(e.getID_RESERVE());
                        //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                        File file = new File("C:\\xampp\\htdocs\\pi\\" + U.getPhoto());

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

                                redirect(String.valueOf(e.getID_ANNONCE()));
                                redirect1(String.valueOf(e.getNBPLACES()));
                                redirect2(String.valueOf(e.getID()));

                            }

                        });
                        setText("Demande envoyée par " + U.getNom() + "  " + U.getPrenom() + "\n" + "Nombre de place demandé " + e.getNBPLACES() + "\n");

                        setFont(Font.font("Berlin Sans FB Demi Bold", 13));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        List.setItems(items);
    }
    int id = 0;
    int idr = 0;
    int nb = 0;

    public void redirect(String id) {
        System.out.println(id);
        this.id = Integer.parseInt(id);
    }

    public void redirect1(String nb) {
        System.out.println(nb);
        this.nb = Integer.parseInt(nb);
    }

    public void redirect2(String idr) {
        System.out.println(idr);
        this.idr = Integer.parseInt(idr);
    }

    @FXML
    private void On_btn_Accepter(ActionEvent event) {
        Covoiturage_service cs = new Covoiturage_service();
        Covoiturage c = cs.findId(id);
        c.setNbrPlaces(c.getNbrPlaces() - nb);
        ReservationcovService rs = new ReservationcovService();
        Reservationcov r = rs.findId(idr);
        r.setETAT(true);
        rs.update(r);
        cs.update(c);
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLDemandeCov.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);

        cs.miseajour();
    }

    @FXML
    private void On_btn_Decliner(ActionEvent event) {

        ReservationcovService rs = new ReservationcovService();
        Reservationcov r = rs.findId(idr);
        r.setETAT(true);
        rs.remove(idr);
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLDemandeCov.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Retour(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilUtilisateur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_TRUE(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLVoirResapprouve.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

}
