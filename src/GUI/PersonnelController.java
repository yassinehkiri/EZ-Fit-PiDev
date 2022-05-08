/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.personnel;
import Service.personnelservice;
import Service.scontrole;
import Tools.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author lamis
 */
public class PersonnelController implements Initializable {

    @FXML
    private TextField nomsal;
    @FXML
    private TextField prenoms;
    @FXML
    private TextField telsal;
    @FXML
    private TextField emailsal;
    @FXML
    private TextField motdepsal;
    @FXML
    private Button ajout;
    @FXML
    private Button mod;
    @FXML
    private TableView<personnel> persotable;
    @FXML
    private TableColumn<personnel, Integer> idp;
    @FXML
    private TableColumn<personnel, Integer> tnomsc;
    @FXML
    private TableColumn<personnel, String> tnomp;
    @FXML
    private TableColumn<personnel, String> tprenomp;
    @FXML
    private TableColumn<personnel, Integer> ttelp;
    @FXML
    private TableColumn<personnel, String> temailp;
    @FXML
    private TableColumn<personnel, String> tmdpp;
    @FXML
    private TableColumn<personnel, Double> tsalp;
    @FXML
    private TableColumn<personnel, String> tpostp;
    @FXML
    private TableColumn<personnel, Integer> thtp;
    @FXML
    private TableColumn<personnel, Integer> thabp;
    @FXML
    private TableColumn<personnel, Date> tdateembp;
    @FXML
    private Button supp;
    @FXML
    private Button clearr;
    @FXML
    private TextField salaireper;
    @FXML
    private TextField postpers;
    @FXML
    private TextField htp;
    @FXML
    private TextField hap;
    @FXML
    private ComboBox<String> comboxcps;
ObservableList<personnel>  platList = FXCollections.observableArrayList();
  personnel ss=new personnel();
     Statement ste;
    private personnel r;
    String query = null;
    Connection connection = null ;
    Connection cnx=MaConnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
            + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
    @FXML
    private DatePicker dateepp;
    @FXML
    private Button hh;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        load();
          refperso();
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
         connection= MaConnexion.getInstance().getCnx();
          String nom_s = comboxcps.getValue();
           personnelservice rec = new personnelservice();
          int id= rec.chercherIds(nom_s);
          String nom_p =nomsal.getText();
           String prenom_p =prenoms.getText();
         // String image =imageeep.getText();
        //  String idcatt = idddccaatttppp.getValue();
           
          int tel_p =Integer.valueOf(telsal.getText());
          
          String email_p =emailsal.getText();
           String mdp =motdepsal.getText();
          double salaire_p =Float.valueOf(salaireper.getText());
           String poste =postpers.getText();
          int h_travail = Integer.valueOf(htp.getText());   
           int h_absence = Integer.valueOf(hap.getText());
               Date date_embauche = Date.valueOf(dateepp.getValue());
              //Date date_embauche = Integer.valueOf(hap.getText());
              
          scontrole sc= new scontrole();

  
        personnel p=new personnel(id,nom_p,prenom_p,tel_p,email_p,mdp,salaire_p,poste,h_travail,h_absence,date_embauche);
    
    Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(emailsal.getText());
       
