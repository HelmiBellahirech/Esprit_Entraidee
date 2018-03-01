/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import static CONTROLLER.FXMLAfficheCollocationController.Adresse;
import MODEL.Colocation;
import MODEL.Covoiturage;
import SERVICE.Colocation_service;
import SERVICE.Covoiturage_service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import esprit_entraide.Esprit_Entraide;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class FXMLUpdateDeleteColocationController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private JFXTextField nbPersonne;
    @FXML
    private JFXDatePicker date_dispo;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField adresse;
   
    @FXML
    private JFXRadioButton meuble1;
    @FXML
    private ToggleGroup tog;
    @FXML
    private JFXRadioButton meuble2;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextField nbChambre;
    @FXML
    private ImageView myimage;
    @FXML
    private JFXButton idm;
    @FXML
    private JFXButton retour;
    private TextField photoI;
    @FXML
    private JFXButton butmodif;
    @FXML
    private JFXButton butremo;
    @FXML
    private AnchorPane rootpane;
    private static int id;
     @FXML
    private JFXComboBox<String> etage;
    ObservableList<String> etageoption= FXCollections.observableArrayList("RDC", "1", "2", "3","4","5");
     @FXML
    private JFXComboBox<String> type_log;
    ObservableList<String> typelogoption= FXCollections.observableArrayList("Studio", "Appartement", "chambre", "Maison");
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        etage.setItems(etageoption);
        type_log.setItems(typelogoption);
        Colocation c = new Colocation_service().findId(id);
        adresse.setText(c.getAdresse());
        titre.setText(c.getTitre());
        nbPersonne.setText(String.valueOf(c.getNbPersonne()));
        nbChambre.setText(String.valueOf(c.getNbChambre()));
        prix.setText(String.valueOf(c.getPrix()));
        date_dispo.setValue(c.getDate_dispo().toLocalDate());
        date_dispo.setPromptText(c.getDate_dispo().toString());
        type_log.setPromptText(String.valueOf(c.getType_log()));
        type_log.setValue(String.valueOf(c.getType_log()));
        etage.setValue(c.getEtage());
        etage.setPromptText(c.getEtage());
        if (c.getMeuble().equals("Oui")) {
            tog.selectToggle(meuble1);
        } else if (c.getMeuble().equals("Non")) {
            tog.selectToggle(meuble2);
        }
     nbPersonne.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
      adresse.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(50));
      nbChambre.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
      titre.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(50));
      prix.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
   
           
    }    
  public void redirect(String id) {
        System.out.println("Interface Affiche Mod/supp ");
        System.out.println(id);
        this.id = Integer.parseInt(id);

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
            photoI.setText(file.getName());
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
                    pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesCollocation.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLUpdateDeleteCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void but_Update(ActionEvent event) {
        String Meuble1="";
        Date date;
        String NbChambre1 = nbChambre.getText();
        String NbPersonne1 = nbPersonne.getText();
         if (date_dispo.getValue() != null) {
            date = java.sql.Date.valueOf(date_dispo.getValue());
        } else {
            date = null;
        }
        String Type_log1= type_log.getValue();
        String Adresse1 = adresse.getText();
        String Etage1 = etage.getValue();
        if (meuble1.isSelected()) {
            Meuble1 = meuble1.getText();
        }
        if (meuble2.isSelected()) {
            Meuble1 = meuble2.getText();
        }
        String Titre = titre.getText();
      //  String Photo = photoI.getText();
        String Prix1 = prix.getText();
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        
      if ((!"".equals(Titre)) && (!"".equals(Etage1))  && (!"".equals(Adresse1)) && (!"".equals(NbChambre1)) && (!"".equals(NbChambre1))&& (!"".equals(Prix1))&& (!"".equals(Type_log1))) {
             if (date.compareTo(date_sql) < 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec de la modification");
                alert.setHeaderText(null);
                alert.setContentText("Attention ! Date invalide !");
                alert.showAndWait();
                }  else{

               Colocation c= new Colocation(Integer.parseInt(NbChambre1),Integer.parseInt(NbPersonne1),Type_log1,Adresse1,Etage1,date,Meuble1,Float.parseFloat(Prix1),Titre) ; 
                Colocation_service ser = new Colocation_service() ; 
                ser.update(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification Annonce");
                alert.setHeaderText(null);
                alert.setContentText("Votre Annonce à ete Modifié !");
                alert.showAndWait();
                AnchorPane pane = new AnchorPane();
                try {
                    pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesCollocation.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLUpdateDeleteColocationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane.getChildren().setAll(pane);

            }

     
             }   
   
       
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
    private void but_remove(ActionEvent event) {
      
        Colocation_service ser = new Colocation_service() ; 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer " + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
        ser.remove(id);
          AnchorPane pane = new AnchorPane();
                try {
                    pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLMesCollocation.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLUpdateDeleteColocationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootpane.getChildren().setAll(pane);

    }
    }
    
}
