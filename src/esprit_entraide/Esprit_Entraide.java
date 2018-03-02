 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit_entraide;

import MODEL.Etudiant;
import MODEL.Utilisateur;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author OrbitG
 */
public class Esprit_Entraide extends Application {

    private Stage stage;
    private static Esprit_Entraide instance;
    private Scene scene;
    private Etudiant loggedEtudiant; 
    private Utilisateur loggedUser;
    private Utilisateur loggedAdmin;

    public Etudiant getLoggedEtudiant() {
        return loggedEtudiant;
    }

    public void setLoggedEtudiant(Etudiant loggedEtudiant) {
        this.loggedEtudiant = loggedEtudiant;
    }
 
        
    public Esprit_Entraide() throws IOException {
      //  note.play();
        instance = this;
        scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLLogin.fxml")));
         
    }

    public static Esprit_Entraide getInstance() {
        return instance;
        
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        this.stage = stage;
        
        //stage.setResizable(false);
        stage.setScene(this.scene);
        stage.show();
        
        

    }

    public void ChangeScene(Scene scene) {
        this.scene = scene;
        stage.setScene(scene);
        stage.show();
    }

    public void setLoggedUser(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedAdmin(Utilisateur loggedAdmin) {
        this.loggedAdmin = loggedAdmin;
    }

    public Utilisateur getLoggedAdmin() {
        return loggedAdmin;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

}
