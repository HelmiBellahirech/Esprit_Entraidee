/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import DataSource.DataSource;
import ISERVICE.IreclamationService;
import MODEL.Reclamation;
import MODEL.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class ReclamationService implements IreclamationService{
    Connection connection ; 

    public ReclamationService() {
        connection= DataSource.getInstance().getConnection(); 
    }
     @Override
   public boolean add(Reclamation t) {
       String req = "insert into reclamation (description,date_decouverte,lieu_decouverte,typeobj_perdu,type,autretypeobj_perdu,localisation,autrelocalisation,etage,salle,photo,id_user) values (?,?,?,?,?,?,?,?,?,?,?,?)";
              

        PreparedStatement preparedStatement;

//Tu fais tes traitement sur date_util...
//Tu castes à la fin pour insérer en base.
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            System.out.println("je suis laaa"+req);
            preparedStatement = connection.prepareStatement(req);
            
         
           
            
            preparedStatement.setString(1, t.getDescription());
            preparedStatement.setDate(2, t.getDate_decouverte());
            preparedStatement.setString(3, t.getLieu_decouverte());
            preparedStatement.setString(4, t.getTypeobj_perdu());
            preparedStatement.setString(5, t.getType());
            preparedStatement.setString(6, t.getAutretypeobj_perdu());
            preparedStatement.setString(7, t.getLocalisation());
            preparedStatement.setString(8, t.getAutrelocalisation());
            preparedStatement.setString(9, t.getEtage());
            preparedStatement.setString(10, t.getSalle());
            preparedStatement.setString(11, t.getPhoto());
            preparedStatement.setInt(12, esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID());
            // preparedStatement.setInt(9, t.getID_USER());
         return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; 
    }
   @Override
   public void addMal(Reclamation t) {
       String req = "insert into reclamation (type,matricule,photo2,id_user) values (?,?,?,?)";
              

        PreparedStatement preparedStatement;

//Tu fais tes traitement sur date_util...
//Tu castes à la fin pour insérer en base.
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            System.out.println("je suis laaa"+req);
            preparedStatement = connection.prepareStatement(req);

            preparedStatement.setString(1, t.getType());
            preparedStatement.setString(2, t.getMatricule());
            preparedStatement.setString(3, t.getPhoto2());
            preparedStatement.setInt(4, esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID());
            // preparedStatement.setInt(9, t.getID_USER());
          preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}
    @Override
     public boolean update(Reclamation t) {
        String req = "update reclamation set  description=?,date_decouverte=?,lieu_decouverte=?,typeobj_perdu=?,type=?,autretypeobj_perdu=?,localisation=?,autrelocalisation=?,etage=?,salle=?,photo=? where id =?";
        PreparedStatement preparedStatement;
        try {
            System.out.println("je suis laaa"+req);
            preparedStatement = connection.prepareStatement(req);
            
            preparedStatement.setString(1, t.getDescription());
            preparedStatement.setDate(2, t.getDate_decouverte());
            preparedStatement.setString(3,t.getLieu_decouverte()); 
            preparedStatement.setString(4, t.getTypeobj_perdu());
            preparedStatement.setString(5, t.getType());
            preparedStatement.setString(6, t.getAutretypeobj_perdu());
            preparedStatement.setString(7, t.getLocalisation());
            preparedStatement.setString(8, t.getAutrelocalisation());
            preparedStatement.setString(9, t.getEtage());
            //preparedStatement.setString(10, t.getMatricule());
            preparedStatement.setString(10, t.getSalle());
            preparedStatement.setString(11, t.getPhoto());
            //preparedStatement.setString(13, t.getPhoto2());
            preparedStatement.setInt(12, t.getId());
            System.out.println(t.getDescription()+t.getLieu_decouverte()+t.getLieu_decouverte()+t.getTypeobj_perdu()+t.getType()+t.getAutretypeobj_perdu()+t.getLocalisation()+t.getAutrelocalisation()+t.getEtage()+t.getSalle()+t.getPhoto()+t.getId());
            System.out.println(t.getId());
          return preparedStatement.executeUpdate()>0 ;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; 
    }
     
      @Override
     public void updateMal(Reclamation t) {
        String req = "update reclamation set type=?,matricule=?,photo2=? where id =?";
        PreparedStatement preparedStatement;
        try {
            System.out.println("je suis laaa"+req);
            preparedStatement = connection.prepareStatement(req);
            

            preparedStatement.setString(1, t.getType());
            System.out.println(t.getType());
            preparedStatement.setString(2, t.getMatricule());
            System.out.println(t.getMatricule());
            preparedStatement.setString(3, t.getPhoto2());
            System.out.println(t.getPhoto2());
            preparedStatement.setInt(4, t.getId());
            System.out.println(t.getId());
          preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       @Override
       public boolean remove(Integer r) {
         String req = "delete from reclamation where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();
            return true ; 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false ; 
    } 
         @Override
    public List<Reclamation> getAll() {
        Reclamation reclamation = null;
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from reclamation";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reclamation=new Reclamation(resultSet.getInt("id"),resultSet.getString("description"),resultSet.getDate("date_decouverte"),resultSet.getString("lieu_decouverte"),resultSet.getString("typeobj_perdu"),resultSet.getString("type"),resultSet.getString("autretypeobj_perdu"),resultSet.getString("localisation"),resultSet.getString("autrelocalisation"),resultSet.getString("etage"),resultSet.getString("matricule"),resultSet.getString("salle"),resultSet.getString("photo"),resultSet.getString("photo2"));
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reclamations;
    }
    
    @Override
    public List<Reclamation> findId_user(Integer r) {
        Reclamation reclamation = null;
         List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from reclamation where id_user =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reclamation=new Reclamation(resultSet.getInt("id"),resultSet.getString("description"),resultSet.getDate("date_decouverte"),resultSet.getString("lieu_decouverte"),resultSet.getString("typeobj_perdu"),resultSet.getString("type"),resultSet.getString("autretypeobj_perdu"),resultSet.getString("localisation"),resultSet.getString("autrelocalisation"),resultSet.getString("etage"),resultSet.getString("matricule"),resultSet.getString("salle"),resultSet.getString("photo"),resultSet.getString("photo2"),resultSet.getInt("id_user"));
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reclamations;
    }

    @Override
    public Reclamation findId(Integer r) {
        Reclamation reclamation = null;
        String req = "select * from reclamation where ID =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reclamation = new Reclamation(resultSet.getInt("id"),resultSet.getString("description"),resultSet.getDate("date_decouverte"),resultSet.getString("lieu_decouverte"),resultSet.getString("typeobj_perdu"),resultSet.getString("type"),resultSet.getString("autretypeobj_perdu"),resultSet.getString("localisation"),resultSet.getString("autrelocalisation"),resultSet.getString("etage"),resultSet.getString("matricule"),resultSet.getString("salle"),resultSet.getString("photo"),resultSet.getString("photo2"),resultSet.getInt("id_user"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reclamation;
    }
     @Override
    public Utilisateur findUserByMatricule(String Matricule) {
        Utilisateur utilisateur = null;
        String req = "select * from utilisateur where matricule =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, Matricule);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                utilisateur = new Utilisateur();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return utilisateur;
    }

    public List<Reclamation> rechercher(String x) {
    Reclamation reclamation = null;
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from reclamation where (`description`LIKE '%"+x+"%' );";
        System.out.println(req);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reclamation=new Reclamation(resultSet.getInt("id"),resultSet.getString("description"),resultSet.getDate("date_decouverte"),resultSet.getString("lieu_decouverte"),resultSet.getString("typeobj_perdu"),resultSet.getString("type"),resultSet.getString("autretypeobj_perdu"),resultSet.getString("localisation"),resultSet.getString("autrelocalisation"),resultSet.getString("etage"),resultSet.getString("matricule"),resultSet.getString("salle"),resultSet.getString("photo"),resultSet.getString("photo2"));
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reclamations;}
     public List<Reclamation> findByType(String x) {
        Reclamation reclamation = null;
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from reclamation where type=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1,x);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reclamation = new Reclamation(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getDate("date_decouverte"), resultSet.getString("lieu_decouverte"), resultSet.getString("typeobj_perdu"), resultSet.getString("type"), resultSet.getString("autretypeobj_perdu"), resultSet.getString("localisation"), resultSet.getString("autrelocalisation"), resultSet.getString("etage"), resultSet.getString("matricule"), resultSet.getString("salle"), resultSet.getString("photo"), resultSet.getString("photo2"));
                reclamations.add(reclamation);
                System.out.println("makni");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reclamations;
    }
}

