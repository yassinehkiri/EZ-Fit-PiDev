/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Profile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
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

/**
 * FXML Controller class
 *
 * @author omen
 */
public class UpdateProfileController implements Initializable {

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
    @FXML
    private Button uploadImage;
    @FXML
    private ImageView pdp;
    @FXML
    private AnchorPane left_main;
            private ObservableList<String> list = FXCollections.observableArrayList("Homme","Femme");
    Profile p;
        File file;
    FileInputStream fis;
    InputStream f;
        String path="";
        int userId;

    /**
     * Initializes the controller class.
     */
   
    public void displayProfile(Profile e,int id){
        System.out.println(UserHolder.getId()+" / "+UserHolder.getNom()+" / "+UserHolder.getPrenom());
                ProfileService ps = new ProfileService();
        p = ps.findByUserId(id);
        f=p.getPdp();
           this.nom.setText(p.getNom());
        this.prenom.setText(p.getPrenom());
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.ddn.setValue(LocalDate.parse(p.getDdn(), formatter));
        Image profilePic= new Image(p.getPdp());
        this.pdp.setImage(profilePic);
        this.num_tel.setText(Integer.toString(p.getNum_tel()));
        this.adresse.setText(p.getAdresse());
        this.sexe.setValue(p.getSexe());
        userId=id;
     
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
  sexe.setItems(list);
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
    @FXML
    private void InsertImage(ActionEvent event) {
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

    @FXML
    private void updateProfile(ActionEvent event)throws IOException{ 
        ProfileService ps = new ProfileService();
                        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
                         Profile p2 ;
                        if(file!=null){
 fis = new FileInputStream(file);
                
             p2  = new Profile(p.getId(),Integer.parseInt(num_tel.getText()),this.nom.getText(),this.prenom.getText(),ddn.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),fis,adresse.getText(),this.sexe.getValue(),this.userId,file.length());
                        }
                        
                        else{
                                               

               
                p2 = new Profile(p.getId(),Integer.parseInt(num_tel.getText()),this.nom.getText(),this.prenom.getText(),ddn.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),adresse.getText(),this.sexe.getValue(),this.userId);
                        }
                        
                        if (  !nom.getText().trim().isEmpty() && !prenom.getText().trim().isEmpty() && !adresse.getText().trim().isEmpty() && !num_tel.getText().trim().isEmpty() && pattern.matcher(num_tel.getText()).matches()){
                    if(        ps.modifier(p2)!=0){
                   
                            JOptionPane.showMessageDialog(null,"Profile modifier");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Home.fxml"));
            Parent root = loader.load();
            HomeController hm = loader.getController();
            hm.displayProfile();
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
    @FXML
    private void goToProfile(ActionEvent event)  throws IOException {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Home.fxml"));
                Parent root = loader.load();
                HomeController hm = loader.getController();
                hm.displayProfile();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show(); 
        
    }

    @FXML
    private void goToUpdateAuthentification(ActionEvent event)  throws IOException{
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/UpdateAuthentification.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show(); 
    }
    
}
