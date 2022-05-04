/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author skander
 */
public class Home extends Application{
  
    
     @Override
     public void start(Stage stage) throws Exception {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("Abonne.fxml"));
       
            Scene scene = new Scene(root);
            stage.setTitle("INSCRIPTION");
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            
        }
}
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
       
        
    }
}
   