       if(sc.existe(p)==0){
           if(!nom_p.isEmpty()||!nom_s.isEmpty()){
           
           if(!controler.matches()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("verifier la format de votre adresse email");
            alert.showAndWait();
        }else if(!sc.isNumeric(mdp))
        {
            Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Mot de Passe doit etre nombre");
            alert.showAndWait();
        }
        else if(!sc.Controlechar2(p))
        { Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Le nom ne doit contenir que des caracteres");
            alert.showAndWait();
        }
           else if(!sc.Controlechar3(p))
        { Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Le prenom ne doit contenir que des caracteres");
            alert.showAndWait();
        }
           else if(salaire_p<=0)
        { Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Le salaire doit etre un nombre positif");
            alert.showAndWait();
        }else{

         personnelservice ps = new personnelservice();
            ps.ajouter(p);
             Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Personnel ")
                                      .text("le personnel a été ajouté avec succes")
                                      .graphic(null)
                                      //.hideAfter(Duration.Hours(5))
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.show(); 
                              refperso();
load();
       }
        
       }}
       else  {
           Alert alert =new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("Ce personnel déjà existe");
            alert.showAndWait();
       }
      
    }
    
    
    //(idcategore,nom_p,prenom_p,tel_p,email_p,mdp,salaire_p,poste,h_travail,h_absence,date_embauche);
    private void load() {
    personnelservice pp=new personnelservice();
    connection= MaConnexion.getInstance().getCnx();
    refperso();
    idp.setCellValueFactory(new PropertyValueFactory<>("Id"));
   
    tnomp.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
    tprenomp.setCellValueFactory(new PropertyValueFactory<>("prenom_p"));
    ttelp.setCellValueFactory(new PropertyValueFactory<>("tel_p"));
    temailp.setCellValueFactory(new PropertyValueFactory<>("email_p"));
    tmdpp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
    tsalp.setCellValueFactory(new PropertyValueFactory<>("salaire_p"));
    tpostp.setCellValueFactory(new PropertyValueFactory<>("poste"));
    thtp.setCellValueFactory(new PropertyValueFactory<>("h_travail"));
    thabp.setCellValueFactory(new PropertyValueFactory<>("h_absence"));
    tdateembp.setCellValueFactory(new PropertyValueFactory<>("date_embauche"));
    tnomsc.setCellValueFactory(new PropertyValueFactory<>("nom_s"));
    comboxcps.setItems(FXCollections.observableArrayList(pp.getAll()));
    }   
    
   private void refperso() {     
        try {
            platList.clear();
           
           query = "select personnel.id,salle.nom_s,personnel.nom_p,personnel.prenom_p,personnel.tel_p,personnel.email_p,personnel.mdp,personnel.salaire_p,personnel.poste,personnel.h_travail,personnel.h_absence,personnel.date_embauche from personnel  INNER JOIN  salle  on personnel.salle_id=salle.id  ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                
                platList.add(new  personnel(
                    
                resultSet.getInt("id"),
                        
                       // resultSet.getInt("salle_id"),
                         
              resultSet.getString("nom_p"),
                resultSet.getString("prenom_p") ,
                resultSet.getInt("tel_p"),
               resultSet.getString("email_p"),
               resultSet.getString("mdp"),
              resultSet.getDouble("salaire_p"),
               resultSet.getString("poste"),
              resultSet.getInt("h_travail"),
              resultSet.getInt("h_absence"),
              resultSet.getDate("date_embauche"),
               resultSet.getString("nom_s")
              
                        )); 
               persotable.setItems(platList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        
               
                   
    
   

    @FXML
    private void modifier(ActionEvent event) {
                personnel cat=new personnel();
   personnelservice pl = new personnelservice();
   cat=persotable.getSelectionModel().getSelectedItem();
   cat.setNom_p(nomsal.getText());
   cat.setPrenom_p(prenoms.getText());
   
  
   int tel_p=Integer.valueOf(telsal.getText());
   cat.setTel_p(tel_p);
    cat.setEmail_p(emailsal.getText());
 cat.setMdp(motdepsal.getText());
  double salaire_p=Float.valueOf(salaireper.getText());
   cat.setSalaire_p(salaire_p);
 cat.setPoste(postpers.getText());
 int h_travail=Integer.valueOf(htp.getText());
   cat.setH_travail(h_travail);
   int h_absence=Integer.valueOf(hap.getText());
   cat.setH_absence(h_absence);
   pl.modifier(cat);
   load(); 
refperso();
    }

    @FXML
    private void onclicked(MouseEvent event) {
        try {
     personnel cattt = persotable.getSelectionModel().getSelectedItem();
   
    
    nomsal.setText(String.valueOf(cattt.getNom_p()));
    prenoms.setText(String.valueOf(cattt.getPrenom_p()));
  
    telsal.setText(String.valueOf(cattt.getTel_p()));
    emailsal.setText(String.valueOf(cattt.getEmail_p()));
    motdepsal.setText(String.valueOf(cattt.getMdp()));
        salaireper.setText(String.valueOf(cattt.getSalaire_p()));
    postpers.setText(String.valueOf(cattt.getPoste()));
    htp.setText(String.valueOf(cattt.getH_travail())); 
    hap.setText(String.valueOf(cattt.getH_absence()));
   
        } catch (Exception e) {
               System.out.println(e.getMessage());
           }
    }

    @FXML
    private void supprimerr(ActionEvent event) {
               if (!persotable.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer plat " + persotable.getSelectionModel().getSelectedItem().getNom_p()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    personnelservice r=new personnelservice();
    r.supprimer(persotable.getSelectionModel().getSelectedItem());
     Notifications notificationBuild = Notifications.create()
                                      .title("Traitement personnel ")
                                      .text("un personnel a été supprimé avec succes")
                                      .graphic(null)
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.show(); 
    refperso();
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
          nomsal.setText(null);
        prenoms.setText(null);
        telsal.setText(null);
        emailsal.setText(null);
        motdepsal.setText(null);
        salaireper.setText(null);
         postpers.setText(null);
        htp.setText(null);
        hap.setText(null);
       // salaireper.setText(null);
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
    
     
}
