/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ReclamationService;
import util.MyConnexion;
import util.javaMail;

/**
 * FXML Controller class
 *
 * @author yassine
 */
public class ReclamationController implements Initializable {

    @FXML
    private Label lblClose;
    @FXML
    private TableView<Reclamation> tablerec;
    @FXML
    private TableColumn<Reclamation, String> noma;
    @FXML
    private TableColumn<Reclamation, String> typea;
    @FXML
    private TableColumn<Reclamation, String> sexea;
    @FXML
    private TableColumn<Reclamation, String> cata;
    @FXML
    private TextField idr;
    @FXML
    private TextField nomr;
    @FXML
    private TextField dater;
    @FXML
    private TextArea rec;
    @FXML
    private TextField objet;
    @FXML
    private TextField corps;
    int  indexR= -1;
    @FXML
    
   
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         afficheR();
          
        ObservableList<Reclamation>  list =  FXCollections.observableArrayList();
        
          try { 
            Connection cnx = MyConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT idR , redacteurR, dateR, contenuR FROM reclamation");
            while(rs.next())
            {
                list.add(new Reclamation(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

       //noma.setCellValueFactory(data ->
       // {
          //  return new ReadOnlyStringWrapper(data.getValue().getId());
       // });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    typea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getRedacteurR());
        });
    sexea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getDateR());
        });
         cata.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getContenuR());
        });
       
        // TODO
        tablerec.setItems(list);
        tablerec.refresh();
    // TODO
    }    
    
 @FXML
    private void selectedR(MouseEvent event) {
         indexR=tablerec.getSelectionModel().getSelectedIndex();
        if (indexR<= -1)
        {return; } 
        idr.setText(noma.getCellData(indexR));
                nomr.setText(typea.getCellData(indexR));
                dater.setText(sexea.getCellData(indexR));
                rec.setText(cata.getCellData(indexR));
    }

    @FXML
    private void ajouterR(ActionEvent event) {
        
        if(verifUserChamps() ){ 
                        if ( controlSaisie()){
                                 if ( checkIfNumber()){
                                       if ( nameCheck()){
                                           if(pubCheck()){


                                               ReclamationService ts1 = new  ReclamationService();
                  ts1.AjouterReclamation(new Reclamation(nomr.getText(),dater.getText(),rec.getText()));
                  try {
            String Object = objet.getText();
            String Corps = corps.getText();
            javaMail.sendMail("y.hkiri32@gmail.com", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
                afficheR();

                   }
              }
         }
      }
    }
 
        
  }

    @FXML
    private void modifierR(ActionEvent event) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Modifier Publication");
      alert.setHeaderText("voulez vous vraiment modifier cette publication ?");
        String idR=idr.getText();
        String redacteurR=nomr.getText();
        String dateR=dater.getText();
        String contenuR=rec.getText();
       
  if(verifUserChamps() ){ 
                        if ( controlSaisie()){
                                 if ( checkIfNumber()){
                                       if ( nameCheck()){
                                           if(pubCheck()){
        ReclamationService r = new ReclamationService();
        Reclamation rl = new Reclamation();
        alert.setContentText(rl.getContenuR());
        Optional<ButtonType> modifier1 = alert.showAndWait();
        rl = (Reclamation) tablerec.getSelectionModel().getSelectedItem();
        rl.setIdR(rl.getIdR());
        rl.setRedacteurR(redacteurR);
        rl.setDateR(dateR);
        rl.setContenuR(contenuR);
          if(modifier1.get()==ButtonType.OK){
          r.modifierR(rl);
            alert1.setContentText("Reclamation a été Modifier!");
            alert1.show();
        } else if (modifier1.get() == ButtonType.CANCEL) {
         alert1.setContentText("Modification a été Annuler!");
         alert1.show();
        }
         afficheR();
           }
              }
           }
        }
       }
    }

    @FXML
    private void supprimerR(ActionEvent event) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Supprimer Publication");
      alert.setHeaderText("voulez vous vraiment supprimer cette publication ?");
        ReclamationService r = new ReclamationService();
        Reclamation rl = new Reclamation();
        alert.setContentText(rl.getContenuR());
        Optional<ButtonType> supprimer1 = alert.showAndWait();
        rl = (Reclamation) tablerec.getSelectionModel().getSelectedItem();
                
         if(supprimer1.get()==ButtonType.OK){
          r.SupprimerReclamation(rl.getIdR());
            alert1.setContentText("Reclamation a été Supprimer!");
            alert1.show();
        } else if (supprimer1.get() == ButtonType.CANCEL) {
         alert1.setContentText("Suppression a été Annuler!");
         alert1.show();
        }
         afficheR();
    }

    public ObservableList<Reclamation> show1()
    { 
       

           try {
               ObservableList<Reclamation> obl =FXCollections.observableArrayList();
                             Connection cnx = MyConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                  PreparedStatement pt= cnx.prepareStatement("SELECT idR, redacteurR, dateR, contenuR FROM reclamation ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Reclamation ls = new Reclamation();
                 ls.setIdR(rs.getInt("idR"));
                 ls.setRedacteurR(rs.getString("redacteurR"));
                 ls.setDateR(rs.getString("dateR"));
                 ls.setContenuR(rs.getString("contenuR"));
                
             

                  
                  System.out.println("");
                   obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    } 
    
    
     public void afficheR() {
        
           //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
           //System.out.println(timeStamp);
                         
      noma.setCellValueFactory(new PropertyValueFactory<>("idR"));
      typea.setCellValueFactory(new PropertyValueFactory<>("redacteurR"));
      sexea.setCellValueFactory(new PropertyValueFactory<>("dateR"));
      cata.setCellValueFactory(new PropertyValueFactory<>("contenuR"));
     
      ObservableList<Reclamation> obl =FXCollections.observableArrayList();
      obl=show1(); 
      tablerec.setItems(obl);
      System.out.println(""+obl);

                      
    }
                  //Controle De Saisie//
     
      public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        //idp.setStyle(styledefault);
        nomr.setStyle(styledefault);
        dater.setStyle(styledefault);
        rec.setStyle(styledefault);
        

     

       /* if (idp.getText().equals("")) {
            idp.setStyle(style);
            verif = 1;
        }*/
       
        if ( nomr.getText().equals("")) {
             nomr.setStyle(style);
            verif = 1;
        }
         
        if (dater.getText().equals("")) {
            dater.setStyle(style);
            verif = 1;
        }
       
        if (rec.getText().equals("")) {
            rec.setStyle(style);
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
         
                if(checkIfStringContainsNumber(nomr.getText())){
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
          if(checkIfStringContainsNumber2(dater.getText())){
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
           
       if((nomr.getText().length()<3)){
            alert.setContentText("Le nom ne doit pas etre inferieur à 3 caracteres");
            alert.showAndWait();
            return false;
          }
       if((nomr.getText().length()>20)){
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
           
       if((rec.getText().length()>1000)){
            alert.setContentText("la reclamation ne doit pas depasser 1000 caracteres");
            alert.showAndWait();
            return false;
       }
       return true;
    }
      
      
      
   public void mailfonction(MouseEvent event) {
        try {
            String Object = objet.getText();
            String Corps = corps.getText();
            javaMail.sendMail("y.hkiri32@gmail.com", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
}
