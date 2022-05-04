/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author omen
 */
public class CodeCompteController implements Initializable {

    @FXML
    private TextField code;
private String mail;
private int id;/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToLogin(ActionEvent event) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Authentification.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void confirmer(ActionEvent event)  throws IOException{
        UtilisateurService us = new UtilisateurService();
        System.out.println(id);
        if (us.confirmer(mail, id, code.getText())==1){
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CreateProfile.fxml"));
                Parent root = loader.load();
                CreateProfileController pc = loader.getController();
                pc.getUserId(id);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
        
    }
    public void setUser(String mail,int id){
        this.mail=mail;
        this.id=id;
    }
}
