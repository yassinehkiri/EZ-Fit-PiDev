/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.Sendmail;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author omen
 */
public class SignUpController implements Initializable {

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
    public void signUp(ActionEvent event) throws IOException{
        
                UtilisateurService up = new UtilisateurService();
                System.out.println(username.getText()+" "+mdp.getText());
                                 if (  !username.getText().trim().isEmpty() && !mdp.getText().trim().isEmpty()  ){

                if ((username.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "esprit.tn$"))){
                                      
                             try{
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(mdp.getText().getBytes());
    String mdpHash = Utility.toHexString(hash);
        String gs= generateString();
                    if(up.ajouter(new Utilisateur(this.username.getText(),mdpHash,this.role.getValue().toString()),gs)!=0){
                        Sendmail sm = new Sendmail();
                        sm.envoyer(username.getText(), "ESPRIT-CONNECT Confirmation E-mail", "Your confirmation code is : \n"+gs);
                            JOptionPane.showMessageDialog(null,"Compte créer");

                    this.goToLogin(event);
                    }
                    else JOptionPane.showMessageDialog(null,"Echec!");
                }
 catch(Exception e ){
    System.out.println(e.getMessage());
}

                }
                else {
                    JOptionPane.showMessageDialog(null,"Mail invalid!");
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
