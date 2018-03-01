/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISERVICE;

import MODEL.Reclamation;
import MODEL.Utilisateur;
import java.util.List;

/**
 *
 * @author USER
 */
public interface IreclamationService extends Iservice<Reclamation,Integer>{
     public List<Reclamation> findId_user(Integer r) ;
      public void addMal(Reclamation t) ;
      public void updateMal(Reclamation t) ;
      public Utilisateur findUserByMatricule(String Matricule) ;
}
