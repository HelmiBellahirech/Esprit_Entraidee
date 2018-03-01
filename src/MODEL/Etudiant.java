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
public class Etudiant extends Utilisateur{

    private String Classe;
    private int ID_CLUB;
    
    

    
    public String getClasse() {
        return Classe;
    }

    public void setClasse(String Classe) {
        this.Classe = Classe;
    }

    public int getID_CLUB() {
        return ID_CLUB;
    }

    public void setID_CLUB(int ID_CLUB) {
        this.ID_CLUB = ID_CLUB;
    }

    public Etudiant( String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Sexe, String Classe,String Matricule, int ID_CLUB) {
        super(Email, username, Password, Nom, Prenom, Telephone, Sexe, Matricule);
        this.Classe = Classe;
        this.ID_CLUB = ID_CLUB;
    }

    public Etudiant( String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Sexe,String Classe, int ID_CLUB) {
        super(Email, username, Password, Nom, Prenom, Telephone, Sexe);
        this.Classe = Classe;
        this.ID_CLUB = ID_CLUB;
    }

    public Etudiant( int ID, String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Matricule, String Role,String Classe, int ID_CLUB) {
        super(ID, Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Matricule, Role);
        this.Classe = Classe;
        this.ID_CLUB = ID_CLUB;
    }
    

    public Etudiant(String Classe, int ID_CLUB) {
        this.Classe = Classe;
        this.ID_CLUB = ID_CLUB;
    }

    public Etudiant( String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Matricule,String Classe, int ID_CLUB) {
        super(Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Matricule);
        this.Classe = Classe;
        this.ID_CLUB = ID_CLUB;
    }

    public Etudiant( int ID, String Email, String username, String Telephone, String Photo,String Classe, int ID_CLUB) {
        super(ID, Email, username, Telephone, Photo);
        this.Classe = Classe;
        this.ID_CLUB = ID_CLUB;
    }

    public Etudiant(int ID, String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, Date Date_Creation, String Matricule, String Role,String Classe, int ID_CLUB) {
        super(ID, Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Date_Creation, Matricule, Role);
        this.Classe = Classe;
        this.ID_CLUB = ID_CLUB;
    }

   
    public Etudiant() {
    }

    @Override
    public String toString() {
        return super.toString()+"Classe "+Classe; //To change body of generated methods, choose Tools | Templates.
    }

  
   

   
}
