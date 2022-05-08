/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Fournisseur;
import Service.FournisseurService;
import Tools.Maconnexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FournisseurController implements Initializable {

    @FXML
    private TextField nomf;
    @FXML
    private TextField prenomf;
    @FXML
    private TextField telf;
    @FXML
    private TextField emailf;
    @FXML
    private TextField adressef;
    @FXML
    private TextField ribf;
    @FXML
    private TextField imagef;
    @FXML
    private TableView<Fournisseur> tablef;
    @FXML
    private TableColumn<Fournisseur, String> prenomfv;
    @FXML
    private TableColumn<Fournisseur, Integer> telfv;
    @FXML
    private TableColumn<Fournisseur, String> emailfv;
    @FXML
    private TableColumn<Fournisseur, String> adressefv;
    @FXML
    private TableColumn<Fournisseur, Integer> ribfv;
    @FXML
    private TableColumn<Fournisseur, String> imagefv;
    @FXML
    private Button uploadimage;
    @FXML
    private Button ajouterf;
    @FXML
    private Button modifierf;
    @FXML
    private Button supprimerf;
    @FXML
    private Button Clear;
    @FXML
    private TextField recherche;
 ObservableList<Fournisseur>  platList = FXCollections.observableArrayList();
  Fournisseur ss=new Fournisseur();
     Statement ste;
    private Fournisseur r;
    String query = null;
    Connection connection = null ;
    Connection cnx=Maconnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    @FXML
    private TableColumn<Fournisseur, Integer> iffv;
    @FXML
    private TableColumn<Fournisseur, String> nomfv;
    @FXML
    private ImageView imageff;
    @FXML
    private AnchorPane retour;
    @FXML
    private Button rr;
   
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void upload(ActionEvent event) throws FileNotFoundException, IOException {
             Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\YummyGout\\\\Plat\\\\"+ "Plat"  + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            imageff.setImage(img);
           /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
            imagef.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
            
        } else {
            System.out.println("error");

        }
    
    }

    @FXML
    private void ajouter(ActionEvent event) {
        
          connection= Maconnexion.getInstance().getCnx();
          String nom_f =nomf.getText();
          String prenom_f =prenomf.getText();
     
         int tel_f =Integer.valueOf(telf.getText());
          FournisseurService rec = new FournisseurService();
          String email_f= emailf.getText();
          String adresse =adressef.getText();
          String rib_f =ribf.getText();
               
         // Scontrol sc= new Scontrol();

  
        
       Fournisseur re = new Fournisseur(nom_f,prenom_f,tel_f,email_f,adresse,rib_f,imagef.getText());
       
        if( nom_f.isEmpty()){           
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);            
            alert.setContentText("champs vides");
            alert.showAndWait();
            
        }  
       
        else {
            rec.ajouter(re);
             reffournisseur();
             Alert alert =new Alert(Alert.AlertType.CONFIRMATION);            
            alert.setContentText("fournisseur ajouter");
            alert.showAndWait();
        }}      
    private void load() {
    FournisseurService pp=new FournisseurService();
    connection= Maconnexion.getInstance().getCnx();
    reffournisseur();
    iffv.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomfv.setCellValueFactory(new PropertyValueFactory<>("nom_f"));
    prenomfv.setCellValueFactory(new PropertyValueFactory<>("prenom_f"));
    telfv.setCellValueFactory(new PropertyValueFactory<>("tel_f"));
    emailfv.setCellValueFactory(new PropertyValueFactory<>("email_f"));
    adressefv.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    ribfv.setCellValueFactory(new PropertyValueFactory<>("rib_f"));
    imagefv.setCellValueFactory(new PropertyValueFactory<>("image"));
    
    }   
    
   private void reffournisseur() {     
        try {
            platList.clear();
            
            query = "select * from Fournisseur";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                platList.add(new  Fournisseur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom_f"),
                        resultSet.getString("prenom_f"),
                        resultSet.getInt("tel_f"),
                        resultSet.getString("email_f"),
                        resultSet.getString("adresse"),
                        resultSet.getString("rib_f"),
                        resultSet.getString("image")
                        
                        )); 
               tablef.setItems(platList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    @FXML
    private void modifier(ActionEvent event) {
   Fournisseur cat=new Fournisseur();
   FournisseurService pl = new FournisseurService();
   cat=tablef.getSelectionModel().getSelectedItem();
   cat.setNom_f(nomf.getText());
   cat.setPrenom_f(prenomf.getText());
   int tel_f=Integer.valueOf(telf.getText());
   cat.setTel_f(tel_f);
   cat.setEmail_f(emailf.getText());
   cat.setAdresse(adressef.getText());
   cat.setRib_f(ribf.getText());
   cat.setImage(imagef.getText());
   
   pl.modifier(cat);
   load(); 
   reffournisseur();      
    }

    @FXML
    private void supprimerr(ActionEvent event) {
              if (!tablef.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer un fournisseur " + tablef.getSelectionModel().getSelectedItem().getNom_f()+ " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    FournisseurService cc = new FournisseurService();
    cc.supprimer(tablef.getSelectionModel().getSelectedItem());
    reffournisseur();
}
  }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
        
 
    }

    @FXML
    private void clear(ActionEvent event) {
        nomf.setText(null);
        prenomf.setText(null);
        telf.setText(null);
        emailf.setText(null);
        adressef.setText(null);
        ribf.setText(null);
        imagef.setText(null);
    }

    @FXML
    private void rechercher(KeyEvent event) {
        
        FournisseurService bs=new FournisseurService(); 
        Fournisseur b= new Fournisseur();
        ObservableList<Fournisseur>filter= bs.chercherTitreplat(recherche.getText());
        populateTable(filter);
    }
     private void populateTable(ObservableList<Fournisseur> branlist){
       tablef.setItems(branlist);
   
       }

    @FXML
    private void oncliked(MouseEvent event) {
         
    try {
     Fournisseur cattt = tablef.getSelectionModel().getSelectedItem();
   
    
    nomf.setText(String.valueOf(cattt.getNom_f()));
    prenomf.setText(String.valueOf(cattt.getPrenom_f()));
   // imageeep.setText(String.valueOf(cattt.getImage()));
    telf.setText(String.valueOf(cattt.getTel_f()));
    emailf.setText(String.valueOf(cattt.getEmail_f()));
    adressef.setText(String.valueOf(cattt.getAdresse()));
    ribf.setText(String.valueOf(cattt.getRib_f()));
    imagef.setText(String.valueOf(cattt.getImage()));
        String path = cattt.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageff.setImage(img);
    
        } catch (Exception e) {
               System.out.println(e.getMessage());
           }
    }

    private void retour11(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void ret1(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
}
