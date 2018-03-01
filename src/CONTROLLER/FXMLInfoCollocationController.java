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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FXMLInfoCollocationController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    @FXML
    private Label typr_log;
    @FXML
    private Label nbChambre;
    @FXML
    private Label prix;
    @FXML
    private Label nbPersonne;
    @FXML
    private Label meu;
    @FXML
    private Label eta;
    @FXML
    private Label date_dis;
    @FXML
    private JFXButton retour;
    @FXML
    private AnchorPane pa;
    private static int id ; 
    @FXML
    private GoogleMapView mapView;
    @FXML
    private VBox notic;
    @FXML
    private JFXTextField adresse;
      protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected String from=" Z.I. Chotrana II B.P. 160، Pôle Technologique El Ghazela Ariana 2088";
    protected StringProperty to = new SimpleStringProperty();
    @FXML
    private JFXButton Aff;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Colocation col = new Colocation_service().findId(id);
        typr_log.setText(col.getType_log());
        nbChambre.setText(String.valueOf(col.getNbChambre()));
        prix.setText(String.valueOf(col.getPrix()));
        meu.setText(col.getMeuble());
        eta.setText(col.getEtage());
        nbPersonne.setText(String.valueOf(col.getNbPersonne()));
        date_dis.setText(col.getDate_dispo().toString());
        adresse.setText(col.getAdresse());
        
        mapView.addMapInializedListener(this);
        to.bindBidirectional(adresse.textProperty());
        
    }    
      public void redirect(String id){
        System.out.println("Interface Affiche Details ");
        System.out.println(id);
        this.id = Integer.parseInt(id);
    
    }
    

    @FXML
    private void retour_but(ActionEvent event) {
           AnchorPane pane = new AnchorPane();
                                    try {
                                        pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLChercherAll.fxml"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(FXMLInfoCollocationController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    pa.getChildren().setAll(pane);
    }
    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
    }
    private static final double latitude = 36.898392;
    private static final double longitude = 10.189732000000049;


    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        MarkerOptions markerOptions = new MarkerOptions();
        LatLong markerLatLong = new LatLong(latitude, longitude);
        markerOptions.position(markerLatLong)
                .title("Esprit")
                .animation(Animation.DROP)
                .visible(true);
        Marker myMarker = new Marker(markerOptions);

        options.center(new LatLong(36.8189700, 10.1657900))
                .zoomControl(true)
                .zoom(8)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
        map.addMarker(myMarker);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content("<h2>ESPRIT:Pôle Technologique El Ghazela، Ariana 2088</h2>");     
        InfoWindow window = new InfoWindow(infoOptions);
             window.open(map, myMarker);
    }

    @FXML
    private void monaction(ActionEvent event) {
        DirectionsRequest request = new DirectionsRequest(from, to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
    }
    
}
