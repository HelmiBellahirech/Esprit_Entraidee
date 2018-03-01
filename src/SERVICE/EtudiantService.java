/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import DataSource.DataSource;
import ISERVICE.IEtudiantService;
import MODEL.Etudiant;
import MODEL.Utilisateur;
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
public class EtudiantService implements IEtudiantService {

    Connection connection;
 private PreparedStatement ps;
    public EtudiantService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean add(Etudiant t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Etudiant e) {
        String req = "update utilisateur set  Email=?,Username=?,Password=?,Nom=?,Prenom=?,Telephone=?,Photo=?,Sexe=?,Role=?,Classe=?,ID_CLUB=? where ID = ?";
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
                preparedStatement.setString(9, "Etudiant");
                preparedStatement.setString(10, e.getClasse());
                preparedStatement.setInt(11, e.getID_CLUB());
               
                preparedStatement.setInt(12, e.getID());
            //preparedStatement.setInt(9, t.getID_USER());
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
    public Etudiant findId(Integer r) {
       String req = "select * from utilisateur where id =?";
        Etudiant u = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new Etudiant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),rs.getString(12),rs.getInt(13));
                u.setRole(rs.getString("Role"));
                u.setID(rs.getInt("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public List<Etudiant> getAll() {
        Etudiant etudiant;
        List<Etudiant> etudiantList = new ArrayList<>();
        String req = "SELECT * FROM utilisateur where ROLE='Etudiant'";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                etudiant = new Etudiant(resultSet.getInt("ID"),
                        resultSet.getString("Email"), resultSet.getString("Username"),
                        resultSet.getString("Password"), resultSet.getString("Nom"), resultSet.getString("Prenom"),
                        resultSet.getString("Telephone"), resultSet.getString("Photo"), resultSet.getString("Sexe"),
                        resultSet.getDate("Date_Creation"), resultSet.getString("Classe"),
                        resultSet.getString("Matricule"), resultSet.getString("Role"),
                        resultSet.getInt("ID_CLUB")
                );
                etudiantList.add(etudiant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etudiantList;
    }
}
