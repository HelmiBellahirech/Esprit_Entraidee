package MODEL;

import java.sql.Date;









/**
 *
 * @author LENOVO
 */
public class Colocation {
    private int id;
    private int id_user;
    private int nbChambre;
    private int nbPersonne;
    private String type_log;
    private String adresse;
    private String etage;
    private Date date_dispo  ;
    private String meuble  ;
    private float prix;
    private String titre  ;
    private String photo  ;

    public Colocation(int id, int id_user, int nbChambre, int nbPersonne, String type_log, String adresse, String etage, Date date_dispo, String meuble, float prix , String titre,String photo) {
        this.id = id;
        this.id_user = id_user;
        this.nbChambre = nbChambre;
        this.nbPersonne = nbPersonne;
        this.type_log = type_log;
        this.adresse = adresse;
        this.etage = etage;
        this.date_dispo = date_dispo;
        this.meuble = meuble;
        this.prix = prix;
        this.titre = titre;
        this.photo = photo;
    }
    public Colocation( int nbChambre, int nbPersonne, String type_log, String adresse, String etage,Date date_dispo, String meuble, float prix , String titre,String photo) {
        
        this.nbChambre = nbChambre;
        this.nbPersonne = nbPersonne;
        this.type_log = type_log;
        this.adresse = adresse;
        this.etage = etage;
        this.date_dispo = date_dispo;
        this.meuble = meuble;
        this.prix = prix;
         this.titre = titre;
        this.photo = photo;
    }
     public Colocation( int nbChambre, int nbPersonne, String type_log, String adresse, String etage,Date date_dispo, String meuble, float prix , String titre) {
        
        this.nbChambre = nbChambre;
        this.nbPersonne = nbPersonne;
        this.type_log = type_log;
        this.adresse = adresse;
        this.etage = etage;
        this.date_dispo = date_dispo;
        this.meuble = meuble;
        this.prix = prix;
         this.titre = titre;
        
    }
      public Colocation( int nbChambre, int nbPersonne, String type_log) {
        
        this.nbChambre = nbChambre;
        this.nbPersonne = nbPersonne;
        this.type_log = type_log;
       
    }

      public Colocation(int id, int nbChambre, int nbPersonne, String type_log, String adresse, String etage,Date date_dispo, String meuble, float prix , String titre,String photo) {
         this.id = id;
        this.nbChambre = nbChambre;
        this.nbPersonne = nbPersonne;
        this.type_log = type_log;
        this.adresse = adresse;
        this.etage = etage;
        this.date_dispo = date_dispo;
        this.meuble = meuble;
        this.prix = prix;
         this.titre = titre;
        this.photo = photo;
    }
      

    public Colocation(int id) {
         this.id = id;
       
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

    public int getNbChambre() {
        return nbChambre;
    }

    public void setNbChambre(int nbChambre) {
        this.nbChambre = nbChambre;
    }

    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public String getType_log() {
        return type_log;
    }

    public void setType_log(String type_log) {
        this.type_log = type_log;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public Date getDate_dispo() {
        return date_dispo;
    }

    public void setDate_dispo(Date date_dispo) {
        this.date_dispo = date_dispo;
    }

    public String getMeuble() {
        return meuble;
    }

    public void setMeuble(String meuble) {
        this.meuble = meuble;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Colocation{" + "id=" + id + ", id_user=" + id_user + ", nbChambre=" + nbChambre + ", nbPersonne=" + nbPersonne + ", type_log=" + type_log + ", adresse=" + adresse + ", etage=" + etage + ", date_dispo=" + date_dispo + ", meuble=" + meuble + ", prix=" + prix + ", titre=" + titre + ", photo=" + photo + '}';
    }

    

    
   
    
    
}
