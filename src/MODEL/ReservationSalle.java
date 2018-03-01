/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.time.LocalDateTime;

/**
 *
 * @author nadaghanem
 */
public class ReservationSalle {

    private int id;
    private Utilisateur user;
    private Salle salle;
    private LocalDateTime dateTime1;
    private LocalDateTime dateTime2;
    private int nbPersonnes;

    public ReservationSalle(int id, Salle salle, Utilisateur user, LocalDateTime dateTime1, LocalDateTime dateTime2, int nbPersonnes) {
        this(salle, user, dateTime1, dateTime2, nbPersonnes);
        this.id = id;
    }

    public ReservationSalle(Salle salle, Utilisateur user, LocalDateTime dateTime1, LocalDateTime dateTime2, int nbPersonnes) {
        this.salle = salle;
        this.user = user;
        this.dateTime1 = dateTime1;
        this.dateTime2 = dateTime2;
        this.nbPersonnes = nbPersonnes;
    }

    public LocalDateTime getDateTime2() {
        return dateTime2;
    }

    public void setDateTime2(LocalDateTime dateTime2) {
        this.dateTime2 = dateTime2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public LocalDateTime getDateTime1() {
        return dateTime1;
    }

    public void setDateTime1(LocalDateTime dateTime1) {
        this.dateTime1 = dateTime1;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

}
