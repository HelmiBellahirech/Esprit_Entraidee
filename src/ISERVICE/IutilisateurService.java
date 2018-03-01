/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

import MODEL.Utilisateur;

/**
 *
 * @author OrbitG
 */
public interface IutilisateurService extends Iservice<Utilisateur,Integer>{
    public int login(String username, String mdp) ; 
    public Utilisateur getById(Integer id) ; 
     public Utilisateur findbynum(String num);
     public Utilisateur findUserByMatricule(String Matricule);
}
