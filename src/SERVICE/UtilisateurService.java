/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import DataSource.DataSource;
import ISERVICE.IutilisateurService;
import MODEL.Etudiant;
import MODEL.Professeur;
import MODEL.Responsable;
import MODEL.Utilisateur;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OrbitG
 */
public class UtilisateurService implements IutilisateurService {

    private Connection connection;
    private PreparedStatement ps;
    private PreparedStatement ps1;

    public UtilisateurService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean add(Utilisateur t) {
        if (t instanceof Etudiant) {
            Etudiant e = (Etudiant) t;
            String req = "insert into Utilisateur (Email,Username,Password,Nom,Prenom,Telephone,Photo,Sexe,Date_Creation,Role,Classe) values (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;

//Tu fais tes traitement sur date_util...
//Tu castes à la fin pour insérer en base.
            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
            try {
                preparedStatement = connection.prepareStatement(req);

                // 
                preparedStatement.setString(1, e.getEmail());
                preparedStatement.setString(2, e.getUsername());
                preparedStatement.setString(3, e.getPassword());
                preparedStatement.setString(4, e.getNom());
                preparedStatement.setString(5, e.getPrenom());
                preparedStatement.setString(6, e.getTelephone());
                preparedStatement.setString(7, e.getPhoto());
                preparedStatement.setString(8, e.getSexe());
                preparedStatement.setDate(9, date_sql);
                preparedStatement.setString(10, "Etudiant");
                preparedStatement.setString(11, e.getClasse());

                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (t instanceof Professeur) {
            Professeur P = (Professeur) t;
            String req = "insert into Utilisateur (Email,Username,Password,Nom,Prenom,Telephone,Photo,Sexe,Date_Creation,Role,Specialite) values (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;

//Tu fais tes traitement sur date_util...
//Tu castes à la fin pour insérer en base.
            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
            try {
                preparedStatement = connection.prepareStatement(req);

                // 
                preparedStatement.setString(1, P.getEmail());
                preparedStatement.setString(2, P.getUsername());
                preparedStatement.setString(3, P.getPassword());
                preparedStatement.setString(4, P.getNom());
                preparedStatement.setString(5, P.getPrenom());
                preparedStatement.setString(6, P.getTelephone());
                preparedStatement.setString(7, P.getPhoto());
                preparedStatement.setString(8, P.getSexe());
                preparedStatement.setDate(9, date_sql);
                preparedStatement.setString(10, "Professeur");
                preparedStatement.setString(11, P.getSpecialite());

                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (t instanceof Responsable) {
            Responsable r = (Responsable) t;
            String req = "insert into Utilisateur (Email,Username,Password,Nom,Prenom,Telephone,Photo,Sexe,Date_Creation,Role,Nom_Club,Photo_Club) values (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;

//Tu fais tes traitement sur date_util...
//Tu castes à la fin pour insérer en base.
            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
            try {
                preparedStatement = connection.prepareStatement(req);

                // 
                preparedStatement.setString(1, r.getEmail());
                preparedStatement.setString(2, r.getUsername());
                preparedStatement.setString(3, r.getPassword());
                preparedStatement.setString(4, r.getNom());
                preparedStatement.setString(5, r.getPrenom());
                preparedStatement.setString(6, r.getTelephone());
                preparedStatement.setString(7, r.getPhoto());
                preparedStatement.setString(8, r.getSexe());
                preparedStatement.setDate(9, date_sql);
                preparedStatement.setString(10, "Responsable");
                preparedStatement.setString(11, r.getNom_Club());
                preparedStatement.setString(12, r.getPhoto_Club());

                return preparedStatement.executeUpdate() > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean update(Utilisateur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilisateur findId(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int login(String Email, String mdp) {
        String req = "select * from utilisateur where Email =? ";
        Utilisateur u = new Utilisateur();
        UtilisateurService userService = new UtilisateurService();
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, Email);

            ResultSet rs;
            rs = ps.executeQuery();
            if (rs.last())//utilisateur existe
            {
                if (rs.getString("Password").equals(mdp)) {
                    //u = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
                    u.setID(rs.getInt("ID"));
                    u.setEmail(rs.getString("Email"));
                    u.setUsername(rs.getString("Username"));
                    u.setPassword(rs.getString("password"));
                    u.setNom(rs.getString("Nom"));
                    u.setPrenom(rs.getString("Prenom"));
                    u.setTelephone(rs.getString("Telephone"));
                    u.setPhoto(rs.getString("Photo"));
                    u.setSexe(rs.getString("Sexe"));
                    u.setDate_Creation(rs.getDate("Date_Creation"));
                    u.setRole(rs.getString("Role"));

                    System.out.println(rs.getString(11));
                    if (userService.getById(rs.getInt(1)).getRole().equals("Admin")) {
                        esprit_entraide.Esprit_Entraide.getInstance().setLoggedAdmin(u);
                        return 3;//email et mdp vrais administrateur
                    } else if (userService.getById(rs.getInt(1)).getRole().equals("Etudiant") || userService.getById(rs.getInt(1)).getRole().equals("Professeur")) {
                        esprit_entraide.Esprit_Entraide.getInstance().setLoggedUser(u);
                        System.out.println(u);
                        return 0;//email et mdp vrais Etudiant
                    } else {
                        esprit_entraide.Esprit_Entraide.getInstance().setLoggedUser(u);
                        System.out.println(u);
                        return 4; //email et mdp vrais Responsable des club
                    }
                } else {
                    return 1;//mdp incorrect
                }
            } else {
                return 2;//utilisateur n'existe pas 
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return 3;

        }
    }

    @Override
    public Utilisateur getById(Integer id) {
        String req = "select * from utilisateur where id =?";
        Utilisateur u = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
                u.setRole(rs.getString("Role"));
                u.setID(rs.getInt("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public int verifAddUtilisateur(Utilisateur u) {
        int existe = 0;
        String req = "select * from utilisateur where email=?";
        String req1 = "select * from utilisateur where username=?";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, u.getEmail());
            if (ps.executeQuery().next()) {
                existe = 1; //emailexiste
            }
            ps = connection.prepareStatement(req1);
            ps.setString(1, u.getUsername());
            if (ps.executeQuery().next()) {
                existe = 2; //Usernameexiste
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public int addUser(Utilisateur u) {
        int existe = verifAddUtilisateur(u);
        //String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));

        if (existe == 0) {
            if (u instanceof Etudiant) {
                Etudiant e = (Etudiant) u;
                PreparedStatement preparedStatement;

                String req = "insert into Utilisateur (Email,Username,Password,Nom,Prenom,Telephone,Photo,Sexe,Date_Creation,Role,Classe,Matricule) values (?,?,?,?,?,?,?,?,?,?,?,?)";
                java.util.Date date_util = new java.util.Date();
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
                    preparedStatement.setDate(9, date_sql);
                    preparedStatement.setString(10, "Etudiant");
                    preparedStatement.setString(11, e.getClasse());
                    preparedStatement.setString(12, e.getMatricule());
                    preparedStatement.executeUpdate();
                    //esprit_entraide.Esprit_Entraide.getInstance().setLoggedUser(u);
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }

        }
        return existe;

    }
    
}
