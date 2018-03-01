/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Covoiturage;
import MODEL.Reservationcov;
import MODEL.Utilisateur;
import SERVICE.Covoiturage_service;
import SERVICE.ReservationcovService;
import SERVICE.UtilisateurService;
import UTILS.InputValidation;
import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author OrbitG
 */
public class FXMLDetailsCovoiturageController implements Initializable, MapComponentInitializedListener,DirectionsServiceCallback{

    private GoogleMap map;
    @FXML
    private Label depart;
    @FXML
    private Label arrive;
    @FXML
    private Label heure;
    @FXML
    private Label comfort;
    @FXML
    private Label fumeur;
    @FXML
    private Label prix;
    @FXML
    private Label departtitre;
    @FXML
    private Label arrivetitre;
    @FXML
    private Label datetitre;
    private static int id;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXButton a;
    @FXML
    private JFXButton Reserver;
    @FXML
    private Label pubpar;
    @FXML
    private Label adresse;
    @FXML
    private Label numtel;
    @FXML
    private Label nbr;
    @FXML
    private ImageView img;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private Pane panemap;
   protected  DirectionsService directionService ;
   protected DirectionsPane DirectionsPane; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Covoiturage c = new Covoiturage_service().findId(id);
        UtilisateurService us = new UtilisateurService();
        Utilisateur u = us.getById(c.getID_USER());
        departtitre.setText(c.getDepart());
        arrivetitre.setText(c.getArrive());
        depart.setText(c.getDepart());
        arrive.setText(c.getArrive());
        datetitre.setText(c.getDate().toString());
        heure.setText(c.getHeure());
        prix.setText(String.valueOf(c.getPrix()));
        fumeur.setText(c.getFumeur());
        comfort.setText(c.getComfort());
        nbr.setText(String.valueOf(c.getNbrPlaces()));
        pubpar.setText(u.getNom() + " " + u.getPrenom());
        adresse.setText(u.getEmail());
        numtel.setText(u.getTelephone());
        File f = new File("C:\\xampp\\htdocs\\pi\\" + u.getPhoto());
        System.out.println(f.toURI().toString());

        Image IMAGE_RUBY = new Image(f.toURI().toString());
        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

        img.setFitHeight(100);
        img.setFitWidth(100);
        Rectangle clip = new Rectangle(
                img.getFitWidth(), img.getFitHeight()
        );

        clip.setArcWidth(20);
        clip.setArcHeight(20);
        img.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img.setClip(null);

        // apply a shadow effect.
        img.setEffect(new DropShadow(20, Color.BLACK));
        
         img.setImage(IMAGE_RUBY);
        mapView.addMapInializedListener(this);
       
    }
    

    public void redirect(String id) {
        System.out.println("Interface Affiche Details ");
        System.out.println(id);
        this.id = Integer.parseInt(id);

    }

    @FXML
    private void a(ActionEvent event) throws IOException {

        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLTajriba.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().setAll(pane);

    }

    @FXML
    private void On_btn_Reserver(ActionEvent event) {
        Covoiturage c = new Covoiturage_service().findId(id);
        Covoiturage_service cs = new Covoiturage_service();
        List<Integer> choices = new ArrayList<>();
        choices.add(1);
        choices.add(2);
        choices.add(3);
        choices.add(4);

        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1, choices);
        dialog.setTitle("Reservation");
        dialog.setHeaderText("Reservation au trajet");
        dialog.setContentText("Choissizer le nombre de places souhaité:");

// Traditional way to get the response value.
        Optional<Integer> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Votre choix: " + result.get());

            if (result.get() > c.getNbrPlaces()) {

                Alert alert = new InputValidation().getAlert("ERREUR", "Le nombre de place est superieur au " + "\n    " + "nombre de place disponible");
                alert.showAndWait();

            } else {

                Reservationcov reservation = new Reservationcov();
                reservation.setID_RESERVE(esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID());
                reservation.setID_CAHUFFEUR(c.getID_USER());
                reservation.setID_ANNONCE(c.getID());
                reservation.setETAT(false);
                reservation.setNBPLACES(result.get());
                ReservationcovService rs = new ReservationcovService();
                if (rs.verifReservation(reservation) != null) {
                    Alert alert = new InputValidation().getAlert("ERREUR", "Vous avez deja reservé a ce trajet");
                    alert.showAndWait();
                } else {
                    rs.add(reservation);
                    Alert alert = new InputValidation().getAlert("Succes", "Votre Demande a été envoyé avec succes");
                    alert.showAndWait();
                    AnchorPane pane = new AnchorPane();

                    /*c.setNbrPlaces(c.getNbrPlaces() - result.get());
                cs.update(c);*/
                    try {
                        pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLTajriba.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDetailsCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rootpane.getChildren().setAll(pane);

                }
            }
        }

    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @Override
    public void mapInitialized() {
        Covoiturage_service cs = new Covoiturage_service();
        Covoiturage c = new Covoiturage();
        c = cs.findId(id);
        //Set the initial properties of the map.
        Double l = 0.0;
        Double log = 0.0;
        Double l1 = 0.0;
        Double log1 = 0.0;
        String adresse = c.getDepart();
        String adresse1 = c.getArrive();
        String lat, lon;
        directionService = new DirectionsService();
        String locationAddres1 = adresse1.replaceAll(" ", "%20");
        String locationAddres = adresse.replaceAll(" ", "%20");
      String str = "http://maps.googleapis.com/maps/api/geocode/json?address=" + locationAddres + "&sensor=true";

        String str1 = "http://maps.googleapis.com/maps/api/geocode/json?address=" + locationAddres1 + "&sensor=true";

        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(l, log))
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(new LatLong(l, log))
                .visible(Boolean.TRUE)
                .title("Depart");

        Marker marker = new Marker(markerOptions);
        MarkerOptions markerOptions1 = new MarkerOptions();

        markerOptions1.position(new LatLong(l1, log1))
                .visible(Boolean.TRUE)
                .title("Arrive");

        Marker marker1 = new Marker(markerOptions1);

     //   map.addMarker(marker);
       // map.addMarker(marker1);
     
         DirectionsPane = mapView.getDirec();
       
        DirectionsRequest request = new DirectionsRequest(adresse,adresse1,TravelModes.DRIVING);
        directionService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), DirectionsPane));


    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        
    }

}
