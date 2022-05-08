/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Equipement;
import Service.BareCode;
import Entities.Fournisseur;
import Entities.Livraison;
import Service.EquipemenetService;
import Service.FournisseurService;
import Service.LivraisonService;
import Service.Scontrol;
import Tools.Maconnexion;
import Tools.QrCode;
import com.google.zxing.ReaderException;
import com.google.zxing.WriterException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EquipementController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField marquee;
    @FXML
    private TextField prixx;
    @FXML
    private TableView<Equipement> tabequipement;
    @FXML
    private TableColumn<Equipement, Integer> iffv;
    @FXML
    private TableColumn<Equipement, String> typev;
    @FXML
    private TableColumn<Equipement, String> marquev;
    @FXML
    private TableColumn<Equipement, String> gammev;
    @FXML
    private TableColumn<Equipement, Integer> quantitev;
    @FXML
    private TableColumn<Equipement, Date> datecommandev;
    @FXML
    private TableColumn<Equipement, Double> prixv;
    @FXML
    private TableColumn<Equipement, String> nomfournissv;
    @FXML
    private Button ajouterf;
    @FXML
    private Button modifierf;
    @FXML
    private Button supprimerf;
    @FXML
    private Button Clear;
    @FXML
    private ComboBox<String> comboxnf;
    @FXML
    private DatePicker datecc;
    @FXML
    private ComboBox<String> comboxgamme;
    
    ObservableList<Equipement>  platList = FXCollections.observableArrayList();
  Equipement ss=new Equipement();
     Statement ste;
    private Equipement r;
    String query = null;
    Connection connection = null ;
    Connection cnx=Maconnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    @FXML
    private TableColumn<Equipement, String> nomeqv;
    @FXML
    private TextField nomequ;
  
    @FXML
    private TextField qu;
    @FXML
    private Button ret;
    @FXML
    private Button print;
    @FXML
    private Button recordd;
    @FXML
    private Button clearpdf;
    @FXML
    private TextArea textttt;
    @FXML
    private Label txttotal;
    @FXML
    private Button barecode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("haute gamme","moyenne gamme");
        comboxgamme.setItems(list);
     loooaad();
      refequipp();
    }    

    @FXML
    private void oncliked(MouseEvent event) {
                   try {
     Equipement cattt = tabequipement.getSelectionModel().getSelectedItem();
   
    
    type.setText(String.valueOf(cattt.getType_e()));
    marquee.setText(String.valueOf(cattt.getMarque()));
   // imageeep.setText(String.valueOf(cattt.getImage()));
    prixx.setText(String.valueOf(cattt.getPrix()));
    String c =  cattt.getGamme();
    comboxgamme.setValue(c);
    nomequ.setText(String.valueOf(cattt.getNom_e()));
    qu.setText(String.valueOf(cattt.getQuantite()));
    
       
        } catch (Exception e) {
               System.out.println(e.getMessage());
           }
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
             connection= Maconnexion.getInstance().getCnx();
          String nom_e =nomequ.getText();
          String type_e =type.getText();
           String marque= marquee.getText();
            String gamme = (String)comboxgamme.getValue();
         int quantite =Integer.valueOf(qu.getText());
        // int h_travail = Integer.valueOf(htp.getText());   
         Date date_commande = Date.valueOf(datecc.getValue());
          String nom_f = comboxnf.getValue();
           EquipemenetService rec = new EquipemenetService();
          int id= rec.chercherIds(nom_f);
         
           double prix =Float.valueOf(prixx.getText());
         
              Scontrol sc= new Scontrol();

  
        Equipement p=new Equipement(id,nom_e,type_e,marque,gamme,quantite,date_commande,prix);
    
    
       
       if(sc.existe(p)==0){
           if(nom_e.isEmpty()||marque.isEmpty()){
     Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("verifier les champs");
            alert.showAndWait();
           }
        else if(!sc.Controlechar2(p))
        { Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Le nom ne doit contenir que des caracteres");
            alert.showAndWait();
        }
        
           else if(prix<=0)
        { Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Le prix doit etre un nombre positif");
            alert.showAndWait();
        }else{

         EquipemenetService ps = new EquipemenetService();
            ps.ajouter(p);
             Notifications notificationBuild = Notifications.create()
                                      .title("Traitement equipement ")
                                      .text("equipement a été ajouté avec succes")
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
 refequipp();
loooaad();
       }
        
       }
       else  {
           Alert alert =new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("le nom d'equipement déjà existe");
            alert.showAndWait();
       }
      
               
     
    }
    
    private void loooaad() {
    EquipemenetService pp=new EquipemenetService();
    connection= Maconnexion.getInstance().getCnx();
    refequipp();
    iffv.setCellValueFactory(new PropertyValueFactory<>("Id"));
   nomeqv.setCellValueFactory(new PropertyValueFactory<>("nom_e")); 
    
    typev.setCellValueFactory(new PropertyValueFactory<>("type_e"));
    marquev.setCellValueFactory(new PropertyValueFactory<>("marque"));
    gammev.setCellValueFactory(new PropertyValueFactory<>("gamme"));
    quantitev.setCellValueFactory(new PropertyValueFactory<>("quantite"));
    datecommandev.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
    prixv.setCellValueFactory(new PropertyValueFactory<>("prix"));
    
    
    nomfournissv.setCellValueFactory(new PropertyValueFactory<>("nom_f"));
    comboxnf.setItems(FXCollections.observableArrayList(pp.getAll()));
    }   
    
   private void refequipp() {     
//        try {
//            platList.clear();
//           
//           query = "select Equipement.id ,Fournisseur.nom_f ,Equipement.nom_e ,Equipement.type_e, Equipement.marque, Equipement.gamme ,Equipement.quantite, Equipement.date_commande,Equipement.prix from Equipement INNER JOIN Fournisseur where Equipement.fournisseur_id=Fournisseur.id ";
//           resultSet = preparedStatement.executeQuery();
//          
//            while (resultSet.next()){
//                
//                platList.add(new  Equipement(
//                    
//                resultSet.getInt("id"),
//           resultSet.getString("nom_f") ,
//              
//                resultSet.getString("nom_e") ,
//                  resultSet.getString("type_e") ,       
//                resultSet.getString("marque"),
//               resultSet.getString("gamme"),
//               resultSet.getInt("quantite"),
//              resultSet.getDate("date_commande"),
//               resultSet.getDouble("prix")
//                         
//              
//                
//               
//                        )); 
//               tabequipement.setItems(platList);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
   platList.clear();
   EquipemenetService ee=new EquipemenetService();
   // ObservableList<Equipement>  platList1 = FXCollections.observableArrayList();
    platList=ee.afficher();
     tabequipement.setItems(platList);
   
            
    }
    
        

    @FXML
    private void modifier(ActionEvent event) {
               Equipement cat=new Equipement();
   EquipemenetService pl = new EquipemenetService();
   cat=tabequipement.getSelectionModel().getSelectedItem();
      int quantite =Integer.valueOf(qu.getText());
   cat.setQuantite(quantite);
   cat.setNom_e(nomequ.getText());
   cat.setMarque(marquee.getText());
  double prix =Float.valueOf(prixx.getText());
   cat.setPrix(prix);
    String gamme = (String)comboxgamme.getValue();
   cat.setGamme(gamme);
   
   
   pl.modifier(cat);
   loooaad(); 
   refequipp();
    }

    @FXML
    private void supprimerr(ActionEvent event) {
                            if (!tabequipement.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer une livraison " + tabequipement.getSelectionModel().getSelectedItem().getNom_e()+ " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    EquipemenetService cc = new EquipemenetService();
    cc.supprimer(tabequipement.getSelectionModel().getSelectedItem());
    refequipp();
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
           type.setText(null);
        marquee.setText(null);
        prixx.setText(null);
        qu.setText(null);
        nomequ.setText(null);
       // g.setText(null);
          comboxgamme.setValue(null);
           comboxnf.setValue(null);
    }

    @FXML
    private void selectgamme(ActionEvent event) {
          String ss = comboxgamme.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void printt(ActionEvent event) throws FileNotFoundException, BadElementException, IOException {
        
        
//        Document document =new Document();
//        String path ="C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1-20220308T220817Z-001\\tester_crud_1";
//        PdfWriter  pdfWriter =new PdfWriter(path);
//        Document doc=new Document();
//        try{
//            PdfWriter pp=PdfWriter.getInstance(doc, new FileOutputStream("Facture"));
//            
//            doc.open();
//            doc.add(new Paragraph(textttt.getText()));
//            doc.close();
//            
//            pp.close();
//            document.add(com.itextpdf.text.Image.getInstance("Images\\logo.png"));
//            document.close();
//            System.out.println("finished");
//        }
//        catch(DocumentException e){
//            e.printStackTrace();
//        }

String nom=tabequipement.getSelectionModel().getSelectedItem().getNom_e();
Document document =new Document();
        String path ="C:\\Users\\LENOVO\\Desktop\\EasyFit";
        //PdfWriter  pdfWriter =new PdfWriter(path);
        Document doc=new Document();
        try{
            PdfWriter pp=PdfWriter.getInstance(doc, new FileOutputStream(nom+".pdf"));
            
            doc.open();
            doc.add(new Paragraph(textttt.getText()));
            doc.close();
            
            pp.close();
           //document.add(com.itextpdf.text.Image.getInstance("C:\\Users\\LENOVO\\Desktop\\EasyFit\\logo.png"));
            document.close();
            
             File file = new File(path);
        if (file.exists()){ 
        if(Desktop.isDesktopSupported()){
            Desktop.getDesktop().open(file);
            System.out.println(pp);
        }}
    
        }
        catch(DocumentException e){
            e.printStackTrace();
        }


    }

    @FXML
    private void record(ActionEvent event) {
        textttt.appendText("\t\t Detail Commande \n\n"+
            
            "\n=====================================\n" +
            
            " Nom de l'equipement :\t\t\t" + nomequ.getText()+
               
             " Marque :\t\t\t" + marquee.getText()+ "\n\n" +
                       
            "Gamme :\t\t\t " + comboxgamme.getValue()+ "\n\n" +
           " Quantite :\t\t\t" + qu.getText()+"\n\n" +
           "Date:\t\t\t" +datecc.getValue()+"\n\n" +
            "prix :\t\t\t " + prixx.getText()+"\n\n" +
              "Nom du fournisseur:\t\t\t" +comboxnf.getValue()+"\n\n" +
               "Total prix:\t\t\t" +txttotal.getText()+"\n\n" +
            "Type:\t\t\t" +type.getText()+"\n" +     
                       
                  "================================"
                  
                  );
    }

    @FXML
    private void clearpdff(ActionEvent event) {
        textttt.setText("");
    //   quantiteeee.set;
    }

    @FXML
    private void getsomme(MouseEvent event) throws SQLException {
            EquipemenetService ps = new EquipemenetService();
      int nbr=ps.calculerequipement();
      this.txttotal.setText(String.valueOf(nbr));
      loooaad();
    }
    
  
    @FXML
    private void barecode(MouseEvent event) {
    if (!tabequipement.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir generer le code a barre de cet equipement " + tabequipement.getSelectionModel().getSelectedItem().getNom_e()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();
if (alert.getResult() == ButtonType.YES) {
String val = ""+((int)(Math.random()*9000)+1000);
String nom=tabequipement.getSelectionModel().getSelectedItem().getNom_e();
BareCode bc=new BareCode();
bc.generateImage(nom,val);

 Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Stock")
                                      .text("le code a été generé avec succes"
                                              )
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
}
 
        }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
        
 
        
    }
 
    
    @FXML
    private void sendQr(KeyEvent event) throws SQLException, WriterException, IOException, ReaderException {
           EquipemenetService rs= new EquipemenetService();
        
            QrCode.getQRCodeImage(rs.getById().toString());
            
            Notifications notificationBuild = Notifications.create()
                                      .title("Check your Email")
                                      .text("Your reservation in Qrcode format")
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
      
    
    }
}
