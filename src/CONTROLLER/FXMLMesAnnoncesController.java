/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLMesAnnoncesController implements Initializable {

    @FXML
    private JFXButton Covoiturage;
    @FXML
    private JFXButton Collocation;
    @FXML
    private Pane backgroundPane;
    @FXML
    private AnchorPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void On_btn_Covoiturage(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesCovoiturage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        backgroundPane.getChildren().setAll(pane);
    }

    @FXML

    private void On_btn_Collocation(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesCollocation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCollocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        backgroundPane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Reclamation(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesReclamation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        backgroundPane.getChildren().setAll(pane);

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

}
