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
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
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
public class FXMLMesReservationCovController implements Initializable {

    @FXML
    private JFXListView<Reservationcov> List;

    int id;
    int nb;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXButton Retour;
    private JFXComboBox<String> EtatCombobox;
    ObservableList<String> Comfortoption
            = FXCollections.observableArrayList("Approuvées", "En Attente");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//                EtatCombobox.setItems(Comfortoption);

        Covoiturage_service cs = new Covoiturage_service();
        ReservationcovService rs = new ReservationcovService();
        List<Reservationcov> reservations = new ArrayList<>();
        UtilisateurService us = new UtilisateurService();

        reservations = rs.findByReserve(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID());

        ObservableList<Reservationcov> items = FXCollections.observableArrayList(reservations);

        List.setCellFactory((ListView<Reservationcov> arg0) -> {
            ListCell<Reservationcov> cell = new ListCell<Reservationcov>() {
                @Override
                protected void updateItem(Reservationcov e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
                        File file;
                        Covoiturage c = cs.findId(e.getID_ANNONCE());
                        Utilisateur U = new Utilisateur();
                        U = us.getById(c.getID_USER());
                        //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                        if (e.isETAT()) {
                            file = new File("src\\app.png");
                        } else {
                            file = new File("src\\attente.png");
                        }
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
                                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                    ReservationcovService rss = new ReservationcovService();
                                    Reservationcov cov = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Annuler la demande" + " ?", ButtonType.YES, ButtonType.CANCEL);
                                    alert.showAndWait();

                                    if (alert.getResult() == ButtonType.YES) {
                                        Covoiturage_service cs = new Covoiturage_service();
                                        Covoiturage c = cs.findId(cov.getID_ANNONCE());
                                        c.setNbrPlaces(c.getNbrPlaces()+cov.getNBPLACES());
                                        cs.update(c);
                                        rss.remove(cov.getID());
                                        AnchorPane pane = new AnchorPane();
                                        try {
                                            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesReservationCov.fxml"));
                                        } catch (IOException ex) {
                                            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        rootpane.getChildren().setAll(pane);

                                    }

                                }
                            }

                        });
                        setText("Publier Par " + "\n" + "De " + c.getDepart() + " -> " + c.getArrive() + "\n" + "En " + c.getDate() + " A " + c.getHeure() + "\n" + "Nombre de place souhaité " + e.getNBPLACES());

                        setFont(Font.font("Berlin Sans FB Demi Bold", 13));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        List.setItems(items);
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

}
