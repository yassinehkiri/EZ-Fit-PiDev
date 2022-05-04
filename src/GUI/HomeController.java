/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Profile;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.stage.Stage;
import services.ProfileService;

/**
 *
 * @author omen
 */
public class HomeController {
    @FXML
    Label nom;
    @FXML
    Label prenom;
    @FXML
    Label ddn;
    @FXML
    ImageView pdp;
    @FXML
    Label num_tel;
    @FXML
    Label adresse;
    @FXML
    Label sexe;
   
   
    public void displayProfile(){
      ProfileService ps = new ProfileService();
        Profile p = ps.findByUserId(UserHolder.getId());
UserHolder.setNom(p.getNom());
UserHolder.setPrenom(p.getPrenom());
        System.out.println(p.getNom()+" / "+UserHolder.getId());
        this.nom.setText("Nom: "+p.getNom());
        this.prenom.setText("Prenom: "+p.getPrenom());
        this.ddn.setText("Date de naissance: "+p.getDdn());
        Image profilePic= new Image(p.getPdp());
        this.pdp.setImage(profilePic);
        this.num_tel.setText("Numero telephone: "+Integer.toString(p.getNum_tel()));
        this.adresse.setText("Adresse: "+p.getAdresse());
        this.sexe.setText("Sexe: "+p.getSexe());
   
        
    }
    @FXML
        public void goToLogin(ActionEvent event) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Authentification.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
             
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void goToUpdateProfile(ActionEvent event) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/UpdateProfile.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
             UpdateProfileController pc = loader.getController();
             ProfileService ps = new ProfileService();
             Profile p2 = ps.findByUserId(UserHolder.getId());
                pc.displayProfile(p2,UserHolder.getId());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void goToFindAmis(ActionEvent event) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/FindAmis.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void goToMyAmis(ActionEvent event) throws IOException{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MyAmis.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void goToRequests(ActionEvent event) throws IOException{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RequeteAmis.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
     
}

