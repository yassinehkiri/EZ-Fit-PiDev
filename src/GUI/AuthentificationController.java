/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import entities.Profile;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Random;
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
import services.ProfileService;
import services.Sendmail;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author gaming
 */
public class AuthentificationController implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private TextField mdp;
    private SceneController sc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    
    @FXML
    private void connexion(ActionEvent event) throws IOException{
        UtilisateurService up = new UtilisateurService();
                            try{
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(mdp.getText().getBytes());
    String mdpHash = Utility.toHexString(hash);
                                    String[] r=up.connexion(username.getText(), mdpHash);
                                          if (r!=null) {
            JOptionPane.showMessageDialog(null,"Bienvenue");
    
        UserHolder.setMail(username.getText());
        UserHolder.setId(Integer.parseInt(r[0]));
                                              System.out.println(r[0]+" / "+r[1]+" / "+r[2] );
                                              ProfileService ps = new ProfileService();
        Profile p = ps.findByUserId(Integer.parseInt(r[0]));
                    if ("Admin".equals(r[2])){         
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AdminUser.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();           }

        else if (p!=null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainWindow.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();    
            }
            else if ("0".equals(r[1])){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CreateProfile.fxml"));
                Parent root = loader.load();
                CreateProfileController pc = loader.getController();
                pc.getUserId(Integer.parseInt(r[0]));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CodeCompte.fxml"));
                Parent root = loader.load();
                CodeCompteController cc= loader.getController();
                cc.setUser(username.getText(),Integer.parseInt(r[0]));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else {
                    JOptionPane.showMessageDialog(null,"Verifier les champs");

        }
}
                            
                            catch(Exception e){
                                System.out.println(e.getMessage());
                            }
  
    }
    
     @FXML
    private void goToSignUp(ActionEvent event) throws IOException{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SignUp.fxml"));
      Parent root = loader.load();
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }

    @FXML
    private void motDePasseOublier(ActionEvent event)throws IOException {
                UtilisateurService up = new UtilisateurService();
                System.out.println(username.getText()+" "+mdp.getText());
                if (  !username.getText().trim().isEmpty() ){
                    try{

                    if(up.resetPassword(username.getText())!=0){
                      
                            JOptionPane.showMessageDialog(null,"Mot de passe envoyer");

                    }
                    else JOptionPane.showMessageDialog(null,"Echec!");
                }
 catch(Exception e ){
    System.out.println(e.getMessage());
}}
                
                else{
                                                JOptionPane.showMessageDialog(null,"Verifier les champs!");

                }

    }
            public String generateString() {
   int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 8;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();


    return generatedString;
}
}
