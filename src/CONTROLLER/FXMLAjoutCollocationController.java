/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Colocation;
import SERVICE.Colocation_service;
import UTILS.InputValidation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import esprit_entraide.Esprit_Entraide;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FXMLAjoutCollocationController implements Initializable{

    @FXML
    private AnchorPane panne;
    @FXML
    private Label label;
    @FXML
    private JFXButton button;
    @FXML
    private JFXTextField nbPersonne;
    @FXML
    private JFXDatePicker date_dispo;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXComboBox<String> etage;
    @FXML
    private JFXComboBox<String> type_log;
    @FXML
    private ToggleGroup tog;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextField nbChambre;
    @FXML
    private ImageView myimage;
    @FXML
    private JFXButton idm;
    @FXML
    private JFXRadioButton meuble1;
    @FXML
    private JFXRadioButton meuble2;
     private JFXTextField photo;
    @FXML
    private JFXButton retour;
    @FXML
    private TextField photoI;
    


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      etage.getItems().add("RDC");
      etage.getItems().add("1");
      etage.getItems().add("2");
      etage.getItems().add("3");
      etage.getItems().add("4");
      etage.getItems().add("5");
      type_log.getItems().add("Studio");
      type_log.getItems().add("Appartement");
      type_log.getItems().add("chambre");
      type_log.getItems().add("Maison");
      date_dispo.setValue(LocalDate.now());
      
      nbPersonne.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
      adresse.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(50));
      nbChambre.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
      titre.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(50));
      prix.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
     
    }    

     /*limitter la validation des nombres :maxLengh et  seulement des nombres *************************************/
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9.]")){ 
                if(txt_TextField.getText().contains(CharSequence.class.cast(".")) && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }
        }
    };
}    


 /*limitter la validation des lettres :maxLengh et  seulement des Letters de  Validation *************************************/
        public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
            return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Z a-z]")){ 
            }else{
                e.consume();
            }
        }
    };
} 

       
 
    @FXML
    private void Saveannonce(ActionEvent event) {
         String Meuble = "";
        Date date;
        String NbChambre = nbChambre.getText() ; 
        String NbPersonne = nbPersonne.getText();
         if (date_dispo.getValue() != null) {
            date = java.sql.Date.valueOf(date_dispo.getValue());
        } else {
            date = null;
        }
        String Prix = prix.getText();
        String Type_log = type_log.getValue();
        String Adresse = adresse.getText();
        String Etage = etage.getValue();
          if (meuble1.isSelected()) {
            Meuble = meuble1.getText();
        }
        if (meuble2.isSelected()) {
            Meuble = meuble2.getText();
        }
        
        String Titre = titre.getText();
        String Photo = photoI.getText();
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        
      if ((!"".equals(Titre)) && (!"".equals(Etage))  && (!"".equals(Adresse)) && (!"".equals(NbChambre)) && (!"".equals(NbPersonne))&& (!"".equals(Photo))&& (!"".equals(Prix))&& (!"".equals(Type_log))) {
             if (date.compareTo(date_sql) < 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec de l'ajout");
                alert.setHeaderText(null);
                alert.setContentText("Attention ! Date invalide !");
                alert.showAndWait();
                } 
             else{

        Colocation c= new Colocation(Integer.parseInt(NbChambre),Integer.parseInt(NbPersonne),Type_log,Adresse,Etage,date,Meuble,Float.parseFloat(Prix),Titre,Photo) ; 
        Colocation_service ser = new Colocation_service() ; 
        ser.add(c);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout Annonce");
                alert.setHeaderText(null);
                alert.setContentText("Votre Annonce à ete ajouté !");

                alert.showAndWait();
                AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheCollocation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjoutCollocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        panne.getChildren().setAll(pane);
            }

     
             }   
      else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Echec de l'ajout");
            alert.setHeaderText(null);
            alert.setContentText("Attention ! Veuillez remplir tous les champs ");

            alert.showAndWait();
        }
         
    }
    
    
    @FXML
    private void loadimage(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
         //Show open file dialog
         File file = fileChooser.showOpenDialog(null);
             if (file != null) {
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            photoI.setText(file.  getName());
            System.out.println(file.getName()); 
            
           
                       
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
               myimage.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(Esprit_Entraide.class.getName()).log(Level.SEVERE, null, ex);
            }}
             else {
             photoI.setText("File selection cancelled."); }
    }
    


    @FXML
    private void retour_butt(ActionEvent event) {
          AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheCollocation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        panne.getChildren().setAll(pane);
    }

 

  

   
}
