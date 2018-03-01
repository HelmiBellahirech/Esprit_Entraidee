package SERVICE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DataSource.DataSource;
import ISERVICE.IcovoiturageService;
import MODEL.Covoiturage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OrbitG
 */
public class Covoiturage_service implements IcovoiturageService {

    Connection connection;

    public Covoiturage_service() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean add(Covoiturage t) {
        String req = "insert into covoiturage (depart,arrive,prix,date_sys,date,heure,nbrPlaces,comfort,fumeur,id_user) values (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement;

//Tu fais tes traitement sur date_util...
//Tu castes à la fin pour insérer en base.
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            preparedStatement = connection.prepareStatement(req);

            // 
            preparedStatement.setString(1, t.getDepart());
            preparedStatement.setString(2, t.getArrive());
            preparedStatement.setFloat(3, t.getPrix());
            preparedStatement.setDate(4, date_sql);
            preparedStatement.setDate(5, t.getDate());
            preparedStatement.setString(6, t.getHeure());
            preparedStatement.setInt(7, t.getNbrPlaces());
            preparedStatement.setString(8, t.getComfort());
            preparedStatement.setString(9, t.getFumeur());
            preparedStatement.setInt(10, esprit_entraide.Esprit_Entraide.getInstance().getLoggedUser().getID());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Covoiturage t) {

        String req = "update covoiturage set  depart=?,arrive=?,prix=?,date=?,heure=?,nbrPlaces=?,comfort=?,fumeur=? where ID = ?";
        PreparedStatement preparedStatement;
        java.util.Date date_util = new java.util.Date();
//Tu fais tes traitement sur date_util...

//Tu castes à la fin pour insérer en base.
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            preparedStatement = connection.prepareStatement(req);

            preparedStatement.setString(1, t.getDepart());
            preparedStatement.setString(2, t.getArrive());
            preparedStatement.setFloat(3, t.getPrix());
            // preparedStatement.setDate(4, date_sql);
            preparedStatement.setDate(4, t.getDate());
            preparedStatement.setString(5, t.getHeure());
            preparedStatement.setInt(6, t.getNbrPlaces());
            preparedStatement.setString(7, t.getComfort());
            preparedStatement.setString(8, t.getFumeur());
            preparedStatement.setInt(9, t.getID());
            //preparedStatement.setInt(9, t.getID_USER());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Integer r) {
        String req = "delete from covoiturage where id=?";
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
    public Covoiturage findId(Integer r) {
        Covoiturage covoiturage = null;
        String req = "select * from covoiturage where ID =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                covoiturage = new Covoiturage(resultSet.getInt("ID"), resultSet.getString("depart"), resultSet.getString("arrive"), resultSet.getFloat("prix"), resultSet.getDate("date"), resultSet.getDate("date_sys"), resultSet.getString("heure"), resultSet.getInt("nbrPlaces"), resultSet.getString("comfort"), resultSet.getString("fumeur"), resultSet.getInt("ID_USER"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return covoiturage;
    }

    @Override
    public List<Covoiturage> getAll() {
        Covoiturage covoiturage = null;
        List<Covoiturage> covoiturages = new ArrayList<>();
        String req = "select * from covoiturage";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                covoiturage = new Covoiturage(resultSet.getInt("ID"), resultSet.getString("depart"), resultSet.getString("arrive"), resultSet.getFloat("prix"), resultSet.getDate("date"), resultSet.getDate("date_sys"), resultSet.getString("heure"), resultSet.getInt("nbrPlaces"), resultSet.getString("comfort"), resultSet.getString("fumeur"), resultSet.getInt("ID_USER"));
                covoiturages.add(covoiturage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return covoiturages;
    }

    @Override
    public List<Covoiturage> getAllSaufUser(Integer ID) {
        Covoiturage covoiturage = null;
        List<Covoiturage> covoiturages = new ArrayList<>();
        String req = "select * from covoiturage where ID_USER!=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                covoiturage = new Covoiturage(resultSet.getInt("ID"), resultSet.getString("depart"), resultSet.getString("arrive"), resultSet.getFloat("prix"), resultSet.getDate("date"), resultSet.getDate("date_sys"), resultSet.getString("heure"), resultSet.getInt("nbrPlaces"), resultSet.getString("comfort"), resultSet.getString("fumeur"), resultSet.getInt("ID_USER"));
                covoiturages.add(covoiturage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return covoiturages;
    }

    @Override
    public List<Covoiturage> findByDepartArrive(String Depart, String Arrive, Integer ID) {
        Covoiturage covoiturage = null;
        List<Covoiturage> covoiturages = new ArrayList<>();
        String req = "select * from covoiturage where Depart =? AND Arrive=? AND ID_USER!=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, Depart);
            preparedStatement.setString(2, Arrive);
            preparedStatement.setInt(3, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                covoiturage = new Covoiturage(resultSet.getInt("ID"), resultSet.getString("depart"), resultSet.getString("arrive"), resultSet.getFloat("prix"), resultSet.getDate("date"), resultSet.getDate("date_sys"), resultSet.getString("heure"), resultSet.getInt("nbrPlaces"), resultSet.getString("comfort"), resultSet.getString("fumeur"), resultSet.getInt("ID_USER"));
                covoiturages.add(covoiturage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return covoiturages;
    }

    @Override
    public List<Covoiturage> findId_user(Integer r) {
        Covoiturage covoiturage = null;
        List<Covoiturage> covoiturages = new ArrayList<>();
        String req = "select * from covoiturage where ID_USER =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                covoiturage = new Covoiturage(resultSet.getInt("ID"), resultSet.getString("depart"), resultSet.getString("arrive"), resultSet.getFloat("prix"), resultSet.getDate("date"), resultSet.getDate("date_sys"), resultSet.getString("heure"), resultSet.getInt("nbrPlaces"), resultSet.getString("comfort"), resultSet.getString("fumeur"), resultSet.getInt("ID_USER"));
                covoiturages.add(covoiturage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return covoiturages;
    }

    @Override
    public List<Covoiturage> findByArrive(String Arrive, Integer ID) {
        Covoiturage covoiturage = null;
        List<Covoiturage> covoiturages = new ArrayList<>();
        String req = "select * from covoiturage where arrive=? AND ID_USER!=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

            preparedStatement.setString(1, Arrive);
            preparedStatement.setInt(2, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                covoiturage = new Covoiturage(resultSet.getInt("ID"), resultSet.getString("depart"), resultSet.getString("arrive"), resultSet.getFloat("prix"), resultSet.getDate("date"), resultSet.getDate("date_sys"), resultSet.getString("heure"), resultSet.getInt("nbrPlaces"), resultSet.getString("comfort"), resultSet.getString("fumeur"), resultSet.getInt("ID_USER"));
                covoiturages.add(covoiturage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return covoiturages;
    }

    @Override
    public List<Covoiturage> findByDepart(String Depart, Integer ID) {
        Covoiturage covoiturage = null;
        List<Covoiturage> covoiturages = new ArrayList<>();
        String req = "select * from covoiturage where depart =? AND ID_USER!=? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, Depart);
            preparedStatement.setInt(2, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                covoiturage = new Covoiturage(resultSet.getInt("ID"), resultSet.getString("depart"), resultSet.getString("arrive"), resultSet.getFloat("prix"), resultSet.getDate("date"), resultSet.getDate("date_sys"), resultSet.getString("heure"), resultSet.getInt("nbrPlaces"), resultSet.getString("comfort"), resultSet.getString("fumeur"), resultSet.getInt("ID_USER"));
                covoiturages.add(covoiturage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return covoiturages;
    }

    public void miseajour() {
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        String req = "delete from covoiturage where date<?";
        //String req1 = "delete from covoiturage where nbrPlaces=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(1, date_sql);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /*   try {
            preparedStatement = connection.prepareStatement(req1);
            preparedStatement.setInt(1,0 );
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
    }
}
