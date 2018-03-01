/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

import MODEL.ReservationSalle;
import MODEL.Salle;
import java.time.LocalDateTime;

/**
 *
 * @author nadaghanem
 */
public interface ISalleService extends Iservice<Salle, Integer> {

    public boolean addReservation(ReservationSalle rvSalle);

    public boolean salleDisponible(ReservationSalle rvSalle);
}
