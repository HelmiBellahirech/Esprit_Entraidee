/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class Reclamation {
   private int id;
   private int id_user;
   private String typeobj_perdu ;
   private String description;
   private Date  date_decouverte;
   private String lieu_decouverte; 
   private String type;
   private String autretypeobj_perdu;
   private String localisation;
   private String autrelocalisation;
   private String etage;
   private String matricule;
   private String salle;
   private String photo;
   private String photo2;
   
   public Reclamation(int id, String description, Date date_decouverte, String lieu_decouverte, String typeobj_perdu, String type, String autretypeobj_perdu, String localisation, String autrelocalisation, String etage, String matricule, String salle, String photo, String photo2) {
        this.id = id;
        this.description = description;
        this.date_decouverte = date_decouverte;
        this.lieu_decouverte = lieu_decouverte;
        this.typeobj_perdu = typeobj_perdu;  
        this.type = type;
        this.autretypeobj_perdu = autretypeobj_perdu;
        this.localisation = localisation;
        this.autrelocalisation = autrelocalisation;
        this.etage = etage;
        this.matricule = matricule;
        this.salle = salle;
        this.photo = photo;
        this.photo2 = photo2;
    }
   
  public Reclamation(int id, String description, Date date_decouverte, String lieu_decouverte, String typeobj_perdu, String type, String autretypeobj_perdu, String localisation, String autrelocalisation, String etage, String matricule, String salle, String photo, String photo2,int id_user) {
        this.id = id;
        this.description = description;
        this.date_decouverte = date_decouverte;
        this.lieu_decouverte = lieu_decouverte;
        this.typeobj_perdu = typeobj_perdu;  
        this.type = type;
        this.autretypeobj_perdu = autretypeobj_perdu;
        this.localisation = localisation;
        this.autrelocalisation = autrelocalisation;
        this.etage = etage;
        this.matricule = matricule;
        this.salle = salle;
        this.photo = photo;
        this.photo2 = photo2;
        this.id_user=id_user;
    }
   
    public Reclamation(String description, Date date_decouverte, String lieu_decouverte,String typeobj_perdu, String type, String autretypeobj_perdu, String localisation, String autrelocalisation, String etage, String matricule, String salle, String photo, String photo2) {
        this.description = description;
        this.date_decouverte = date_decouverte;
        this.lieu_decouverte = lieu_decouverte;
        this.typeobj_perdu = typeobj_perdu;
        this.type = type;
        this.autretypeobj_perdu = autretypeobj_perdu;
        this.localisation = localisation;
        this.autrelocalisation = autrelocalisation;
        this.etage = etage;
        this.matricule = matricule;
        this.salle=salle;
        this.photo=photo;
        this.photo2=photo2;
    }
     public Reclamation(String description, Date date_decouverte, String lieu_decouverte,String typeobj_perdu, String type, String autretypeobj_perdu, String localisation, String autrelocalisation, String etage, String matricule, String salle, String photo, String photo2,int id_user) {
        this.description = description;
        this.date_decouverte = date_decouverte;
        this.lieu_decouverte = lieu_decouverte;
        this.typeobj_perdu = typeobj_perdu;
        this.type = type;
        this.autretypeobj_perdu = autretypeobj_perdu;
        this.localisation = localisation;
        this.autrelocalisation = autrelocalisation;
        this.etage = etage;
        this.matricule = matricule;
        this.salle=salle;
        this.photo=photo;
        this.photo2=photo2;
        this.id_user=id_user;
    }
     

    public Reclamation() {
        
    }

    public Reclamation(String description, Date date_decouverte, String lieu_decouverte, String typeobj_perdu, String type, String autretypeobj_perdu, String localisation, String autrelocalisation, String etage, String salle, String photo) {
        this.description = description;
        this.date_decouverte = date_decouverte;
        this.lieu_decouverte = lieu_decouverte;
        this.typeobj_perdu = typeobj_perdu;
        this.type = type;
        this.autretypeobj_perdu = autretypeobj_perdu;
        this.localisation = localisation;
        this.autrelocalisation = autrelocalisation;
        this.etage = etage;
        this.salle = salle;
        this.photo = photo;
    }
    public Reclamation(int id,String description, Date date_decouverte, String lieu_decouverte, String typeobj_perdu, String type, String autretypeobj_perdu, String localisation, String autrelocalisation, String etage, String salle, String photo) {
        this.id=id;
        this.description = description;
        this.date_decouverte = date_decouverte;
        this.lieu_decouverte = lieu_decouverte;
        this.typeobj_perdu = typeobj_perdu;
        this.type = type;
        this.autretypeobj_perdu = autretypeobj_perdu;
        this.localisation = localisation;
        this.autrelocalisation = autrelocalisation;
        this.etage = etage;
        this.salle = salle;
        this.photo = photo;
    }

    public Reclamation(String matricule, String photo2,String type) {
        this.matricule = matricule;
        this.photo2 = photo2;
        this.type = type;
    }
    
      public Reclamation(int id,String matricule, String photo2,String type) {
        this.id=id;
        this.matricule = matricule;
        this.photo2 = photo2;
        this.type = type;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
   
    public String getTypeobj_perdu() {
        return typeobj_perdu;
    }

    public void setTypeobj_perdu(String typeobj_perdu) {
        this.typeobj_perdu = typeobj_perdu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_decouverte() {
        return date_decouverte;
    }

    public void setDate_decouverte(Date date_decouverte) {
        this.date_decouverte = date_decouverte;
    }

    public String getLieu_decouverte() {
        return lieu_decouverte;
    }

    public void setLieu_decouverte(String lieu_decouverte) {
        this.lieu_decouverte = lieu_decouverte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAutretypeobj_perdu() {
        return autretypeobj_perdu;
    }

    public void setAutretypeobj_perdu(String autretypeobj_perdu) {
        this.autretypeobj_perdu = autretypeobj_perdu;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getAutrelocalisation() {
        return autrelocalisation;
    }

    public void setAutrelocalisation(String autrelocalisation) {
        this.autrelocalisation = autrelocalisation;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", typeobj_perdu=" + typeobj_perdu + ", description=" + description + ", date_decouverte=" + date_decouverte + ", lieu_decouverte=" + lieu_decouverte + ", type=" + type + ", autretypeobj_perdu=" + autretypeobj_perdu + ", localisation=" + localisation + ", autrelocalisation=" + autrelocalisation + ", etage=" + etage + ", matricule=" + matricule + ", salle=" + salle + ", photo=" + photo + ", photo2=" + photo2 + '}';
    }
   
}
