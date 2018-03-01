/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Colocation;
import MODEL.Covoiturage;
import SERVICE.Colocation_service;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FXMLMesCollocationController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXListView<Colocation> List;
    Colocation_service col = new Colocation_service();
    List<Colocation> colocations = col.findId_user(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Colocation> items = FXCollections.observableArrayList(colocations);
          List.setCellFactory(new Callback<ListView<Colocation>, ListCell<Colocation>>(){
 
            @Override
            public ListCell<Colocation> call(ListView<Colocation> p) {
                 
                ListCell<Colocation> cell = new ListCell<Colocation>(){
 
                    @Override
                    protected void updateItem(Colocation t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            List.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                    Colocation col = List.getItems().get(List.getSelectionModel().getSelectedIndex());
                                    FXMLUpdateDeleteColocationController l = new FXMLUpdateDeleteColocationController();
                                    l.redirect(String.valueOf(col.getId()));
                                    AnchorPane pane = new AnchorPane();
                                    try {
                                        pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLUpdateDeleteColocation.fxml"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FXMLInfoCollocationController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    rootpane.getChildren().setAll(pane);
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
          List.setItems(items);
      
    }    
    
}
