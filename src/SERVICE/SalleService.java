/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import DataSource.DataSource;
import ISERVICE.ISalleService;
import MODEL.ReservationSalle;
import MODEL.Salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nadaghanem
 */
public class SalleService implements ISalleService {

    Connection connection;

    public SalleService() {
        connection = DataSource.getInstance().getConnection();
    }

    public boolean addReservation(ReservationSalle rvSalle) {
        String req = "INSERT INTO reservation_salle (salle_id, user_id, datetime_start, datetime_end, nb_personnes) VALUES (?,?,?,?,?)";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, rvSalle.getSalle().getId());
            preparedStatement.setInt(2, rvSalle.getUser().getID());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(rvSalle.getDateTime1()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(rvSalle.getDateTime2()));
            preparedStatement.setInt(5, rvSalle.getNbPersonnes());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(Salle s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean remove(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Salle findId(Integer r) {
        Salle salle = null;
        String req = "SELECT * from salle WHERE ID=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            salle = new Salle(resultSet.getInt("id"), resultSet.getInt("num_salle"), resultSet.getInt("num_bloc"), 1, 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return salle;
    }

    public List<Salle> getAll() {
        Salle salle;
        List<Salle> salleList = new ArrayList<>();
        String req = "SELECT * FROM salle";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                salle = new Salle(resultSet.getInt("num_salle"), resultSet.getInt("num_bloc"), resultSet.getInt("nbr_chaise"), resultSet.getInt("nbr_table"));
                salleList.add(salle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return salleList;
    }

    @Override
    public boolean salleDisponible(ReservationSalle rvSalle) {
        String query = "SELECT COUNT(*) as count FROM reservation_salle WHERE salle_id = ? AND (datetime_start >= ? AND datetime_start <= ?)"
                + " OR (datetime_end >= ? AND datetime_end <= ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rvSalle.getSalle().getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(rvSalle.getDateTime1()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(rvSalle.getDateTime2()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(rvSalle.getDateTime1()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(rvSalle.getDateTime2()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count") == 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean add(Salle t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
