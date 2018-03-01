/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SERVICE;

import DataSource.DataSource;
import ISERVICE.IProfService;
import MODEL.Cours;
import MODEL.Professeur;
import MODEL.Professeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nadaghanem
 */
public class ProfService implements IProfService {

    Connection connection;

    public ProfService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean add(Professeur t) {
         String req = "insert into Utilisateur (Email,Username,Password,Nom,Prenom,Telephone,Photo,Sexe,Date_Creation,Role,Specialite) values (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;

//Tu fais tes traitement sur date_util...
//Tu castes à la fin pour insérer en base.
            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
            try {
                preparedStatement = connection.prepareStatement(req);

                // 
                preparedStatement.setString(1, t.getEmail());
                preparedStatement.setString(2, t.getUsername());
                preparedStatement.setString(3, t.getPassword());
                preparedStatement.setString(4, t.getNom());
                preparedStatement.setString(5, t.getPrenom());
                preparedStatement.setString(6, t.getTelephone());
                preparedStatement.setString(7, t.getPhoto());
                preparedStatement.setString(8, t.getSexe());
                preparedStatement.setDate(9, date_sql);
                preparedStatement.setString(10, "Professeur");
                preparedStatement.setString(11, t.getSpecialite());

                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        return false;
    }


    @Override
    public boolean update(Professeur e) {
       
        String req = "update utilisateur set  Email=?,Username=?,Password=?,Nom=?,Prenom=?,Telephone=?,Photo=?,Sexe=?,Role=?,Specialite=? where ID = ?";
            PreparedStatement preparedStatement;
            java.util.Date date_util = new java.util.Date();
//Tu fais tes traitement sur date_util...

//Tu castes à la fin pour insérer en base.
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
            try {
                preparedStatement = connection.prepareStatement(req);

                preparedStatement.setString(1, e.getEmail());
                preparedStatement.setString(2, e.getUsername());
                preparedStatement.setString(3, e.getPassword());
                preparedStatement.setString(4, e.getNom());
                preparedStatement.setString(5, e.getPrenom());
                preparedStatement.setString(6, e.getTelephone());
                preparedStatement.setString(7, e.getPhoto());
                preparedStatement.setString(8, e.getSexe());
                preparedStatement.setString(10, "Professeur");
                preparedStatement.setString(11, e.getSpecialite());
               
                preparedStatement.setInt(12, e.getID());

                return preparedStatement.executeUpdate() > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
    }

    @Override
    public boolean remove(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professeur findId(Integer r) {
        Professeur prof = null;
        String req = "SELECT * from Utilisateur WHERE ID=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            prof = new Professeur(resultSet.getInt("ID"), resultSet.getString("Email"),
                    resultSet.getString("username"), resultSet.getString("Password"), resultSet.getString("Nom"),
                    resultSet.getString("Prenom"), resultSet.getString("Telephone"), resultSet.getString("photo"),
                    resultSet.getString("Sexe"), resultSet.getString("Specialite"), resultSet.getString("Matricule"),
                    resultSet.getString("Role"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prof;
    }

    @Override
    public List<Professeur> getAll() {
          Professeur professeur;
        List<Professeur> professeurList = new ArrayList<>();
        String req = "SELECT * FROM utilisateur where ROLE='Professeur'";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                professeur = new Professeur(resultSet.getInt("ID"),
                        resultSet.getString("Email"), resultSet.getString("Username"),
                        resultSet.getString("Password"), resultSet.getString("Nom"), resultSet.getString("Prenom"),
                        resultSet.getString("Telephone"), resultSet.getString("Photo"), resultSet.getString("Sexe"),
                        resultSet.getDate("Date_Creation"), resultSet.getString("Specialite"),
                        resultSet.getString("Matricule"), resultSet.getString("Role")
                        
                );
                professeurList.add(professeur);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return professeurList;
    }
    }


