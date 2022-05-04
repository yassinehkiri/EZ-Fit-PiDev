/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Publication;
import java.util.List;

/**
 *
 * @author yassine
 */
public interface iPublication {
    public void AjouterPublication(Publication p);
public void SupprimerPublication(int id);
//public void ModifierTrip(Trip p);
        public List<Publication> afficher();
       /* public void ModifierPublication(Publication p,  int id );*/
        public int modifier (Publication p);
}
