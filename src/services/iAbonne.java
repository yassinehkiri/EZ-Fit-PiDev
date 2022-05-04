/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Abonne;
import java.util.List;

/**
 *
 * @author yassine
 */
public interface iAbonne {
     public void AjouterAbonne(Abonne a);
     public void SupprimerAbonne(int id);
     //public void ModifierTrip(Trip p);
     public List<Abonne> afficher();
     /* public void ModifierPublication(Publication p,  int id );*/
     public int modifier (Abonne a);
}
