/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import ISERVICE.ICoursService;
import ISERVICE.IEtudiantService;
import ISERVICE.IProfService;
import MODEL.Cours;
import MODEL.Etudiant;
import MODEL.Professeur;
import MODEL.Utilisateur;
import SERVICE.CoursService;
import SERVICE.EtudiantService;
import SERVICE.ProfService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.sql.rowset.serial.SerialBlob;
import UTILS.MailHelpers;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author nadaghanem
 */
public class FXMLCoursController implements Initializable {

    @FXML
    ComboBox moduleCb;
    @FXML
    ComboBox matiereCb;
    @FXML
    Button fileChooserBtn;
    @FXML
    TextField filePathTxt;
    @FXML
    TableView coursTable;

    Professeur nada;
    IProfService profService = new ProfService();
    ICoursService coursService = new CoursService();
    IEtudiantService etudiantService = new EtudiantService();    
    @FXML
    private TableColumn<Cours, Integer> idCol;
    @FXML
    private TableColumn<Cours, String> profCol;
    @FXML
    private TableColumn<Cours, String> moduleCol;
    @FXML
    private TableColumn<Cours, String> matiereCol;
    @FXML
    private TableColumn<Cours, Button> fileCol;
    @FXML
    private TableColumn<Cours, Date> dateCol;
    @FXML
    private TableColumn<Cours, Button> actionCol;

    @FXML
    private VBox crudVBox;

    ObservableList<Cours> data = FXCollections.observableArrayList();
    List<Etudiant> etudiantList;

    Utilisateur loggedUser;
    @FXML
    private CheckBox notifCk;
    @FXML
    private AnchorPane rootContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moduleCb.getItems().addAll("Math", "Informatique", "réseau", "unix", "analyse");
        matiereCb.getItems().addAll("Algèbre", "Java", "analyse financiere", "analyse numerique");
        //default prof nada
        nada = profService.findId(1);
        setCellsTable();
        loadCoursTable();

        coursTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Cours cours = (Cours) newSelection;
                moduleCb.setValue(cours.getModule());
                matiereCb.setValue(cours.getMatiere());
            }
        });

        etudiantList = etudiantService.getAll();
        loggedUser = esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser();
        if (loggedUser.getRole().equals("Etudiant")) {
            crudVBox.setVisible(false);
            actionCol.setVisible(false);
        }
    }

    @FXML
    public void addCours() {
        Blob blob = null;
        try {
            blob = new SerialBlob(convertFileContentToBlob(file));
        } catch (IOException ex) {
            Logger.getLogger(FXMLCoursController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Professeur prof = profService.findId(1);
        Cours cours = new Cours(nada, moduleCb.getValue().toString(), matiereCb.getValue().toString(), new Date(), blob);
        if (coursService.add(cours)) {
            data.add(cours);
            if (notifCk.isSelected()) {
                for (int i = 0; i < etudiantList.size(); i++) {
                    MailHelpers.sendMail(etudiantList.get(i).getEmail(), cours, prof);
                }
            }
        }
    }

    @FXML
    private void On_btn_Menu(ActionEvent event) {
        System.out.println("menu principal");
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/FXMLAcceuilUtilisateur.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjoutCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootContainer.getChildren().setAll(pane);
    }

    @FXML
    public void modifCours() {
        Blob blob = null;
        if (file != null) {
            try {
                blob = new SerialBlob(convertFileContentToBlob(file));
            } catch (IOException ex) {
                Logger.getLogger(FXMLCoursController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Cours selectedCours = (Cours) coursTable.getSelectionModel().getSelectedItem();
        selectedCours.setModule(moduleCb.getValue().toString());
        selectedCours.setMatiere(matiereCb.getValue().toString());
        selectedCours.setDate_pub(new Date());
        if (blob != null) {
            selectedCours.setFichier(blob);
        }
        coursService.update(selectedCours);
    }

    //convertir un fichier File à un tableau binaire byte[]
    public static byte[] convertFileContentToBlob(File file) throws IOException {
        byte[] fileContent = new byte[(int) file.length()];
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            inputStream.read(fileContent);
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " + e.getMessage());
        } finally {
            // close input stream
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return fileContent;
    }

    private void loadCoursTable() {
        try {
            List<Cours> coursList = coursService.getAll();
            data.addAll(coursList);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        coursTable.setItems(data);
    }

    public void setCellsTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //récupérer l'objet prof à partir de l'objet cours et combiner le nom et le prénom
        profCol.setCellValueFactory((CellDataFeatures<Cours, String> p) -> new ReadOnlyObjectWrapper<>(p.getValue().getProf().getPrenom() + " " + p.getValue().getProf().getNom()));
        moduleCol.setCellValueFactory(new PropertyValueFactory<>("module"));
        matiereCol.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        //intégrer la cellule personalisé "télécharger"
        fileCol.setCellFactory((TableColumn<Cours, Button> p) -> new ButtonCell(coursTable));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date_pub"));
        actionCol.setCellFactory((TableColumn<Cours, Button> p) -> new ActionCell(coursTable));
    }

    public class ActionCell extends TableCell<Cours, Button> {

        final Button deleteButton = new Button("Supprimer");

        ActionCell(final TableView tblView) {
            deleteButton.setOnAction((ActionEvent t) -> {
                int selectdIndex = getTableRow().getIndex();
                Cours cours = (Cours) tblView.getItems().get(selectdIndex);
                if (coursService.remove(cours.getId())) {
                    tblView.getItems().remove(selectdIndex);
                } else {
                    System.out.println("Erreur suppression cours : " + cours.getId());
                }
            });
        }

        @Override
        protected void updateItem(Button t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(deleteButton);
            } else {
                setGraphic(null);
            }
        }
    }
    //créer une cellule personnalisé avec button "Télécharger" pour l'intégrer au TableView

    public class ButtonCell extends TableCell<Cours, Button> {

        final Button cellButton = new Button("Télécharger");

        ButtonCell(final TableView tblView) {
            cellButton.setOnAction((ActionEvent t) -> {
                //choisir le dossier ou on va sauvgarder le fichier téléchargé
                File path = showDirectoryChooser();
                int selectdIndex = getTableRow().getIndex();
                //récupérer l'objet Cours qui contient notre fichier a sauvgarder dynamiquement à partir de tableview
                Cours cours = (Cours) tblView.getItems().get(selectdIndex);
                System.out.println("saving file " + cours.getMatiere() + "...");
                Blob blob = cours.getFichier();
                try {
                    //sauvgarde de fichier bytes -> file
                    saveFile(path + "\\" + cours.getMatiere() + cours.getId() + ".pdf", blob.getBytes(1, (int) blob.length()));
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLCoursController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        @Override
        protected void updateItem(Button t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            } else {
                setGraphic(null);

            }
        }

        private void saveFile(String path, byte[] bytes) {
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(bytes);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLCoursController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //afficher une fenêtre pour selectionner un fichier à uploader avec le cours
    final FileChooser fileChooser = new FileChooser();
    File file;

    @FXML
    public void showFileChooser(ActionEvent e) {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            filePathTxt.setText(file.getPath());
        }
    }

    //afficher une fenêtre pour selectionner le dossier ou on va télécharger le cours
    final DirectoryChooser dirChooser = new DirectoryChooser();

    public File showDirectoryChooser() {
        File dir = dirChooser.showDialog(null);
        return dir;
    }
}
