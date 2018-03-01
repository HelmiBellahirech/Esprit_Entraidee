/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import SERVICE.ReclamationService;
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
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLAcceuilAdministrateurController implements Initializable {

    @FXML
    private JFXButton Decnx;
    @FXML
    private ImageView img;
    @FXML
    private JFXButton feedback;
    @FXML
    private BarChart<String, Integer> barchartid;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private JFXButton Gestion_Utilisateur;
    @FXML
    private Pane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ReclamationService rs = new ReclamationService();
        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("Reclamation Objet perdu", rs.findByType("Reclamation Objet perdu").stream().count()));
        set1.getData().add(new XYChart.Data("Reclamation Objet trouvé", rs.findByType("Reclamation Objet trouvé").stream().count()));

        barchartid.getData().addAll(set1);
        barchartid.setVisible(false);

    }

    @FXML
    private void On_btn_Decnx(ActionEvent event) throws IOException {
        esprit_entraide.Esprit_Entraide.getInstance().getStage().hide();
        esprit_entraide.Esprit_Entraide.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml"))));

        esprit_entraide.Esprit_Entraide.getInstance().getStage().show();
    }

    @FXML
    private void On_btn_feedback(ActionEvent event) {
        barchartid.setVisible(true);
    }

    @FXML
    private void On_btn_Utilisateur(ActionEvent event) {
barchartid.setVisible(false);
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheUtilisateur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Prof(ActionEvent event) {
        barchartid.setVisible(false);
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheProfesseur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);
    }
}
