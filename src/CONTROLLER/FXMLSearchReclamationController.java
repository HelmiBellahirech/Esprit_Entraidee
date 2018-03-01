/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Reclamation;
import SERVICE.ReclamationService;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLSearchReclamationController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private Label searchLabel;

    private ResultSet rs = null;
    private ObservableList<Reclamation> data;

    @FXML
    private JFXListView<Reclamation> List;
    ReclamationService sb = new ReclamationService();
    List<Reclamation> reclamations = sb.getAll();
    @FXML
    private Button Retour;
    @FXML
    private AnchorPane rootpane;
    ObservableList<Reclamation> items = FXCollections.observableArrayList(reclamations);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        //loadDataFromDatabase();

        //////////////////////////////////////
        System.out.println(items);
        List.setCellFactory((ListView<Reclamation> arg0) -> {
            ListCell<Reclamation> cell;
            cell = new ListCell<Reclamation>() {
                @Override
                protected void updateItem(Reclamation re, boolean btl) {
                    super.updateItem(re, btl);
                    if (re != null) {
                        //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                        File file = new File("src\\images.png");
                        file.getParentFile().mkdirs();
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

                        ImageView imgview = new ImageView(IMAGE_RUBY);

                        setGraphic(imgview);

                        imgview.setFitHeight(70);
                        imgview.setFitWidth(70);
                        Rectangle clip = new Rectangle(
                                imgview.getFitWidth(), imgview.getFitHeight()
                        );

                        clip.setArcWidth(20);
                        clip.setArcHeight(20);
                        imgview.setClip(clip);

                        // snapshot the rounded image.
                        SnapshotParameters parameters = new SnapshotParameters();
                        parameters.setFill(Color.TRANSPARENT);
                        WritableImage image = imgview.snapshot(parameters, null);

                        // remove the rounding clip so that our effect can show through.
                        imgview.setClip(null);

                        // apply a shadow effect.
                        imgview.setEffect(new DropShadow(20, Color.BLACK));

                        // store the rounded image in the imageView.
                        imgview.setImage(image);

                        if ("Reclamation Objet perdu".equals(re.getType())) {
                            setText(" Type de Réclamation: " + re.getType() + " \n Description: " + re.getDescription() + "\n Date: " + re.getDate_decouverte() + "\n Lieu :" + re.getLieu_decouverte() + "\n Type d'objet perdu/trouvé: " + re.getTypeobj_perdu() + "\n  autre: " + re.getAutretypeobj_perdu() + "\n Ou exactement ? " + re.getLocalisation() + "\n autre : " + re.getAutrelocalisation() + "\n Etage: " + re.getEtage() + "\n Salle: " + re.getSalle() + "\n");
                        } else if ("Reclamation Objet trouvé".equals(re.getType())) {
                            setText(" Type de Réclamation: " + re.getType() + "\n  Description: " + re.getDescription() + "\n Date: " + re.getDate_decouverte() + "\n Lieu :" + re.getLieu_decouverte() + "\n Type d'objet perdu/trouvé: " + re.getTypeobj_perdu() + "\n  autre: " + re.getAutretypeobj_perdu() + "\n Ou exactement ? " + re.getLocalisation() + "\n autre : " + re.getAutrelocalisation() + "\n Etage: " + re.getEtage() + "\n Salle: " + re.getSalle() + "\n");
                        } else {
                            setText(" Type de Réclamation: " + re.getType() + "\n Matricule: " + re.getMatricule());
                        }
                        //setText(" Description: " +re.getDescription()+ "\n Date: " + re.getDate_decouverte()+ "\n Lieu :"  + re.getLieu_decouverte()+ "\n Type d'objet perdu/trouvé: " + re.getTypeobj_perdu()+ "\n type de votre réclamation: " + re.getType()+ "\n  autre: " + re.getAutretypeobj_perdu()+ "\n Ou exactement ? " + re.getLocalisation()+ "\n autre : " + re.getAutrelocalisation()+ "\n Etage: " + re.getEtage()+ "\n Matricule: " + re.getMatricule()+ "\n Salle: " + re.getSalle()+ "\n" );

                        setFont(Font.font("Berlin Sans FB Demi Bold", 13));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        List.setItems(items);


        ////////////
        /*
            FilteredList<Reclamation> filteredData=new FilteredList<>(data,e->true);
    search.setOnKeyReleased(e->{
    search.textProperty().addListener((observableVlue, oldValue, newValue)->{
    filteredData.setPredicate((Predicate<? super Reclamation>) Reclamation->{
         
        if(newValue==null ||newValue.isEmpty()){
        return true;
        }
        String lowerCaseFilter=newValue.toLowerCase();
        /*if(Reclamation.getId().getValue().contains(newValue)){
        return true;
        }else */ /*if(Reclamation.getTypeobj_perdu().toLowerCase().contains(lowerCaseFilter)){
        return true;
        }else if(Reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)){
        return true;
        }else if(Reclamation.getLieu_decouverte().toLowerCase().contains(lowerCaseFilter)){
        return true;
        }else if(Reclamation.getMatricule().toLowerCase().contains(lowerCaseFilter)){
        return true;}
        else if(Reclamation.getType().toLowerCase().contains(lowerCaseFilter)){
        return true;}
        else if(Reclamation.getLocalisation().toLowerCase().contains(lowerCaseFilter)){
        return true;}
        else if(Reclamation.getAutretypeobj_perdu().toLowerCase().contains(lowerCaseFilter)){
        return true;}
        return false;
    });
    });
        SortedList<Reclamation> sortedData=new SortedList<>(filteredData);
       // sortedData.comparatorProperty().bind(List.comparatorProperty());
        List.setItems(sortedData);
    });*/

    }
/*
    public ArrayList<Reclamation> recherche(String x) {
        System.out.println("Recheeeerche ");
        List<Reclamation> reclam = new ArrayList<>();
        List<Reclamation> all = new ArrayList<>();
        all = sb.getAll();
        all.stream().forEach(System.out::println);
        System.out.println("*************");
        all.stream().filter(t -> t.getTypeobj_perdu().startsWith("PC")).forEach(System.out::println);
        reclam = all.stream().filter(t -> t.getTypeobj_perdu().startsWith("PC")).collect(Collectors.toList());
        System.out.println("*************");
        System.out.println("Ahmeed");
        reclam.stream().forEach(System.out::println);
        return (ArrayList<Reclamation>) reclam;
    }*/

    /*
      private void loadDataFromDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/zerobug";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM reclamation");
            while (rs.next()) {
                
                int id_Rec = rs.getInt("id");
                String description_Rec = rs.getString("description");
                Date date_decouverte_Rec = rs.getDate("date_decouverte");
                String lieu_decouverte_Rec = rs.getString("lieu_decouverte"); 
                String typeobj_perdu_Rec = rs.getString("typeobj_perdu") ; 
                String type_Rec = rs.getString("type") ;
                String autetypeobj_perdu_Rec = rs.getString("autretypeobj_perdu") ;
                String localisation_Rec = rs.getString("localisation") ;
                String autrelocalisation_Rec = rs.getString("autrelocalisation") ;
                String etage_Rec = rs.getString("etage") ;
                //String voiture_Rec = rs.getString("voiture") ;
                String matricule_Rec = rs.getString("matricule") ;
                String salle_Rec = rs.getString("salle") ;
                String photo_Rec = rs.getString("photo") ;
                String photo2_Rec = rs.getString("photo2") ;
                data.add(new Reclamation(id_Rec,description_Rec,date_decouverte_Rec,lieu_decouverte_Rec,typeobj_perdu_Rec,type_Rec,autetypeobj_perdu_Rec,localisation_Rec,autrelocalisation_Rec,etage_Rec,matricule_Rec,salle_Rec,photo_Rec,photo2_Rec));
                System.out.println("recup table view ok !");
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
} */
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
    private void findKey(KeyEvent event) {
        
        String mot = search.getText();
//        System.out.println(mot);
//        if (mot.length() > 0) {
//            System.out.println("Condition valide");
//           // ArrayList<Reclamation> rec = recherche(mot);
////            List.getItems().clear();
//List.getItems().clear();
//            List.getItems().addAll(sb.getAll().stream().filter(t->{
//                return t.getMatricule().contains("1");
//            }).collect(Collectors.toList()));
////List.setItems((ObservableList<Reclamation>)sb.getAll().stream().filter(t->t.getTypeobj_perdu().startsWith("PC")).collect(Collectors.toList()));
//        } else {
//            List.getItems().clear();
//            List.getItems().addAll(sb.getAll());
//        }
        if (mot.length() > 0) {
            System.out.println("Eveneement Ok ");
            ArrayList<Reclamation> rec = (ArrayList<Reclamation>) sb.rechercher(mot);
            ObservableList<Reclamation> find = FXCollections.observableArrayList(sb.rechercher(mot));
            List.getItems().clear();
            rec.stream().forEach(System.out::println);
            List.setItems(find);
        } else {
            System.out.println("jajkajaka");
            //List.getItems().clear();
            //List.setItems((ObservableList<Reclamation>) sb.getAll());
             ReclamationService sb = new ReclamationService();
               List<Reclamation> reclamations = sb.getAll();
                ObservableList<Reclamation> items = FXCollections.observableArrayList(reclamations);

            System.out.println(items);
            
            
            /////////////////
            List.setCellFactory((ListView<Reclamation> arg0) -> {
            ListCell<Reclamation> cell;
            cell = new ListCell<Reclamation>() {
                @Override
                protected void updateItem(Reclamation re, boolean btl) {
                    super.updateItem(re, btl);
                    if (re != null) {
                        //    File file = new File(ps.findById(e.getPassager().getId()).getAvatar());
                        File file = new File("src\\images.png");
                        file.getParentFile().mkdirs();
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                        //   Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getId()).getAvatar());

                        ImageView imgview = new ImageView(IMAGE_RUBY);

                        setGraphic(imgview);

                        imgview.setFitHeight(70);
                        imgview.setFitWidth(70);
                        Rectangle clip = new Rectangle(
                                imgview.getFitWidth(), imgview.getFitHeight()
                        );

                        clip.setArcWidth(20);
                        clip.setArcHeight(20);
                        imgview.setClip(clip);

                        // snapshot the rounded image.
                        SnapshotParameters parameters = new SnapshotParameters();
                        parameters.setFill(Color.TRANSPARENT);
                        WritableImage image = imgview.snapshot(parameters, null);

                        // remove the rounding clip so that our effect can show through.
                        imgview.setClip(null);

                        // apply a shadow effect.
                        imgview.setEffect(new DropShadow(20, Color.BLACK));

                        // store the rounded image in the imageView.
                        imgview.setImage(image);

                        if ("Reclamation Objet perdu".equals(re.getType())) {
                            setText(" Type de Réclamation: " + re.getType() + " \n Description: " + re.getDescription() + "\n Date: " + re.getDate_decouverte() + "\n Lieu :" + re.getLieu_decouverte() + "\n Type d'objet perdu/trouvé: " + re.getTypeobj_perdu() + "\n  autre: " + re.getAutretypeobj_perdu() + "\n Ou exactement ? " + re.getLocalisation() + "\n autre : " + re.getAutrelocalisation() + "\n Etage: " + re.getEtage() + "\n Salle: " + re.getSalle() + "\n");
                        } else if ("Reclamation Objet trouvé".equals(re.getType())) {
                            setText(" Type de Réclamation: " + re.getType() + "\n  Description: " + re.getDescription() + "\n Date: " + re.getDate_decouverte() + "\n Lieu :" + re.getLieu_decouverte() + "\n Type d'objet perdu/trouvé: " + re.getTypeobj_perdu() + "\n  autre: " + re.getAutretypeobj_perdu() + "\n Ou exactement ? " + re.getLocalisation() + "\n autre : " + re.getAutrelocalisation() + "\n Etage: " + re.getEtage() + "\n Salle: " + re.getSalle() + "\n");
                        } else {
                            setText(" Type de Réclamation: " + re.getType() + "\n Matricule: " + re.getMatricule());
                        }
                        //setText(" Description: " +re.getDescription()+ "\n Date: " + re.getDate_decouverte()+ "\n Lieu :"  + re.getLieu_decouverte()+ "\n Type d'objet perdu/trouvé: " + re.getTypeobj_perdu()+ "\n type de votre réclamation: " + re.getType()+ "\n  autre: " + re.getAutretypeobj_perdu()+ "\n Ou exactement ? " + re.getLocalisation()+ "\n autre : " + re.getAutrelocalisation()+ "\n Etage: " + re.getEtage()+ "\n Matricule: " + re.getMatricule()+ "\n Salle: " + re.getSalle()+ "\n" );

                        setFont(Font.font("Berlin Sans FB Demi Bold", 13));

                        // setAlignment(Pos.CENTER);
                    }

                }

            };
            return cell;
        });
        List.setItems(items);
        }

        // List.getItems().addAll(rec);
    }

}
