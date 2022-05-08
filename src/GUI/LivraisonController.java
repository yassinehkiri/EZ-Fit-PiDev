/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;

import Entities.Livraison;

import Service.LivraisonService;
import Tools.Maconnexion;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LivraisonController implements Initializable {

    @FXML
    private TextField numl;
    @FXML
    private TextField noml;
    @FXML
    private TextField prenoml;
    @FXML
    private TextField tell;
    @FXML
    private TextField adressel;
    private TextField datel;
    private TextField dateal;
    @FXML
    private TableView<Livraison> tableliv;
    @FXML
    private TableColumn<Livraison, Integer> iffv;
    @FXML
    private TableColumn<Livraison, Integer> numvl;
    @FXML
    private TableColumn<Livraison, String> nomlv;
    @FXML
    private TableColumn<Livraison, String> prenomlv;
    @FXML
    private TableColumn<Livraison, String> telfv;
    @FXML
    private TableColumn<Livraison, String> adresselv;
    @FXML
    private TableColumn<Livraison, Date> datelv;
    @FXML
    private TableColumn<Livraison, Date> datearl;
    @FXML
    private Button ajouterliv;
    @FXML
    private Button modifierliv;
    @FXML
    private Button supprimerliv;
    @FXML
    private Button Clearl;
ObservableList<Livraison>  platList = FXCollections.observableArrayList();
  Livraison ss=new Livraison();
     Statement ste;
    private Livraison r;
    String query = null;
    Connection connection = null ;
    Connection cnx=Maconnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    @FXML
    private DatePicker datelll;
    @FXML
    private DatePicker dateallll;
    @FXML
    private Button rr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
        reffournisseur();
    }    

    @FXML
    private void oncliked(MouseEvent event) {
             try {
     Livraison cattt = tableliv.getSelectionModel().getSelectedItem();
   
    
    numl.setText(String.valueOf(cattt.getNum_l()));
    noml.setText(String.valueOf(cattt.getNom_livreur()));
   // imageeep.setText(String.valueOf(cattt.getImage()));
    prenoml.setText(String.valueOf(cattt.getPrenom_livreur()));
    tell.setText(String.valueOf(cattt.getTel_livreur()));
    adressel.setText(String.valueOf(cattt.getAdresse_livraison()));
   // datelll.setText(String.valueOf(cattt.getDate_livraison()));
    dateal.setText(String.valueOf(cattt.getDate_arrive()));
       
        } catch (Exception e) {
               System.out.println(e.getMessage());
           }
    }

    @FXML
    private void ajouterl(ActionEvent event)  {
         connection= Maconnexion.getInstance().getCnx();
           int num_l =Integer.valueOf(numl.getText());
          
          String nom_livreur =noml.getText();
         String prenom_livreur =prenoml.getText();
         String tel_livreur =tell.getText();
          LivraisonService rec = new LivraisonService();
          String adresse_livraison= adressel.getText();
//          String date_livraison =datel.getText();
//          String date_arrive =dateal.getText();
         Date date_livraison = Date.valueOf(datelll.getValue());
         // Date date_arrive = Date.valueOf(dateal.getValue());
            Date date_arrive = Date.valueOf(dateallll.getValue());
         // Scontrol sc= new Scontrol();*
         
           Livraison l=new Livraison(num_l,nom_livreur,prenom_livreur,tel_livreur,adresse_livraison,date_livraison,date_arrive);
  
           if( nom_livreur.isEmpty()){           
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);            
            alert.setContentText("champs vides");
            alert.showAndWait();
            
        }
        else {
            rec.ajouter(l);
       
             reffournisseur();
             Alert alert =new Alert(Alert.AlertType.CONFIRMATION);            
            alert.setContentText("livraison ajoutée");
            alert.showAndWait();
          sms();
        }}      
       private void load() {
    LivraisonService pp=new LivraisonService();
    connection= Maconnexion.getInstance().getCnx();
    reffournisseur();
    iffv.setCellValueFactory(new PropertyValueFactory<>("id"));
    numvl.setCellValueFactory(new PropertyValueFactory<>("num_l"));
    nomlv.setCellValueFactory(new PropertyValueFactory<>("nom_livreur"));
   // telfv.setCellValueFactory(new PropertyValueFactory<>("tel_f"));
    prenomlv.setCellValueFactory(new PropertyValueFactory<>("prenom_livreur"));
    telfv.setCellValueFactory(new PropertyValueFactory<>("tel_livreur"));
    adresselv.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
    datelv.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
    datearl.setCellValueFactory(new PropertyValueFactory<>("date_arrive"));
    
    }   
    
 private void reffournisseur() {     
        try {
            platList.clear();
            
            query = "select * from Livraison";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                platList.add(new  Livraison(
                        resultSet.getInt("id"),
                        resultSet.getInt("num_l"),
                        resultSet.getString("nom_livreur"),
                        resultSet.getString("prenom_livreur"),
                        resultSet.getString("tel_livreur"),
                        resultSet.getString("adresse_livraison"),
                        resultSet.getDate("date_livraison"),
                        resultSet.getDate("date_arrive")
                        
                        )); 
               tableliv.setItems(platList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    private void modifierl(ActionEvent event) {
         Livraison cat=new Livraison();
   LivraisonService pl = new LivraisonService();
   cat=tableliv.getSelectionModel().getSelectedItem();
      int num_l =Integer.valueOf(numl.getText());
   cat.setNum_l(num_l);
   cat.setNom_livreur(noml.getText());
   cat.setPrenom_livreur(prenoml.getText());

   cat.setTel_livreur(tell.getText());
   cat.setAdresse_livraison(adressel.getText());
  // cat.setDate_livraison(datel.getText());
   //cat.setDate_arrive(dateal.getText());
   
   pl.modifier(cat);
   load(); 
   reffournisseur(); 
    }

    @FXML
    private void supprimerrl(ActionEvent event) {
                       if (!tableliv.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer une livraison " + tableliv.getSelectionModel().getSelectedItem().getNom_livreur()+ " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    LivraisonService cc = new LivraisonService();
    cc.supprimer(tableliv.getSelectionModel().getSelectedItem());
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
    private void clearl(ActionEvent event) {
           numl.setText(null);
        noml.setText(null);
        prenoml.setText(null);
        tell.setText(null);
        adressel.setText(null);
     
    }

    @FXML
    private void endd(ActionEvent event) {
    }

    @FXML
    private void rett(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
    
    
    void sms()
    {
       
        int num=96002288;
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("boutheina.lagrem@esprit.tn");
        defaultClient.setPassword("454D1CC6-E6B2-12A5-7608-E310D047E08F");
        SmsApi apiInstance = new SmsApi(defaultClient);
        
        
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("Votre livraison est en cours de traitement" );
        smsMessage.to("+216"+num);
        smsMessage.source("Livraison");
       

        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            e.printStackTrace();
        }
    }
    
     
    
}
