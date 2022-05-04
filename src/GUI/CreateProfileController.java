/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Profile;
import entities.Utilisateur;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ProfileService;
import services.Sendmail;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author omen
 */
public class CreateProfileController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<String> sexe;
    @FXML
    private DatePicker ddn;
    @FXML
    private TextField num_tel;
    private Desktop desktop = Desktop.getDesktop();
        private ObservableList<String> list = FXCollections.observableArrayList("Homme","Femme");
    @FXML
    private Button uploadImage;
    @FXML
    private ImageView pdp;
    @FXML
    private AnchorPane left_main;
    private int userId;
    String path="";
    File file;
    FileInputStream fis;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  sexe.setItems(list);
        sexe.setValue("Homme");  
        num_tel.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8));

       nom.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(20));
              prenom.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(20));

    }    
    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
return new EventHandler<KeyEvent>() {
@Override
public void handle(KeyEvent e) {
TextField txt_TextField = (TextField) e.getSource();                
if (txt_TextField.getText().length() >= max_Lengh) {                    
e.consume();
}
if(e.getCharacter().matches("[A-Za-z]")){ 
}else{
e.consume();
}
}
};
}
public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
return new EventHandler<KeyEvent>() {
@Override
public void handle(KeyEvent e) {
TextField txt_TextField = (TextField) e.getSource();                
if (txt_TextField.getText().length() >= max_Lengh) {                    
e.consume();
}
if(e.getCharacter().matches("[0-9.]")){ 
if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
e.consume();
}else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
e.consume(); 
}
}else{
e.consume();
}
}
};
}
public void getUserId(int id){
    userId=id;
}
    @FXML

         public void InsertImage(){
         FileChooser open = new FileChooser();
        Stage stage =(Stage)left_main.getScene().getWindow();
        file = open.showOpenDialog(stage);
        if(file != null){
            path = file.getAbsolutePath();
            
            Image image = new Image(file.toURI().toString(), 110,110,false,true);
            pdp.setImage(image);
        }else{
            System.out.println("No FILE EXIST");
            }
        }
   
     private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
         @FXML
    @SuppressWarnings("empty-statement")
    public void createProfile(ActionEvent event) throws IOException{
                ProfileService ps = new ProfileService();
                Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
                fis = new FileInputStream(file);
                System.out.println(fis);
                Profile p = new Profile(Integer.parseInt(num_tel.getText()),this.nom.getText(),this.prenom.getText(),ddn.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),fis,adresse.getText(),this.sexe.getValue(),this.userId,file.length());
                if (  !nom.getText().trim().isEmpty() && !prenom.getText().trim().isEmpty() && !adresse.getText().trim().isEmpty() && !num_tel.getText().trim().isEmpty() && pattern.matcher(num_tel.getText()).matches()){
                    if(ps.ajouter(p)!=0){
                   
                            JOptionPane.showMessageDialog(null,"Profile créer");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainWindow.fxml"));
            Parent root = loader.load();
          
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();  
                    }
                    else JOptionPane.showMessageDialog(null,"Echec!");
                }
              

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
    
}
