/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Covoiturage;
import SERVICE.Covoiturage_service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalTime;
import com.jfoenix.controls.JFXTextField;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLUpdateDeleteCovoiturageController implements Initializable {

    @FXML
    private JFXTextField departInput;
    @FXML
    private JFXTextField arriveInput;
    @FXML
    private JFXTextField prixInput;
    @FXML
    private JFXDatePicker DateInpuit;
    @FXML
    private JFXTimePicker heureInput;
    private JFXTextField nbredeplaceInput;
    @FXML
    private JFXComboBox<String> comfortcombobox;
    ObservableList<String> Comfortoption
            = FXCollections.observableArrayList("Basique", "Normale", "Comfortable", "Luxe");
    @FXML
    private JFXRadioButton oui;
    @FXML
    private ToggleGroup fumeur;
    @FXML
    private JFXRadioButton non;
    @FXML
    private JFXButton Modifier;
    @FXML
    private JFXButton Supprimer;
    private static int id;
    @FXML
    private JFXComboBox<String> NbPlacesCombobox;
    ObservableList<String> Nbplacesoption
            = FXCollections.observableArrayList("1", "2", "3", "4");
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXButton Annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comfortcombobox.setItems(Comfortoption);
        NbPlacesCombobox.setItems(Nbplacesoption);
        Covoiturage c = new Covoiturage_service().findId(id);
        departInput.setText(c.getDepart());
        arriveInput.setText(c.getArrive());
        prixInput.setText(String.valueOf(c.getPrix()));
        DateInpuit.setValue(c.getDate().toLocalDate());
        DateInpuit.setPromptText(c.getDate().toString());
        heureInput.setPromptText(c.getHeure());
        heureInput.setValue(LocalTime.parse(CharSequence.class.cast(c.getHeure())));
        NbPlacesCombobox.setPromptText(String.valueOf(c.getNbrPlaces()));
        NbPlacesCombobox.setValue(String.valueOf(c.getNbrPlaces()));
        comfortcombobox.setValue(c.getComfort());
        comfortcombobox.setPromptText(c.getComfort());
        if (c.getFumeur().equals("Oui")) {
            fumeur.selectToggle(oui);
        } else if (c.getFumeur().equals("Non")) {
            fumeur.selectToggle(non);
        }

    }

    public void redirect(String id) {

        System.out.println(id);
        this.id = Integer.parseInt(id);

    }

    @FXML
    private void On_btn_Modifier(ActionEvent event) {

        String fumeurr = "";
        String depart = departInput.getText();
        String arrive = arriveInput.getText();
        Date date;
        if (DateInpuit.getValue() != null) {
            date = java.sql.Date.valueOf(DateInpuit.getValue());
        } else {
            date = null;
        }
        System.out.println(date);
        String heure;

        if (heureInput.getValue() != null) {
            heure = heureInput.getValue().toString();
        } else {
            heure = null;
        }
        String nbrplaces1 = NbPlacesCombobox.getValue();
        String comfort = comfortcombobox.getValue();
        String prix1 = prixInput.getText();
        int nbplace;
        if (NbPlacesCombobox.getValue() != null) {
            nbplace = Integer.parseInt(NbPlacesCombobox.getValue());
        } else {
            nbplace = 0;
        }
        if (oui.isSelected()) {
            fumeurr = oui.getText();
        }
        if (non.isSelected()) {
            fumeurr = non.getText();
        }
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());

        if ((!"".equals(depart)) && (!"".equals(arrive)) && (date != null) && (!"".equals(heure)) && (!"".equals(nbrplaces1)) && (!"".equals(prix1))) {

            if (date.compareTo(date_sql) < 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec de la modification");
                alert.setHeaderText(null);
                alert.setContentText("Attention ! Date invalide !");
                alert.showAndWait();
            } else {
                float prix = Float.parseFloat(prixInput.getText());
                int nbrplaces = Integer.parseInt(NbPlacesCombobox.getValue());
                Covoiturage C = new Covoiturage(id, depart, arrive, prix, date, heure, nbplace, comfort, fumeurr);
                Covoiturage_service cs = new Covoiturage_service();
                cs.update(C);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification Annonce");
                alert.setHeaderText(null);
                alert.setContentText("Votre Annonce a ete Modifié !");
                alert.showAndWait();
                AnchorPane pane = new AnchorPane();
                try {
                    pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesCovoiturage.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLUpdateDeleteCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane.getChildren().setAll(pane);

            }
        }
    }

    @FXML
    private void On_btn_Supprimer(ActionEvent event) {
        Covoiturage_service cs = new Covoiturage_service();

        Alert alert = new Alert(AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer " + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            cs.remove(id);
            AnchorPane pane = new AnchorPane();
            try {
                pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesCovoiturage.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(FXMLUpdateDeleteCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            rootpane.getChildren().setAll(pane);
        }

    }

    @FXML
    private void On_btn_Annuler(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesCovoiturage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDeleteCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);

    }

}
