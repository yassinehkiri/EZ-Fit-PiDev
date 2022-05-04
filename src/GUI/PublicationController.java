/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.PublicationService;
import util.MyConnexion;


/**
 * FXML Controller class
 *
 * @author yassine
 */
public class PublicationController implements Initializable {

    @FXML
    private PieChart pieHash;
    @FXML
    private PieChart pieKey;
    @FXML
    private Label lblClose;
    @FXML
    private TableColumn<Publication, String> noma;
    @FXML
    private TableColumn<Publication, String> typea;
    @FXML
    private TableColumn<Publication, String> sexea;
    @FXML
    private TableColumn<Publication, String> cata;
    @FXML
    private TableView<Publication> tablepub;
    @FXML
    private TextField idp;
    @FXML
    private TextField nomp;
    @FXML
    private TextField datep;
    @FXML
    private TextArea pubp;
    @FXML
    private TextField id;
    @FXML
    private Rating rating;
    @FXML
    private Label msg;
    @FXML
    private Button clearr;

       @FXML
    private Button supprimer;
    
    
   
   


int  index= -1; 
  
 
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rating.ratingProperty().addListener((ObservableValue<? extends Number> url1, Number t, Number t1) -> {
            msg.setText("Rating : "+t1.toString());
        });
        
        affiche();
          
        ObservableList<Publication>  list =  FXCollections.observableArrayList();
        
