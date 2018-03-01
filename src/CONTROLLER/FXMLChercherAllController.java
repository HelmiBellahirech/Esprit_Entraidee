/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Colocation;
import MODEL.Covoiturage;
import SERVICE.Colocation_service;
import SERVICE.Covoiturage_service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.LabeledText;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FXMLChercherAllController implements Initializable {


    @FXML
    private JFXTextField prixMax;
    @FXML
    private JFXTextField prixMin;
    @FXML
    private JFXButton chercherA;
    @FXML
    private JFXListView<Colocation> listv;
  
    @FXML
    private JFXTextField type_log1;
    @FXML
    private JFXButton retour;
    @FXML
    private AnchorPane pane2;
    String adresse = FXMLAfficheCollocationController.Adresse;
    List<Colocation> colocations = new ArrayList<>() ; 
    Colocation_service col = new Colocation_service();

     


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(adresse.equals(""))
        {
            colocations = col.getAll();
        }
       
        if(!adresse.equals(""))
        {colocations=col.findByAdresse(adresse) ; }
        
        ObservableList<Colocation> items = FXCollections.observableArrayList(colocations);
        listv.setCellFactory(new Callback<ListView<Colocation>, ListCell<Colocation>>(){
 
            @Override
            public ListCell<Colocation> call(ListView<Colocation> p) {
                 
                ListCell<Colocation> cell = new ListCell<Colocation>(){
 
                    @Override
                    protected void updateItem(Colocation t, boolean bln) {
                        super.updateItem(t, bln);
                         if (t != null) {
                        
                           listv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                    Colocation col = listv.getItems().get(listv.getSelectionModel().getSelectedIndex());
                                    FXMLInfoCollocationController l = new FXMLInfoCollocationController();
                                   l.redirect(String.valueOf(col.getId()));
                                   System.out.println(col.getId());
                                    AnchorPane pane = new AnchorPane();
                                    try {
                                        pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLInfoCollocation.fxml"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FXMLInfoCollocationController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    pane2.getChildren().setAll(pane);
                                }
                            }

                        });
                            setText( t.getTitre() +"\n"+"prix :"+t.getPrix()+ "\n"+"Disponible Ã  parir de :"+t.getDate_dispo()+ "\n"+"Type de logement:"+t.getType_log());
                        }
                    }
 
                };
                 
                return cell;
                
            }
              
        });
        listv.setItems(items);
        FilteredList<Colocation> filteredData = new  FilteredList<>(items, e -> true);
         type_log1.setOnKeyReleased( e ->{
         type_log1.textProperty().addListener((observable, oldValue, newValue) -> {
             filteredData.setPredicate((Predicate<? super Colocation>) colocation -> {
               if (newValue == null || newValue.isEmpty()){
                     return true;
                 }
                String lowerCaseFilter = newValue.toLowerCase();
               if (colocation.getType_log().toLowerCase().contains(CharSequence.class.cast(lowerCaseFilter))){
                     return true;
                 }         
                 return false;
                 
             });
         });
         SortedList<Colocation> sortedData = new SortedList<>(filteredData);
         //sortedData.comparatorProperty().bind(listv.comparatorProperty());
          listv.setItems(sortedData);
            
        });
     
}
    
    @FXML
    private void chercher_A(ActionEvent event) {
        if (prixMin.getText().equals("") == true) {
            prixMin.setText("0");
        }
        if (prixMax.getText().equals("") == true) {
            prixMax.setText("99999999");
        }
        if(adresse.equals(""))
        {
            colocations = new Colocation_service().getAll().stream()
             
                .filter(s -> s.getPrix() >= Integer.parseInt(prixMin.getText()))
                .filter(s -> s.getPrix() <= Integer.parseInt(prixMax.getText()))
                .collect(Collectors.toList());
        }
                if(!adresse.equals(""))
        {colocations = new Colocation_service().getAll().stream()
                .filter(s -> s.getAdresse().equals(adresse))
                .filter(s -> s.getPrix() >= Integer.parseInt(prixMin.getText()))
                .filter(s -> s.getPrix() <= Integer.parseInt(prixMax.getText()))
                .collect(Collectors.toList()); }
           ObservableList<Colocation> items = FXCollections.observableArrayList(colocations);
        System.out.println(colocations);
        items.clear();
//        listv.getItems().clear();
        prixMin.setText("");
        prixMax.setText("");
        colocations.forEach(e -> {
            items.add(e);
            System.out.println(e);
        });
                   
 listv.setCellFactory(new Callback<ListView<Colocation>, ListCell<Colocation>>(){
 
            @Override
            public ListCell<Colocation> call(ListView<Colocation> p) {
                 
                ListCell<Colocation> cell = new ListCell<Colocation>(){
 
                    @Override
                    protected void updateItem(Colocation t, boolean bln) {
                        super.updateItem(t, bln);
                         if (t != null) {
                        
                           listv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                    Colocation col = listv.getItems().get(listv.getSelectionModel().getSelectedIndex());
                                    FXMLInfoCollocationController l = new FXMLInfoCollocationController();
                                   l.redirect(String.valueOf(col.getId()));
                                   System.out.println(col.getId());
                                    AnchorPane pane = new AnchorPane();
                                    try {
                                        pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLInfoCollocation.fxml"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FXMLInfoCollocationController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    pane2.getChildren().setAll(pane);
                                }
                            }

                        });
                            setText( t.getTitre() +"\n"+"prix :"+t.getPrix()+ "\n"+"Disponible Ã  parir de :"+t.getDate_dispo()+ "\n"+"Type de logement:"+t.getType_log());
                        }
                    }
 
                };
                 
                return cell;
                
            }
              
        });
        listv.setItems(items);
            listv.setItems(items);
        FilteredList<Colocation> filteredData = new  FilteredList<>(items, e -> true);
         type_log1.setOnKeyReleased( e ->{
         type_log1.textProperty().addListener((observable, oldValue, newValue) -> {
             filteredData.setPredicate((Predicate<? super Colocation>) colocation -> {
               if (newValue == null || newValue.isEmpty()){
                     return true;
                 }
                String lowerCaseFilter = newValue.toLowerCase();
               if (colocation.getType_log().toLowerCase().contains(CharSequence.class.cast(lowerCaseFilter))){
                     return true;
                 }         
                 return false;
                 
             });
         });
         SortedList<Colocation> sortedData = new SortedList<>(filteredData);
         //sortedData.comparatorProperty().bind(listv.comparatorProperty());
          listv.setItems(sortedData);
            
        });
     
        
    }


    @FXML
    private void but_retour(ActionEvent event) {
         AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheCollocation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficheCollocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane2.getChildren().setAll(pane);
    }
        


   
    
     }


