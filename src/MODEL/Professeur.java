/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Date;

/**
 *
 * @author OrbitG
 */
public class Professeur extends Utilisateur {

    private String Specialite;

    public Professeur(String string, int aInt, String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSpecialite() {
        return Specialite;
    }

    public void setSpecialite(String Specialite) {
        this.Specialite = Specialite;
    }

    public Professeur() {
    }

    @Override
    public String toString() {
        return "Professeur{" + "Specialite=" + Specialite + '}';
    }

    public Professeur(String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Sexe, String Specialite, String Matricule) {
        super(Email, username, Password, Nom, Prenom, Telephone, Sexe, Matricule);
        this.Specialite = Specialite;
    }

    public Professeur(int ID, String Email, String username, String Password, String Nom, String Prenom,
            String Telephone, String Photo, String Sexe, String Specialite, String Matricule,
            String Role) {
        super(ID, Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Matricule, Role);
        this.Specialite = Specialite;
    }

    

    public Professeur( String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Sexe,String Specialite) {
        super(Email, username, Password, Nom, Prenom, Telephone, Sexe);
        this.Specialite = Specialite;
    }

    public Professeur(String Specialite) {
        this.Specialite = Specialite;
    }

    public Professeur(String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Matricule, String Specialite) {
        super(Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Matricule);
        this.Specialite = Specialite;
    }

    public Professeur(int ID, String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, Date Date_Creation, String Matricule, String Specialite, String Role) {
        super(ID, Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Date_Creation, Matricule, Role);
        this.Specialite = Specialite;
    }

  

}
