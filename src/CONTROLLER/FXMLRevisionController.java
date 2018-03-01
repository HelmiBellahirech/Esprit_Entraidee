/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

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

/**
 *
 * @author nadaghanem
 */
public class FXMLRevisionController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void On_btn_ReservSalle(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLReservationSalle.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Cours(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLCours.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootPane.getChildren().setAll(pane);
    }

}
