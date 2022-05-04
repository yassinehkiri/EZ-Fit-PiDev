/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author omen
 */
public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void goToHome(ActionEvent event) throws IOException{

             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Home.fxml"));
      this.root = loader.load();
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    public void goToLogin(ActionEvent event) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Authentification.fxml"));
      this.root = loader.load();
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
        
    }
    public void goToSignUp(ActionEvent event) throws IOException{
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SignUp.fxml"));
      this.root = loader.load();
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    
}
