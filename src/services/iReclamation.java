/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.util.List;

/**
 *
 * @author yassine
 */
public interface iReclamation {
     public void AjouterReclamation(Reclamation r);
     public void SupprimerReclamation(int idR);
     //public void ModifierTrip(Trip p);
     public List<Reclamation> afficher();
     /* public void ModifierPublication(Publication p,  int id );*/
     public int modifierR (Reclamation r);
}
