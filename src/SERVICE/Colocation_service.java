package SERVICE;
import DataSource.DataSource;
import ISERVICE.Icolocationservice;
import MODEL.Colocation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author LENOVO
 */
public class Colocation_service implements Icolocationservice{
  Connection connection ; 
   ImageView imgView = new ImageView();//setting imageview where we will set our uploaded picture before taking it to the database
   

   public Colocation_service() {
        connection = DataSource.getInstance().getConnection();
    }
    @Override
    public boolean add(Colocation t) {
         String req = "insert into colocation (id_user,nbChambre,nbPersonne,type_log,adresse,etage,date_dispo,meuble,prix,titre,photo) values (?,?,?,?,?,?,?,?,?,?,?)";
              

        PreparedStatement preparedStatement;
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
          try {
            preparedStatement = connection.prepareStatement(req);
            
         
           // 
           
            preparedStatement.setInt(1, esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID());
            preparedStatement.setInt(2, t.getNbChambre());
            preparedStatement.setInt(3, t.getNbPersonne());
            preparedStatement.setString(4, t.getType_log());
            preparedStatement.setString(5, t.getAdresse());
            preparedStatement.setString(6, t.getEtage());
            preparedStatement.setDate(7, t.getDate_dispo());
            preparedStatement.setString(8, t.getMeuble());
            preparedStatement.setFloat(9, t.getPrix());
            preparedStatement.setString(10, t.getTitre());
            preparedStatement.setString(11, t.getPhoto());
           
            // preparedStatement.setInt(9, t.getID_USER());
        
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Colocation t) {
           String req = "update colocation set  nbChambre=?,nbPersonne=?,type_log=?,adresse=?,etage=?,date_dispo=?,meuble=?,prix=?,titre=?,photo=? where id = ?";
        PreparedStatement preparedStatement;
        java.util.Date date_util = new java.util.Date();
//Tu fais tes traitement sur date_util...

//Tu castes Ãƒ  la fin pour insÃƒÂ©rer en base.
       java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            preparedStatement = connection.prepareStatement(req);
            
                    
          
            preparedStatement.setInt(1, t.getNbChambre());
            preparedStatement.setInt(2, t.getNbPersonne());
            preparedStatement.setString(3, t.getType_log());
            preparedStatement.setString(4, t.getAdresse());
            preparedStatement.setString(5, t.getEtage());
            preparedStatement.setDate(6, t.getDate_dispo());
            preparedStatement.setString(7, t.getMeuble());
            preparedStatement.setFloat(8, t.getPrix());
            preparedStatement.setString(9, t.getTitre());
            preparedStatement.setString(10, t.getPhoto());
            preparedStatement.setInt(11, t.getId());
            //preparedStatement.setInt(9, t.getID_USER());
         
             return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

  
    @Override
    public boolean remove(Integer r) {
         String req = "delete from colocation where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            
             return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

   @Override
    public Colocation findId(Integer r) {
         Colocation colocation = null;
        String req = "select * from colocation where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                colocation=new Colocation(resultSet.getInt("id"),resultSet.getInt("id_user"), resultSet.getInt("nbChambre"), resultSet.getInt("nbPersonne"), resultSet.getString("type_log"), resultSet.getString("adresse"), resultSet.getString("etage"), resultSet.getDate("date_dispo"), resultSet.getString("meuble"), resultSet.getFloat("prix"), resultSet.getString("titre"), resultSet.getString("photo"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     
        return colocation ;
    }

    @Override
    public List<Colocation> getAll() {
         Colocation colocation = null;
        List<Colocation> colocations = new ArrayList<>();
        String req = "select * from colocation";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                colocation = new Colocation(resultSet.getInt("id"),resultSet.getInt("id_user"), resultSet.getInt("nbChambre"), resultSet.getInt("nbPersonne"), resultSet.getString("type_log"), resultSet.getString("adresse"), resultSet.getString("etage"), resultSet.getDate("date_dispo"), resultSet.getString("meuble"), resultSet.getFloat("prix"), resultSet.getString("titre"), resultSet.getString("photo"));
                colocations.add(colocation);
                 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
        return colocations;
    }
     @Override
    public List<Colocation> findByAdresse(String Adresse) {
        Colocation colocation = null;
        List<Colocation> colocations = new ArrayList<>();
        String req = "select * from colocation where adresse =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1,Adresse);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                colocation = new Colocation(resultSet.getInt("id"),resultSet.getInt("id_user"), resultSet.getInt("nbChambre"), resultSet.getInt("nbPersonne"), resultSet.getString("type_log"), resultSet.getString("adresse"), resultSet.getString("etage"), resultSet.getDate("date_dispo"), resultSet.getString("meuble"), resultSet.getFloat("prix"), resultSet.getString("titre"), resultSet.getString("photo"));
                colocations.add(colocation);
                     
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return colocations;
    }
       @Override
    public List<Colocation> findId_user(Integer r) {
        Colocation colocation = null;
         List<Colocation> colocations = new ArrayList<>();
        String req = "select * from colocation where ID_USER =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                colocation = new Colocation(resultSet.getInt("id"),resultSet.getInt("id_user"), resultSet.getInt("nbChambre"), resultSet.getInt("nbPersonne"), resultSet.getString("type_log"), resultSet.getString("adresse"), resultSet.getString("etage"), resultSet.getDate("date_dispo"), resultSet.getString("meuble"), resultSet.getFloat("prix"), resultSet.getString("titre"), resultSet.getString("photo"));
                colocations.add(colocation);
                 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return colocations;
    }
}

