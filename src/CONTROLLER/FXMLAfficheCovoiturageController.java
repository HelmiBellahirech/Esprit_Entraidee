/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLAfficheCovoiturageController implements Initializable {

    @FXML
    private ImageView img11;
    @FXML
    private JFXTextField DepartInput;
    @FXML
    private JFXTextField ArriveInput;
    @FXML
    private JFXButton Rechercher;
    @FXML
    private JFXButton Ajouter;
    public static String Depart;
    public static String Arrive;
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
    private void On_btn_Rechercher(ActionEvent event) throws IOException {
        Depart = DepartInput.getText();
        Arrive = ArriveInput.getText();
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLTajriba.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);

    }

    @FXML
    private void On_btn_Ajouter(ActionEvent event) throws IOException {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAjoutCovoiturage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjoutCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);

    }

    @FXML
    private void On_btn_Menu(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilUtilisateur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjoutCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

}
