/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author nadaghanem
 */
public class Salle {

    private int id;
    private int num_salle;
    private int num_bloc;
    private int nbr_chaise;
    private int nbr_table;

    public Salle(int num_salle, int num_bloc, int nbr_chaise, int nbr_table) {
        this.num_salle = num_salle;
        this.num_bloc = num_bloc;
        this.nbr_chaise = nbr_chaise;
        this.nbr_table = nbr_table;
    }

    public Salle(int id, int num_salle, int num_bloc, int nbr_chaise, int nbr_table) {
        this.id = id;
        this.num_salle = num_salle;
        this.num_bloc = num_bloc;
        this.nbr_chaise = nbr_chaise;
        this.nbr_table = nbr_table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_salle() {
        return num_salle;
    }

    public void setNum_salle(int num_salle) {
        this.num_salle = num_salle;
    }

    public int getNum_bloc() {
        return num_bloc;
    }

    public void setNum_bloc(int num_bloc) {
        this.num_bloc = num_bloc;
    }

    public int getNbr_chaise() {
        return nbr_chaise;
    }

    public void setNbr_chaise(int nbr_chaise) {
        this.nbr_chaise = nbr_chaise;
    }

    public int getNbr_table() {
        return nbr_table;
    }

    public void setNbr_table(int nbr_table) {
        this.nbr_table = nbr_table;
    }

    @Override
    public String toString() {
        return num_salle + "- Block " + num_bloc;
    }
}
