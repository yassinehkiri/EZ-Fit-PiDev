/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface Iservice<T>{
      void ajouter(T t );
    List<T> afficher();
    void supprimer (T t);
    void modifier(T t);
    
}
