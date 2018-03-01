/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import ISERVICE.ISalleService;
import MODEL.ReservationSalle;
import MODEL.Salle;
import MODEL.Utilisateur;
import SERVICE.SalleService;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author nadaghanem
 */
public class FXMLReservationSalleController implements Initializable {

    @FXML
    private ComboBox salleCB;
    @FXML
    private TextField nbPersoTxt;
    @FXML
    private DatePicker datePK;
    @FXML
    private JFXTimePicker fromPK;
    @FXML
    private JFXTimePicker toPK;
    @FXML
    private Button reservBtn;
    @FXML
    private Label infoLbl;
    @FXML
    private AnchorPane rootContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        salleCB.getItems().addAll("Salle 1", "Salle 2", "Salle 3");
    }

    ISalleService salleService = new SalleService();

    @FXML
    private void On_btn_Menu(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilUtilisateur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjoutCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootContainer.getChildren().setAll(pane);
    }

    @FXML
    private void OnBtnReservClick(ActionEvent event) {
        Salle salle = salleService.findId(1);
        Utilisateur user = esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser();
        int nbPersonnes = Integer.parseInt(nbPersoTxt.getText());
        LocalDateTime dateTime1 = datePK.getValue().atStartOfDay().plusMinutes(fromPK.getValue().getMinute()).plusHours(fromPK.getValue().getHour());
        LocalDateTime dateTime2 = datePK.getValue().atStartOfDay().plusMinutes(toPK.getValue().getMinute()).plusHours(toPK.getValue().getHour());

        System.out.println(dateTime1);
        System.out.println(dateTime2);

        ReservationSalle rvSalle = new ReservationSalle(salle, user, dateTime1, dateTime2, 10);
        if (salleService.salleDisponible(rvSalle)) {
            infoLbl.setText("Salle disponible");
            salleService.addReservation(rvSalle);
        } else {
            infoLbl.setText("Salle occupé pour cette date et heure!");
        }
    }
}
