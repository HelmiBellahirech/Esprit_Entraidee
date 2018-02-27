/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import SERVICE.ReservationcovService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLAcceuilUtilisateurController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXButton zx;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Pane backgroundPane;

    NavigationDrawerFXMLController nv = new NavigationDrawerFXMLController();
    @FXML
    private JFXButton Covoiturage;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXButton MesAnnonces;
    @FXML

    private JFXButton Reclamation;

    @FXML
    private JFXButton MesReservation;
    @FXML
    private JFXButton Demande;
    @FXML
    private Label notif;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationcovService rs = new ReservationcovService();
        try {

            FadeTransition fadeout = new FadeTransition(Duration.seconds(2.5), backgroundPane); // TODO
            fadeout.setFromValue(1);
            fadeout.setToValue(0);
            fadeout.play();
            fadeout.setOnFinished(event -> {

                backgroundPane.setStyle(" -fx-background-image: url(\"dest2.jpg\");");

                FadeTransition fadein = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                fadein.setFromValue(0);
                fadein.setToValue(0.6);
                fadein.play();

                fadein.setOnFinished(e -> {

                    backgroundPane.setStyle(" -fx-background-image: url(\"dest3.jpg\");");
                    FadeTransition fadein2 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                    fadein2.setFromValue(0);
                    fadein2.setToValue(1);
                    fadein2.play();

                    fadein2.setOnFinished(event2 -> {

                        backgroundPane.setStyle(" -fx-background-image: url(\"dest2.jpg\");");

                        FadeTransition fadein3 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                        fadein3.setFromValue(1);
                        fadein3.setToValue(0);
                        fadein3.play();

                        fadein3.setOnFinished(event3 -> {
                            backgroundPane.setStyle(" -fx-background-image: url(\"dest4.jpg\");");

                            FadeTransition fadein4 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                            fadein4.setFromValue(0);
                            fadein4.setToValue(1);
                            fadein4.play();

                            fadein4.setOnFinished(event4 -> {
                                backgroundPane.setStyle(" -fx-background-image: url(\"w.png\");");

                                FadeTransition fadein5 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                                fadein5.setFromValue(0);
                                fadein5.setToValue(1);
                                fadein5.play();

                            });

                        });
                    });

                });

            });
            nv.setPage("Utilisateur");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/NavigationDrawerFXML.fxml"));
            AnchorPane pane = loader.load();
            drawer.setSidePane(pane);
            HamburgerBackArrowBasicTransition hamburderTrans = new HamburgerBackArrowBasicTransition(hamburger);
            hamburderTrans.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                hamburderTrans.setRate(hamburderTrans.getRate() * -1);
                hamburderTrans.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }

            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        notif.setText("0");
        if (rs.findByChauffeurb(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID(), false).stream().count() != 0) {
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Notification covoiturage");
            tray.setMessage("Vous avez " + rs.findByChauffeurb(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID(), false).stream().count() + " demande de covoiturage ");
            tray.showAndDismiss(Duration.seconds(3));
            notif.setText(String.valueOf(rs.findByChauffeurb(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID(), false).stream().count()));
        }

    }

    @FXML
    private void On_btn_Covoiturage(ActionEvent event) throws IOException {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheCovoiturage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Cours(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLCours.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Reclamation(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilReclamation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAcceuilReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_MesAnnonces(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesAnnonces.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);

    }

    @FXML

    private void btn_colocation(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheCollocation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCollocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_MesReservation(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesReservationCov.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Demande(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLDemandeCov.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void dmnd(MouseEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLDemandeCov.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Profil(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLProfilUtilisateur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }
}
