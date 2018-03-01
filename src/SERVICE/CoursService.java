package SERVICE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DataSource.DataSource;
import ISERVICE.ICoursService;
import MODEL.Cours;
import MODEL.Professeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OrbitG
 */
public class CoursService implements ICoursService {
    
    Connection connection;
    
    public CoursService() {
        connection = DataSource.getInstance().getConnection();
    }
    
    @Override
    public boolean add(Cours t) {
        String req = "insert into cours (id_prof, module, matiere, date_pub, fichier) values (?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, t.getProf().getID());
            preparedStatement.setString(2, t.getModule());
            preparedStatement.setString(3, t.getMatiere());
            preparedStatement.setDate(4, new java.sql.Date(t.getDate_pub().getTime()));
            preparedStatement.setBlob(5, t.getFichier());
            boolean isInserted = preparedStatement.executeUpdate() > 0;
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                System.out.println(last_inserted_id);
                t.setId(last_inserted_id);
            }
            return isInserted;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean update(Cours t) {
        String req = "UPDATE cours SET  module=?, matiere=?, date_pub=?, fichier=? WHERE ID = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, t.getModule());
            preparedStatement.setString(2, t.getMatiere());
            preparedStatement.setDate(3, new java.sql.Date(t.getDate_pub().getTime()));
            preparedStatement.setBlob(4, t.getFichier());
            preparedStatement.setInt(5, t.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean remove(Integer r) {
        String req = "DELETE FROM cours WHERE ID=?";
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
    public Cours findId(Integer r) {
        Cours cours = null;
        Professeur prof;
        String req = "SELECT * FROM cours c,profs p WHERE c.id = ? AND c.id_prof = p.id";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                prof = new Professeur("", resultSet.getInt("ID"), resultSet.getString("Email"),
                        resultSet.getString("username"), resultSet.getString("Password"), resultSet.getString("Nom"),
                        resultSet.getString("Prenom"), resultSet.getString("Telephone"), resultSet.getString("Photo"),
                        resultSet.getString("Sexe"), resultSet.getString("Matricule"), resultSet.getString("Role"));
                cours = new Cours(resultSet.getInt("id"), prof, resultSet.getString("module"), resultSet.getString("matiere"), resultSet.getDate("date_pub"), resultSet.getBlob("fichier"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cours;
    }

    //we should use this in the controller to retrieve all data about revision
    @Override
    public List<Cours> getAll() {
        Cours cours;
        Professeur prof;
        List<Cours> coursList = new ArrayList<>();
        String req = "SELECT * FROM cours c, Utilisateur u WHERE c.id_prof = u.id";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                prof = new Professeur( resultSet.getInt("ID"), resultSet.getString("Email"),
                        resultSet.getString("username"), resultSet.getString("Password"), resultSet.getString("Nom"),
                        resultSet.getString("Prenom"), resultSet.getString("Telephone"), resultSet.getString("Photo"),
                        resultSet.getString("Sexe"), resultSet.getString("Matricule"), resultSet.getString("Role"),"");
                cours = new Cours(resultSet.getInt("id"), prof, resultSet.getString("module"), resultSet.getString("matiere"), resultSet.getDate("date_pub"), resultSet.getBlob("fichier"));
                coursList.add(cours);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coursList;
    }
}
