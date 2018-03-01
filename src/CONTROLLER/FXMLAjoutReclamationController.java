/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Reclamation;
import MODEL.Utilisateur;
import SERVICE.ReclamationService;
import SERVICE.UtilisateurService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FXMLAjoutReclamationController implements Initializable {

    @FXML
    private JFXComboBox<String> typeobj_perdu;
    ObservableList<String> typeoption
            = FXCollections.observableArrayList("Calculatrice", "Chargeur", "Clés", "Clé USB", "CIN", "Monnaie", "Passeport", "PC", "Portefeuille", "Porte-documents", "Télephone", "Feuilles", "Autre");
    @FXML
    private TextField description;
    @FXML
    private JFXComboBox<String> lieu_decouverte;
    ObservableList<String> typeoption5
            = FXCollections.observableArrayList("Esprit Ghazela", "Esprit Charguia");
    @FXML
    private Label typeobj_perduLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label lieu_decouverteLabel;
    @FXML
    private Button btn_ok;
    @FXML
    private DatePicker date_decouverte;
    @FXML
    private Label date_decouverteLabel;
    @FXML
    private JFXComboBox<String> type;
    ObservableList<String> typeoption1
            = FXCollections.observableArrayList("Reclamation Objet perdu", "Reclamation Objet trouvé");
    @FXML
    private JFXTextField autretypeobj_perdu;
    @FXML
    private JFXTextField autrelocalisation;
    @FXML
    private JFXComboBox<String> etage;
    ObservableList<String> typeoption3
            = FXCollections.observableArrayList("Rez-de-chaussée", "1er étage", "2éme étage", "3éme étage", "4éme étage");

    @FXML
    private JFXComboBox<String> localisation;
    ObservableList<String> typeoption4
            = FXCollections.observableArrayList("Buvette", "Bloc A", "Bloc B", "Bloc C", "Bloc D", "Bloc E", "Bloc H", "Autre");

    @FXML
    private JFXTextField matricule;
    @FXML
    private Label autretypeobj_perduLabel;
    @FXML
    private Label autreLocalisationLabel;
    @FXML
    private JFXTextField salle;
    @FXML
    private Label salleLabel;
    @FXML
    private Label photoLabel;
    @FXML
    private Button photo;
    @FXML
    private JFXTextField actionStatus;
    @FXML
    private Label matriculeLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label localisationLabel;
    @FXML
    private Label etageLabel;
    @FXML
    private JFXRadioButton recobj;
    private ToggleGroup Rec;
    @FXML
    private JFXRadioButton recmalst;
    @FXML
    private ToggleGroup Recc;
    private ImageView imageview1;
   
    private Image image;
    @FXML
    private Label photo2Label;
    @FXML
    private Button photo2;
    @FXML
    private JFXTextField actionStatus2;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button Retour;
    @FXML
    private Button mail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setVisible(false);
        typeLabel.setVisible(false);
        typeobj_perdu.setVisible(false);
        typeobj_perduLabel.setVisible(false);
        lieu_decouverte.setVisible(false);
        lieu_decouverteLabel.setVisible(false);
        localisation.setVisible(false);
        localisationLabel.setVisible(false);
        etage.setVisible(false);
        etageLabel.setVisible(false);
        date_decouverte.setVisible(false);
        date_decouverteLabel.setVisible(false);
        description.setVisible(false);
        descriptionLabel.setVisible(false);
        photo.setVisible(false);
        photoLabel.setVisible(false);
        photo2.setVisible(false);
        photo2Label.setVisible(false);
        matricule.setVisible(false);
        matriculeLabel.setVisible(false);
        autretypeobj_perdu.setVisible(false);
        autretypeobj_perduLabel.setVisible(false);
        autrelocalisation.setVisible(false);
        autreLocalisationLabel.setVisible(false);
        salleLabel.setVisible(false);
        salle.setVisible(false);
        mail.setVisible(false);

        Recc.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                System.out.println("u");
                if (recobj.isSelected()) {
                    type.setVisible(true);
                    typeLabel.setVisible(true);
                    typeobj_perdu.setVisible(true);
                    typeobj_perduLabel.setVisible(true);
                    lieu_decouverte.setVisible(true);
                    lieu_decouverteLabel.setVisible(true);
                    localisation.setVisible(true);
                    localisationLabel.setVisible(true);
                    date_decouverte.setVisible(false);
                    date_decouverteLabel.setVisible(false);
                    etage.setVisible(true);
                    etageLabel.setVisible(true);
                    description.setVisible(true);
                    descriptionLabel.setVisible(true);
                    photo.setVisible(true);
                    photoLabel.setVisible(true);
                    date_decouverte.setVisible(true);
                    date_decouverteLabel.setVisible(true);
                    matricule.setVisible(false);
                    matriculeLabel.setVisible(false);
                    photo2.setVisible(false);
                    photo2Label.setVisible(false);
                    actionStatus2.setVisible(false);
                    actionStatus.setVisible(true);
                     mail.setVisible(false);

                }
                if (recmalst.isSelected()) {
                    type.setVisible(false);
                    typeLabel.setVisible(false);
                    typeobj_perdu.setVisible(false);
                    typeobj_perduLabel.setVisible(false);
                    lieu_decouverte.setVisible(false);
                    lieu_decouverteLabel.setVisible(false);
                    localisation.setVisible(false);
                    localisationLabel.setVisible(false);
                    etage.setVisible(false);
                    etageLabel.setVisible(false);
                    date_decouverte.setVisible(false);
                    date_decouverteLabel.setVisible(false);
                    description.setVisible(false);
                    descriptionLabel.setVisible(false);
                    photo.setVisible(false);
                    photoLabel.setVisible(false);
                    matricule.setVisible(true);
                    matriculeLabel.setVisible(true);
                    actionStatus2.setVisible(true);
                    photo2.setVisible(true);
                    photo2Label.setVisible(true);
                    autretypeobj_perdu.setVisible(false);
                    autretypeobj_perduLabel.setVisible(false);
                    autrelocalisation.setVisible(false);
                    autreLocalisationLabel.setVisible(false);
                    salleLabel.setVisible(false);
                    salle.setVisible(false);
                    actionStatus.setVisible(false);
                    mail.setVisible(true);
                }
            }
        });
        typeobj_perdu.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (typeobj_perdu.getValue().equals("Autre")) {
                autretypeobj_perdu.setVisible(true);
                autretypeobj_perduLabel.setVisible(true);
            } else {
                autretypeobj_perdu.setVisible(false);
                autretypeobj_perduLabel.setVisible(false);
            }
        });

        localisation.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (localisation.getValue().equals("Autre")) {
                autrelocalisation.setVisible(true);
                autreLocalisationLabel.setVisible(true);
            } else {
                autrelocalisation.setVisible(false);
                autreLocalisationLabel.setVisible(false);
            }
        });
        typeobj_perdu.setItems(typeoption);
        type.setItems(typeoption1);
        etage.setItems(typeoption3);
        localisation.setItems(typeoption4);
        lieu_decouverte.setItems(typeoption5);

        localisation.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (localisation.getValue().equals("Bloc A")) {
                salle.setVisible(true);
                salleLabel.setVisible(true);
            } else if (localisation.getValue().equals("Bloc B")) {
                salle.setVisible(true);
                salleLabel.setVisible(true);
            } else if (localisation.getValue().equals("Bloc C")) {
                salle.setVisible(true);
                salleLabel.setVisible(true);
            } else if (localisation.getValue().equals("Bloc D")) {
                salle.setVisible(true);
                salleLabel.setVisible(true);
            } else if (localisation.getValue().equals("Bloc E")) {
                salle.setVisible(true);
                salleLabel.setVisible(true);
            } else if (localisation.getValue().equals("Bloc H")) {
                salle.setVisible(true);
                salleLabel.setVisible(true);
            } else {
                salle.setVisible(false);
                salleLabel.setVisible(false);
            }
        });
        actionStatus.setVisible(false);
        actionStatus2.setVisible(false);
        matricule.setVisible(false);
        matriculeLabel.setVisible(false);

    }

    @FXML
    private void saveReclamation(ActionEvent event) {
     
       if (recobj.isSelected()){
          
           //String typeRec = recobj.getText();
           
           String typeRec;
        if (type.getValue() != null) {
             typeRec = type.getValue();
             System.out.println("shiha");
        }else{typeRec = null;}
        
        
        String typeobj_perduRec;
        if (typeobj_perdu.getValue() != null) {
             typeobj_perduRec = typeobj_perdu.getValue();
        }else {
            typeobj_perduRec = null;}
        
        String autretypeobj_perduRec;
        if (autretypeobj_perdu.getText()!= null) {
             autretypeobj_perduRec = autretypeobj_perdu.getText();
        }else {
            autretypeobj_perduRec = null;}
        
        
        
        String lieu_decouverteRec;
        if (lieu_decouverte.getValue() != null) {
             lieu_decouverteRec = lieu_decouverte.getValue();
        }else {
            lieu_decouverteRec = null;}
        
        
         String localisationRec;
        if (localisation.getValue() != null) {
             localisationRec = localisation.getValue();
        }else {
            localisationRec = null;}
        
        
        String autrelocalisationRec;
        if (autrelocalisation.getText()!= null) {
             autrelocalisationRec = autrelocalisation.getText();
        }else {
            autrelocalisationRec = null;}
        
        
         String etageRec;
        if (etage.getValue() != null) {
             etageRec = etage.getValue();
        }else {
            etageRec = null;}
        
        
        String salleRec;
        if (salle.getText()!= null) {
             salleRec = salle.getText();
        }else {
            salleRec = null;}
        
         Date date;
        if (date_decouverte.getValue() != null) {
            date = java.sql.Date.valueOf(date_decouverte.getValue());
        } else {
            date = null;}
        
       String descriptionRec=description.getText();
         if (description.getText()!= null) {
            descriptionRec = description.getText();
        } else {
            descriptionRec = null;}
        
        String photoRec;
        if (actionStatus.getText()!= null) {
            photoRec = actionStatus.getText();
        } else {
            photoRec = null;}
        
        Reclamation rec = new Reclamation(descriptionRec, date, lieu_decouverteRec, typeobj_perduRec, typeRec, autretypeobj_perduRec, localisationRec, autrelocalisationRec, etageRec, salleRec, photoRec);

        ReclamationService rs = new ReclamationService();
        rs.add(rec);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout Annonce");
                alert.setHeaderText(null);
                alert.setContentText("Votre Annonce a été ajouté avec succés !");
                alert.showAndWait();
       }
       else if(recmalst.isSelected()){
           
        String matriculeRec;
        if (matricule.getText()!= null) {
            matriculeRec = matricule.getText();
        } else {
            matriculeRec = null;}
        
        
        String photo2Rec;
        if (actionStatus2.getText()!= null) {
            photo2Rec = actionStatus2.getText();
        } else {
            photo2Rec = null;}
        
        String typeRec;
        if (recmalst.isSelected()) {
             typeRec = recmalst.getText();
        }else {
            typeRec = null;}
        
        Reclamation rec1 = new Reclamation(matriculeRec,photo2Rec,typeRec);

        ReclamationService rs = new ReclamationService();
        rs.addMal(rec1);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout Annonce");
                alert.setHeaderText(null);
                alert.setContentText("Votre Annonce a été ajouté avec succés !");
                alert.showAndWait();
       }else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Echec de l'ajout");
            alert.setHeaderText(null);
            alert.setContentText("Attention ! Veuillez remplir tous les champs ");

            alert.showAndWait();
       }
       
    }

    @FXML
    private void savePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
      /*  //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", ".JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", ".PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            */
            /*
               try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
               imageview1.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(Esprit_Entraide.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        if (selectedFile != null) {
            //image =new Image(selectedFile.toURI().toString(),100,500,true,true);//path , prefered width , prefered heigh, PreserveRatio,Smooth
            //System.out.println(image);
            //imageview1 = new ImageView(image);
           // System.out.println(imageview1);
            //imageview1.setFitWidth(100);
            //imageview1.setFitHeight(150);
            //imageview1.setPreserveRatio(true);
            actionStatus.setText(selectedFile.getName());
            System.out.println(selectedFile.getName());
        } else {
           actionStatus.setText("File selection cancelled."); }
    }
    
      @FXML
    private void savePhoto2(ActionEvent event) {
        FileChooser fileChooser2 = new FileChooser();
        File selectedFile2 = fileChooser2.showOpenDialog(null);
        
        if (selectedFile2 != null) {
           // image =new Image(selectedFile2.toURI().toString(),100,500,true,true);//path , prefered width , prefered heigh, PreserveRatio,Smooth
            //System.out.println(image);
            //imageview1 = new ImageView(image);
            //System.out.println(imageview1);
            //imageview1.setFitWidth(100);
            //imageview1.setFitHeight(150);
            //imageview1.setPreserveRatio(true);
            actionStatus2.setText(selectedFile2.getName());
            System.out.println(selectedFile2.getName());
        } else {
           actionStatus2.setText("File selection cancelled."); }
    }
 @FXML
    private void On_Btn_Retour(ActionEvent event) {
                
         AnchorPane pane = new AnchorPane();
                                    try {
                                        pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilReclamation.fxml"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FXMLAcceuilReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void On_btn_Envoyer(ActionEvent event) {
       Utilisateur u =new Utilisateur();
        
        final String username ="farahfalleh95@gmail.com"; // mail de la personne qui va envoyer 
	  final String password ="2248019020711095";// password de la personne qui va envoyer 

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
                        UtilisateurService rs=new UtilisateurService();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("farahfalleh95@gmail.com")); // same email id
			message.setRecipients(Message.RecipientType.TO,
                                //////////
				InternetAddress.parse(rs.findUserByMatricule(matricule.getText()).getEmail()));// mail de la personne qui va recevoir le mail
			message.setSubject("VOITURE MAL STATIONNEE");
			message.setText("Merci de venir déplacer votre voiture,"
				+ "\n\n Voiture mal garée ! \n"+"Recalamation envoyée par Mr/Mme "+esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getNom()+" "+esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getPrenom());

			Transport.send(message);

			System.out.println("Mail envoyé avec succés");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }
    
  
}