          try { 
            Connection cnx = MyConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id , redacteur, date, contenu FROM publication");
            while(rs.next())
            {
                list.add(new Publication(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }

       //noma.setCellValueFactory(data ->
       // {
          //  return new ReadOnlyStringWrapper(data.getValue().getId());
       // });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    typea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getRedacteur());
        });
    sexea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getDate());
        });
         cata.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getContenu());
        });
       
        // TODO
        tablepub.setItems(list);
        tablepub.refresh();
    // TODO
    }    

    @FXML
    private void selected(MouseEvent event) {
        index=tablepub.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        idp.setText(noma.getCellData(index));
        nomp.setText(typea.getCellData(index));
        datep.setText(sexea.getCellData(index));
        pubp.setText(cata.getCellData(index));
    }

    @FXML
    private void ajouter(ActionEvent event) {
    
    if(verifUserChamps() ){ 
                        if ( controlSaisie()){
                                 if ( checkIfNumber()){
                                       if ( nameCheck()){
                                           if(pubCheck()){

         PublicationService ts = new PublicationService();
                  PublicationService ts1 = new PublicationService();
       ts.AjouterPublication(new Publication(nomp.getText(),datep.getText(),pubp.getText()));
                affiche();

                   }
              }
         }
      }
    }
 
  }

    @FXML
    private void modifier(ActionEvent event) {
      Alert alert = new Alert(AlertType.CONFIRMATION);
      Alert alert1 = new Alert(AlertType.INFORMATION);
      alert.setTitle("Modifier Publication");
      alert.setHeaderText("voulez vous vraiment modifier cette publication ?");
      
        String id=idp.getText();
        String redacteur=nomp.getText();
        String date=datep.getText();
        String contenu=pubp.getText();
       
  if(verifUserChamps() ){ 
                        if ( controlSaisie()){
                                 if ( checkIfNumber()){
                                       if ( nameCheck()){
                                           if(pubCheck()){
        PublicationService p = new PublicationService();
        Publication pb = new Publication();
        pb = (Publication) tablepub.getSelectionModel().getSelectedItem();
        alert.setContentText(pb.getContenu());
        Optional<ButtonType> modifier1 = alert.showAndWait();
        pb.setId(pb.getId());
        pb.setRedacteur(redacteur);
        pb.setDate(date);
        pb.setContenu(contenu);
        
        if(modifier1.get()==ButtonType.OK){
          p.modifier(pb);
            alert1.setContentText("Publication a été Modifier!");
            alert1.show();
        } else if (modifier1.get() == ButtonType.CANCEL) {
         alert1.setContentText("Modification a été Annuler!");
         alert1.show();
        }
         affiche();
           }
              }
           }
        }
       }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        
      Alert alert = new Alert(AlertType.CONFIRMATION);
      Alert alert1 = new Alert(AlertType.INFORMATION);
      alert.setTitle("Supprimer Publication");
      alert.setHeaderText("voulez vous vraiment supprimer cette publication ?");
        
        
        
        PublicationService p = new PublicationService();
        Publication pb = new Publication();
        pb = (Publication) tablepub.getSelectionModel().getSelectedItem();
        alert.setContentText(pb.getContenu());
        Optional<ButtonType> supprimer1 = alert.showAndWait();
        if(supprimer1.get()==ButtonType.OK){
          p.SupprimerPublication(pb.getId());
            alert1.setContentText("Publication a été Supprimer!");
            alert1.show();
        } else if (supprimer1.get() == ButtonType.CANCEL) {
         alert1.setContentText("Suppression a été Annuler!");
         alert1.show();
        }
         affiche();
    }
    
    
     public ObservableList<Publication> show1()
    { 
       

           try {
               ObservableList<Publication> obl =FXCollections.observableArrayList();
                             Connection cnx = MyConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                  PreparedStatement pt= cnx.prepareStatement("SELECT id, redacteur, date, contenu FROM publication ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Publication ls = new Publication();
                 ls.setId(rs.getInt("id"));
                 ls.setRedacteur(rs.getString("redacteur"));
                 ls.setDate(rs.getString("date"));
                 ls.setContenu(rs.getString("contenu"));
                
             

                  
                  System.out.println("");
                   obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    } 
    
    
     public void affiche() {
        
           //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
           //System.out.println(timeStamp);
                         
      noma.setCellValueFactory(new PropertyValueFactory<>("id"));
      typea.setCellValueFactory(new PropertyValueFactory<>("redacteur"));
      sexea.setCellValueFactory(new PropertyValueFactory<>("date"));
      cata.setCellValueFactory(new PropertyValueFactory<>("contenu"));
     
      ObservableList<Publication> obl =FXCollections.observableArrayList();
      obl=show1(); 
      tablepub.setItems(obl);
      System.out.println(""+obl);

                      
    }
                  //Controle De Saisie//
     
      public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        //idp.setStyle(styledefault);
        nomp.setStyle(styledefault);
        datep.setStyle(styledefault);
        pubp.setStyle(styledefault);
        

     

       /* if (idp.getText().equals("")) {
            idp.setStyle(style);
            verif = 1;
        }*/
       
        if ( nomp.getText().equals("")) {
             nomp.setStyle(style);
            verif = 1;
        }
         
        if (datep.getText().equals("")) {
            datep.setStyle(style);
            verif = 1;
        }
       
        if (pubp.getText().equals("")) {
            pubp.setStyle(style);
            verif = 1;
        }
       
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show() ; 
        
        return false;
    }
      
      
      public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         
                if(checkIfStringContainsNumber(nomp.getText())){
            alert.setContentText("Le nom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
             
        return true;
    }
      
       public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         
          /*if(checkIfStringContainsNumber2(idp.getText())){
            alert.setContentText("ID ne doit pas contenir des Lettres");
            alert.showAndWait();
            return false;
          }*/
          if(checkIfStringContainsNumber2(datep.getText())){
            alert.setContentText("La date ne doit pas contenir des Lettres");
            alert.showAndWait();
            return false;
          }
       return true;
    }
      public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }
      
      
      public boolean checkIfStringContainsNumber2(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
                return true;
            }
        }
        return false;
    }
      
      public boolean nameCheck(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
           
       if((nomp.getText().length()<3)){
            alert.setContentText("Le nom ne doit pas etre inferieur à 3 caracteres");
            alert.showAndWait();
            return false;
          }
       if((nomp.getText().length()>20)){
            alert.setContentText("Le nom ne doit pas depasser 20 caracteres");
            alert.showAndWait();
            return false;
       }
       return true;
    }
      
      public boolean pubCheck(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
           
       if((pubp.getText().length()>1000)){
            alert.setContentText("la publication ne doit pas depasser 1000 caracteres");
            alert.showAndWait();
            return false;
       }
       return true;
    }

    @FXML
    private void allerverspub(ActionEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("Publication.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void allerversrec(ActionEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
       
    }
    
    
    
      private void populateTable(ObservableList<Publication> branlist){
       tablepub.setItems(branlist);
      }
    @FXML
    private void search(KeyEvent event) {
        PublicationService bs=new PublicationService(); 
        Publication b= new Publication();
        ObservableList<Publication>filter= bs.chercherTitreplat(id.getText());
        populateTable(filter);
    }

    @FXML
    private void clear(ActionEvent event) {
        idp.setText(null);
        nomp.setText(null);
        datep.setText(null);
        msg.setText(null);
        //rating.setText(null);
        pubp.setText(null);
    }

    @FXML
    private void rate(MouseEvent event) {
        
    }

    
}
