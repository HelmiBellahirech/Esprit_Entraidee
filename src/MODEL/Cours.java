/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Blob;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author nadaghanem
 */
public class Cours {

    private SimpleIntegerProperty id;
    private Professeur prof;
    private SimpleStringProperty module;
    private SimpleStringProperty matiere;
    private Date date_pub;
    private Blob fichier;

    public Cours(int id, Professeur prof, String module, String matiere, Date date_pub, Blob fichier) {
        this(prof, module, matiere, date_pub, fichier);
        this.id = new SimpleIntegerProperty(id);
    }

    public Cours(Professeur prof, String module, String matiere, Date date_pub, Blob fichier) {
        this.prof = prof;
        this.module = new SimpleStringProperty(module);
        this.matiere = new SimpleStringProperty(matiere);
        this.date_pub = date_pub;
        this.fichier = fichier;
        this.id = new SimpleIntegerProperty();
    }

    public Cours(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id.get();
    }

    public String getModule() {
        return module.get();
    }

    public String getMatiere() {
        return matiere.get();
    }

    public Date getDate_pub() {
        return date_pub;
    }

    public Blob getFichier() {
        return fichier;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setModule(String module) {
        this.module.set(module);
    }

    public StringProperty moduleProperty() {
        return this.module;
    }

    public StringProperty matiereProperty() {
        return this.matiere;
    }

    public IntegerProperty idProperty() {
        return this.id;
    }

    public void setMatiere(String matiere) {
        this.matiere.set(matiere);
    }

    public void setDate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }

    public void setFichier(Blob fichier) {
        this.fichier = fichier;
    }

    public Professeur getProf() {
        return prof;
    }

    public void setProf(Professeur prof) {
        this.prof = prof;
    }
}
