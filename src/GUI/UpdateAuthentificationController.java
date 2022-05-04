/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Profile;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ProfileService;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author omen
 */
public class UpdateAuthentificationController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField mdp;
    @FXML
    private ComboBox<String> role;
    private ObservableList<String> list = FXCollections.observableArrayList("Admin","User");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   role.setItems(list);
        role.setValue("Etudiant");
username.setText(UserHolder.getMail());    }    

    @FXML
    private void update(ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
                        if (  !username.getText().trim().isEmpty() && !mdp.getText().trim().isEmpty()){

        if(us.modifier(new Utilisateur(UserHolder.getId(),username.getText(), mdp.getText(), role.getValue().toString()))!=0){
              JOptionPane.showMessageDialog(null,"Authentification modifier");
            try {
                goToEditProfile(event);
            } catch (IOException ex) {
                Logger.getLogger(UpdateAuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
                                JOptionPane.showMessageDialog(null,"Echec!");

        }}
                        else {
                                                JOptionPane.showMessageDialog(null,"Verifier les champs!");

                        }
    }

    @FXML
    private void goToEditProfile(ActionEvent event) throws IOException{
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
    
}
